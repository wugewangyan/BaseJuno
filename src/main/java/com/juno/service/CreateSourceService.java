package com.juno.service;

import com.juno.createcode.DaoImplTemplate;
import com.juno.createcode.DaoTemplate;
import com.juno.createcode.JavaBeanTemplate;
import com.juno.createcode.MapperImplTemplate;

public interface CreateSourceService {

    public JavaBeanTemplate createJavaBean(String tableName) throws RuntimeException;
    
    
    public DaoTemplate createDao(JavaBeanTemplate javaBeanTemplate) throws RuntimeException;
    
    
    public DaoImplTemplate createDaoImpl(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate)
            throws RuntimeException;
    
    public MapperImplTemplate createMapper(JavaBeanTemplate javaBeanTemplate, DaoTemplate daoTemplate,
            DaoImplTemplate daoImplTemplate) throws RuntimeException;
}
