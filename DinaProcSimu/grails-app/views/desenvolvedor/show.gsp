
<%@ page import="br.cesjf.entities.Desenvolvedor"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'desenvolvedor.label', default: 'Desenvolvedor')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-desenvolvedor" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><g:link class="list" action="list">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="show-desenvolvedor" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list desenvolvedor">

			<g:if test="${desenvolvedorInstance?.nome}">
				<li class="fieldcontain"><span id="nome-label"
					class="property-label"><g:message
							code="desenvolvedor.nome.label" default="Nome" /></span> <span
					class="property-value" aria-labelledby="nome-label"><g:fieldValue
							bean="${desenvolvedorInstance}" field="nome" /></span></li>
			</g:if>

			<g:if test="${desenvolvedorInstance?.experiencia}">
				<li class="fieldcontain"><span id="experiencia-label"
					class="property-label"><g:message
							code="desenvolvedor.experiencia.label" default="Experiencia" /></span>

					<span class="property-value" aria-labelledby="experiencia-label"><g:fieldValue
							bean="${desenvolvedorInstance}" field="experiencia" /></span></li>
			</g:if>

			<g:if test="${desenvolvedorInstance?.tarefas}">
				<li class="fieldcontain"><span id="tarefas-label"
					class="property-label"><g:message
							code="desenvolvedor.tarefas.label" default="Tarefas" /></span> <g:each
						in="${desenvolvedorInstance.tarefas}" var="t">
						<span class="property-value" aria-labelledby="tarefas-label"><g:link
								controller="tarefa" action="show" id="${t.id}">
								${t?.encodeAsHTML()}
							</g:link></span>
					</g:each></li>
			</g:if>

		</ol>
		<g:form>
			<fieldset class="buttons">
				<g:hiddenField name="id" value="${desenvolvedorInstance?.id}" />
				<g:link class="edit" action="edit" id="${desenvolvedorInstance?.id}">
					<g:message code="default.button.edit.label" default="Edit" />
				</g:link>
				<g:actionSubmit class="delete" action="delete"
					value="${message(code: 'default.button.delete.label', default: 'Delete')}"
					onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
			</fieldset>
		</g:form>
	</div>
</body>
</html>
