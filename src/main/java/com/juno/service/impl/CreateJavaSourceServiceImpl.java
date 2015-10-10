package com.juno.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.juno.bean.Config;
import com.juno.createcode.DaoImplTemplate;
import com.juno.createcode.DaoTemplate;
import com.juno.createcode.Entry;
import com.juno.createcode.JavaBeanTemplate;
import com.juno.createcode.MapperImplTemplate;
import com.juno.createcode.SQLBean;
import com.juno.createcode.SQLSource;
import com.juno.createcode.UpdateEntry;
import com.juno.service.CreateSourceService;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class CreateJavaSourceServiceImpl implements CreateSourceService{

    @Autowired
    private SQLSource sqlSource;
    
    @Autowired
    private Config config;
    
    @Deprecated
    private Configuration cfg = new Configuration();

    
    private String createJavaBeanName(String tableName){
        String beanNameTemp = JdbcUtils.convertUnderscoreNameToPropertyName(tableName.toLowerCase());
        return beanNameTemp.substring(0, 1).toUpperCase() + beanNameTemp.substring(1);
    }
    
    /**
     * 
     * @param tableName
     *            大写的table名称
     * @return
     * @throws Exception
     */
    public JavaBeanTemplate createJavaBean(String tableName) throws RuntimeException {
        Map<String, Object> root = new HashMap<String, Object>();

        // 通过tableName创建JavaBeanName
        String className = this.createJavaBeanName(tableName);

        JavaBeanTemplate template = new JavaBeanTemplate();
        template.setPackageName(config.getCodeBase() + "." + config.getEntryPkgName());  //1. javaBean 所在的package
        template.setClassName(className);  // 3. JavaBeanName
        template.setJavaBeanFullName(template.getPackageName() + "." + template.getClassName()); // 该javaBean的包点类名
        
        SQLBean sqlBean = sqlSource.getSQLBean(tableName);
        template.setPrimaryKey(JdbcUtils.convertUnderscoreNameToPropertyName(sqlBean.getPkName()));  // 主键
        template.setCodeBase(config.getCodeBase());
        template.setTableName(tableName.toUpperCase());
        
        List<String> columns = sqlBean.getColumnNames();  // 表的字段名称
        List<Integer> types = sqlBean.getColumnTypes();  // 字段的类型
        List<String> decimalDigits = sqlBean.getDecimalDigits();  // number 字段的精度
        List<String> remarks = sqlBean.getRemarks();  // 字段的描述

        //4. 包含类中的字段（字段名value，类型type，描述remark）
        List<Entry> entrys = new ArrayList<Entry>();
        //2. 该javaBean所需要导入的java类
        List<String> imports = new ArrayList<String>();

        for (int i = 0; i < columns.size(); i++) {
            Class<?> type = getType(types.get(i), decimalDigits.get(i));
            String value = JdbcUtils.convertUnderscoreNameToPropertyName(columns.get(i).toLowerCase());
            entrys.add(new Entry(type.getSimpleName(), value, type, remarks.get(i)));
            if (!type.getCanonicalName().startsWith("java.lang")) {
                if (!imports.contains(type.getCanonicalName())) {
                    imports.add(type.getCanonicalName());
                }
            }
        }

        template.setImports(imports);
        template.setEntrys(entrys);

        root.put("template", template);

        String srcPath = config.getCreateCodeLocation() + config.getEntryPkgName() + "/" + className;
        writer(root, "javaBean.ftl", srcPath + ".java");

        return template;
    }

    public DaoTemplate createDao(JavaBeanTemplate javaBeanTemplate) throws RuntimeException {
        Map<String, Object> root = new HashMap<String, Object>();

        DaoTemplate template = new DaoTemplate();
        
        //1. dao层 所在的package
        template.setPackageName(javaBeanTemplate.getCodeBase() + "." + config.getDaoPkgName());
        //2. javaBeanName
        template.setJavaBeanName(javaBeanTemplate.getClassName());
        template.setDaoName(template.getJavaBeanName() + config.getDaoSuffixName());  // Dao 的简单名称
        template.setDaoFullName(template.getPackageName() + "." + template.getDaoName()); // Dao 的包点类名

        List<String> imports = new ArrayList<String>();
        imports.add(javaBeanTemplate.getJavaBeanFullName());
        template.setImports(imports);

        root.put("template", template);

        String srcPath = config.getCreateCodeLocation() + config.getDaoPkgName() + "/" + template.getDaoName();
        writer(root, "DaoBean.ftl", srcPath + ".java");

        return template;
    }

    public DaoImplTemplate createDaoImpl(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate)
            throws RuntimeException {
        Map<String, Object> root = new HashMap<String, Object>();

        DaoImplTemplate template = new DaoImplTemplate();
        template.setPackageName(daoTemplate.getPackageName() + "." + config.getDaoImplPkgName());
        template.setJavaBeanName(javaBeanTemplate.getClassName());
        template.setDaoBeanName(daoTemplate.getDaoName());
        
        template.setDaoImplBeanName(daoTemplate.getDaoName() + config.getDaoImplSuffixName());// DaoImpl 的简单名称
        template.setDaoImplFullBeanName(template.getPackageName() + "." + template.getDaoImplBeanName());  // DaoImpl 的包点类名

        List<String> imports = new ArrayList<String>();
        imports.add(Repository.class.getCanonicalName());
        imports.add(config.getGenericDaoDefault());  // GenericDaoDefault 包点类名
        imports.add(javaBeanTemplate.getJavaBeanFullName());
        imports.add(daoTemplate.getDaoFullName());
        template.setImports(imports);

        root.put("template", template);

        String srcPath = config.getCreateCodeLocation() + config.getDaoPkgName() + "/" + config.getDaoImplPkgName() + "/";
        srcPath = srcPath + template.getDaoImplBeanName();
        writer(root, "DaoImplBean.ftl", srcPath + ".java");

        return template;
    }

    public MapperImplTemplate createMapper(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate,
            DaoImplTemplate daoImplTemplate) throws RuntimeException {
        Map<String, Object> root = new HashMap<String, Object>();

        MapperImplTemplate template = new MapperImplTemplate();
        template.setNameSpace(javaBeanTemplate.getClassName());
        template.setJavaBeanFullName(javaBeanTemplate.getJavaBeanFullName());
        SQLBean sqlBean = this.sqlSource.getSQLBean(javaBeanTemplate.getTableName());
        template.setInsert(sqlBean.getInertSql());
        template.setUpdate(sqlBean.getUpdateSql());
        template.setDelete(sqlBean.getDeleteSql());
        template.setFindById(sqlBean.getFindByIdSql());
        template.setGetAll(sqlBean.getFindAllSql());
        template.setBaseColumnList(sqlBean.getBaseColumnList());

        template.setPkJavaName(sqlBean.getPkBeanName());
        template.setPkName(sqlBean.getPkName());
        template.setPkNameUpdate("#{" + sqlBean.getPkBeanName() + ", jdbcType=" + sqlBean.getPkJdbcType() + "}");
        template.setTableName(javaBeanTemplate.getTableName());
        template.setPkJdbcType(sqlBean.getPkJdbcType());

        List<UpdateEntry> entrys = new ArrayList<UpdateEntry>();

        if (sqlBean.getColumnNames() != null && sqlBean.getColumnNames().size() > 0) {
            for (int i = 0; i < sqlBean.getColumnNames().size(); i++) {
                String column = sqlBean.getColumnNames().get(i);
                String javaColumn = sqlBean.getJavaBeanColumns().get(i);
                String jdbcType = sqlBean.getJdbcTypes().get(i);
                if (!sqlBean.getPkName().equals(column)) {
                    UpdateEntry entry = new UpdateEntry(column, javaColumn, "#{" + javaColumn + ", jdbcType="
                            + jdbcType + "}", jdbcType);
                    entrys.add(entry);
                }
            }
        }

        template.setUpdateEntrys(entrys);
        root.put("template", template);

        String srcPath = config.getCreateCodeLocation() + config.getMapperLocation() + "/" + javaBeanTemplate.getClassName() + config.getMapperFileSuffix();
        writer(root, "mapperBean.ftl", srcPath + ".xml");

        return template;
    }

    public Class<?> getType(int sqlType, String decimalDigit) {
        if (sqlType == Types.VARCHAR || sqlType == Types.LONGVARCHAR || sqlType == Types.CLOB) {
            return String.class;
        } else if (sqlType == Types.DECIMAL || sqlType == Types.NUMERIC) {
            int digit = 0;
            try {
                digit = Integer.parseInt(decimalDigit);
            } catch (Exception e) {
            }

            if (digit > 0) {
                return BigDecimal.class;
            } else if (digit == 0) {
                return Integer.class;
            }
        } else if (sqlType == Types.DATE) {
            return Date.class;
        } else if (sqlType == Types.TIME) {
            return Time.class;
        } else if (sqlType == Types.TIMESTAMP) {
            return Timestamp.class;
        } else if (sqlType == Types.BIGINT) {
            return Long.class;
        } else if (sqlType == Types.TINYINT || sqlType == Types.INTEGER) {
            return Integer.class;
        }

        return String.class;
    }

    public void writer(Map<String, Object> root, String templatePath, String sourcePath) throws RuntimeException {
        OutputStream out = null;
        try {
            cfg.setClassForTemplateLoading(CreateJavaSourceServiceImpl.class, "");
            Template t = cfg.getTemplate(templatePath, "UTF-8");
            File file = new File(sourcePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            out = new FileOutputStream(new File(sourcePath));
            t.process(root, new OutputStreamWriter(out, "UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
