<%@ page contentType ="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/business/ppmp/core/init.jsp" %>

<head>
	<script type="text/javascript">
	</script>
</head>

<body>
	<pccw-ui:title name="${template.title}">
		<pccw-ui:xToobar>
			<pccw-ui:xBtn value="保存" className="add" id="saveButton"/>
		</pccw-ui:xToobar>
		<form id="baseForm" name="baseForm" action="<%=request.getContextPath() %>/${template.actionPath}.do?method=save" method="post">
			
			<input type="hidden" name="${template.pkField}" id="${template.pkField}"/>
		
			<table class="createTable" cellSpacing="1" cellPadding="1">
		<#assign size = "${template.list?size}"/>	
		<#list template.list as vo> 
			<#if vo_index % 2 == 0>
				<tr>
			</#if>	
					<td class="title_column">
						${vo.fieldTitle}
					</td>
					<td class="content_column" style="width: 40%">
						<input type="text" id="${vo.value}"  name="${vo.value}"/>
					</td>
			<#if (vo_index + 1) % 2 == 0>
				</tr>
			</#if>
			<#if (vo_index + 1) == template.list?size>
				</tr>
			</#if>	
		</#list>	
			</table>
		</form>
	</pccw-ui:title>
</body>