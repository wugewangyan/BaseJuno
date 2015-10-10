package ${template.packageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
 @Repository
 public class ${template.daoBeanName}Impl extends GenericDaoDefault<${template.javaBeanName}> implements ${template.daoBeanName}{

}
 
