package com.juno.controller;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.juno.createcode.DaoImplTemplate;
import com.juno.createcode.DaoTemplate;
import com.juno.createcode.JavaBeanTemplate;
import com.juno.service.CreateSourceService;

@Controller
public class CodeProductController {

    @Autowired
    private CreateSourceService createSourceService;

    @Autowired
    private DataSource dataSource;


    @RequestMapping("/code")
    public String generator(@RequestParam(value = "tableName", required = false) String tableName,
            @RequestParam(value = "schema", required = false) String schema) {

        List<String> tableNames = new ArrayList<String>();

        try {
            if (tableName != null && !"".equals(tableName)) {
                tableNames.add(tableName);
            } else {
                DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
                ResultSet resultSet = metaData.getTables(null, schema, null, new String[] {"TABLE"});
                while (resultSet.next()) {
                    tableNames.add(resultSet.getString(3));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (tableNames != null && tableNames.size() > 0) {
            for (String tName : tableNames) {
                JavaBeanTemplate javaBeanTemplate = createSourceService.createJavaBean(tName);
                DaoTemplate daoTemplate = createSourceService.createDao(javaBeanTemplate);
                DaoImplTemplate daoImplTemplate = createSourceService.createDaoImpl(javaBeanTemplate,
                        daoTemplate);
                createSourceService.createMapper(javaBeanTemplate,
                        daoTemplate, daoImplTemplate);
            }
        }

        return "code/create_code";
    }

}
