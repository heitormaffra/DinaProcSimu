
<%@ page import="br.cesjf.dps.Task" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${taskInstance?.name}" />
		<title><g:message code="task.messages.show" args="[entityName]"/></title>
	</head>
	<body>
		<a href="#show-task" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="task.messages.list"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="task.messages.create" /></g:link></li>
			</ul>
		</div>
		<div id="show-task" class="content scaffold-show" role="main">
			<h1><g:message code="task.messages.show" args="[entityName]"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list task">
			
				<g:if test="${taskInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="task.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${taskInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.parentTask}">
				<li class="fieldcontain">
					<span id="parentTask-label" class="property-label"><g:message code="task.parentTask.label" default="Parent Task" /></span>
					
						<span class="property-value" aria-labelledby="parentTask-label"><g:link controller="task" action="show" id="${taskInstance?.parentTask?.id}">${taskInstance?.parentTask?.name}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${taskInstance?.developers}">
				<li class="fieldcontain">
					<span id="developers-label" class="property-label"><g:message code="task.developers.label" default="Developers" /></span>
					
						<g:each in="${taskInstance.developers}" var="d">
						<span class="property-value" aria-labelledby="developers-label"><g:link controller="developer" action="show" id="${d.id}">${d?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${taskInstance?.id}" />
					<g:link class="edit" action="edit" id="${taskInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
