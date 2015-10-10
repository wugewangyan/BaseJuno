package ${template.packageName};  
   
 <#list template.imports as being>  
 import ${being};  
 </#list>  
 
 
 public interface ${template.javaBeanName}Dao extends GenericDao<${template.javaBeanName}>{

}
 
