
<%@ page import="br.cesjf.entities.Tarefa"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'tarefa.label', default: 'Tarefa')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-tarefa" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><a class="home" href="${createLink(uri: '/')}"><g:message
						code="default.home.label" /></a></li>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="list-tarefa" class="content scaffold-list" role="main">
		<h1>
			<g:message code="default.list.label" args="[entityName]" />
		</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>
		<table>
			<thead>
				<tr>

					<g:sortableColumn property="nomeTarefa"
						title="${message(code: 'tarefa.nomeTarefa.label', default: 'Nome Tarefa')}" />

					<g:sortableColumn property="tempoConcluirTarefa"
						title="${message(code: 'tarefa.tempoConcluirTarefa.label', default: 'Tempo Concluir Tarefa')}" />

				</tr>
			</thead>
			<tbody>
				<g:each in="${tarefaInstanceList}" status="i" var="tarefaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${tarefaInstance.id}">
								${fieldValue(bean: tarefaInstance, field: "nomeTarefa")}
							</g:link></td>

						<td>
							${fieldValue(bean: tarefaInstance, field: "tempoConcluirTarefa")}
						</td>

					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<g:paginate total="${tarefaInstanceTotal}" />
		</div>
	</div>
</body>
</html>
