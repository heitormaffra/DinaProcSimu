
<%@ page import="br.cesjf.entities.Desenvolvedor"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'desenvolvedor.label', default: 'Desenvolvedor')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<a href="#list-desenvolvedor" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div class="nav" role="navigation">
		<ul>
			<li><g:link class="create" action="create">
					<g:message code="default.new.label" args="[entityName]" />
				</g:link></li>
		</ul>
	</div>
	<div id="list-desenvolvedor" class="content scaffold-list" role="main">
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

					<g:sortableColumn property="nome"
						title="${message(code: 'desenvolvedor.nome.label', default: 'Nome')}" />

					<g:sortableColumn property="experiencia"
						title="${message(code: 'desenvolvedor.experiencia.label', default: 'Experiencia')}" />

				</tr>
			</thead>
			<tbody>
				<g:each in="${desenvolvedorInstanceList}" status="i"
					var="desenvolvedorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${desenvolvedorInstance.id}">
								${fieldValue(bean: desenvolvedorInstance, field: "nome")}
							</g:link></td>

						<td>
							${fieldValue(bean: desenvolvedorInstance, field: "experiencia")}
						</td>

					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<g:paginate total="${desenvolvedorInstanceTotal}" />
		</div>
	</div>
</body>
</html>
