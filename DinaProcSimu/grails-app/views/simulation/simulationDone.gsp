
<%@ page import="br.cesjf.dps.Simulation" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'simulation.label', default: 'Simulation')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#list-simulation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>
  <div id="list-simulation" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
      <thead>
        <tr>

      <g:sortableColumn property="timeToConclude" title="${message(code: 'simulation.timeToConclude.label', default: 'Tempo para concluir')}" />

      </tr>
      </thead>
      <tbody>

      <td>${fieldValue(bean: simulationInstance, field: "timeToConclude")}</td>

      </tbody>
    </table>
  </div>
</body>
</html>
