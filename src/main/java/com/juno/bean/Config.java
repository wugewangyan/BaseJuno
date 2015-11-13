package com.juno.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
    
    // 代码基【cn.creditease.pay.trade.core】
    @Value("${code.base}")
    private String codeBase;
    
    // 实体的包名称【entry】
    @Value("${entry.pkg.name}")
    private String entryPkgName; 
    
    // dao层的包名称【dao】
    @Value("${dao.pkg.name}")
    private String daoPkgName;
    
    // dao层的类名称的后缀【Dao】 ==> InvestOrderDao
    @Value("${dao.suffix.name}")
    private String daoSuffixName;
    
    // dao实现层的包名称【impl】
    @Value("${dao.impl.pkg.name}")
    private String daoImplPkgName;
    
    
    // dao层的类名称的后缀【Impl】 ==> InvestOrderDaoImpl
    @Value("${dao.impl.suffix.name}")
    private String daoImplSuffixName;

    // 生成代码的基目录【以/结束D:/yixin/output/】
    @Value("${create.code.location}")
    private String createCodeLocation;
    
    // GenericDaoDefault的包点类名【cn.creditease.pay.trade.core.dao.GenericDaoDefault】
    @Value("${pkg.genericDaoDefault}")
    private String genericDaoDefault;
    
    
    // Mapper文件的存放目录【trade-core-sqlmap】
    @Value("${location.mapper}")
    private String mapperLocation;
    
    // Mapper文件的后缀【Mapper】 ==> InvestOrderMapper.xml
    @Value("${mapper.file.suffix}")
    private String mapperFileSuffix;
    
    // 适用的数据库schema
    @Value("${db.schema}")
    private String dbSchema;
    
    @Value("${redis.ip}")
    private String redisIp;
    
    public String getCodeBase() {
        return codeBase;
    }

    public void setCodeBase(String codeBase) {
        this.codeBase = codeBase;
    }

    public String getEntryPkgName() {
        return entryPkgName;
    }

    public void setEntryPkgName(String entryPkgName) {
        this.entryPkgName = entryPkgName;
    }

    public String getCreateCodeLocation() {
        return createCodeLocation;
    }

    public void setCreateCodeLocation(String createCodeLocation) {
        this.createCodeLocation = createCodeLocation;
    }

    public String getDaoPkgName() {
        return daoPkgName;
    }

    public void setDaoPkgName(String daoPkgName) {
        this.daoPkgName = daoPkgName;
    }

    public String getDaoSuffixName() {
        return daoSuffixName;
    }

    public void setDaoSuffixName(String daoSuffixName) {
        this.daoSuffixName = daoSuffixName;
    }

    public String getDaoImplPkgName() {
        return daoImplPkgName;
    }

    public void setDaoImplPkgName(String daoImplPkgName) {
        this.daoImplPkgName = daoImplPkgName;
    }

    public String getDaoImplSuffixName() {
        return daoImplSuffixName;
    }

    public void setDaoImplSuffixName(String daoImplSuffixName) {
        this.daoImplSuffixName = daoImplSuffixName;
    }

    public String getGenericDaoDefault() {
        return genericDaoDefault;
    }

    public void setGenericDaoDefault(String genericDaoDefault) {
        this.genericDaoDefault = genericDaoDefault;
    }

    public String getMapperLocation() {
        return mapperLocation;
    }

    public void setMapperLocation(String mapperLocation) {
        this.mapperLocation = mapperLocation;
    }

    public String getMapperFileSuffix() {
        return mapperFileSuffix;
    }

    public void setMapperFileSuffix(String mapperFileSuffix) {
        this.mapperFileSuffix = mapperFileSuffix;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }

	public String getRedisIp() {
		return redisIp;
	}

	public void setRedisIp(String redisIp) {
		this.redisIp = redisIp;
	}
}
