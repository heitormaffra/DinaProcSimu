
<%@ page import="br.cesjf.entities.Tarefa"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'tarefa.label', default: 'Tarefa')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#show-tarefa" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="list" action="list">
					<g:message code="default.list.label" args="[entityName]" />
				</g:link></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="show-tarefa" class="content scaffold-show" role="main">
		<h1>
			<g:message code="default.show.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<ol class="property-list tarefa">

			<g:if test="${tarefaInstance?.desenvolvedores}">
				<li class="fieldcontain"><span id="desenvolvedores-label"
					class="property-label"><g:message
							code="tarefa.desenvolvedores.label" default="Desenvolvedores" /></span>

					<g:each in="${tarefaInstance.desenvolvedores}" var="d">
						<span class="property-value"
							aria-labelledby="desenvolvedores-label"><g:link
								controller="desenvolvedor" action="show" id="${d.id}">
								${d?.encodeAsHTML()}
							</g:link></span>
					</g:each></li>
			</g:if>

			<g:if test="${tarefaInstance?.nomeTarefa}">
				<li class="fieldcontain"><span id="nomeTarefa-label"
					class="property-label"><g:message
							code="tarefa.nomeTarefa.label" default="Nome Tarefa" /></span> <span
					class="property-value" aria-labelledby="nomeTarefa-label"><g:fieldValue
							bean="${tarefaInstance}" field="nomeTarefa" /></span></li>
			</g:if>

			<g:if test="${tarefaInstance?.tempoConcluirTarefa}">
				<li class="fieldcontain"><span id="tempoConcluirTarefa-label"
					class="property-label"><g:message
							code="tarefa.tempoConcluirTarefa.label"
							default="Tempo Concluir Tarefa" /></span> <span class="property-value"
					aria-labelledby="tempoConcluirTarefa-label"><g:fieldValue
							bean="${tarefaInstance}" field="tempoConcluirTarefa" /></span></li>
			</g:if>

		</ol>
		<g:form>
			<fieldset class="buttons">
				<g:hiddenField name="id" value="${tarefaInstance?.id}" />
				<g:link class="edit" action="edit" id="${tarefaInstance?.id}">
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
