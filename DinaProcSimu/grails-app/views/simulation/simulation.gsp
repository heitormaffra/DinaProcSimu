<%@ page import="br.cesjf.dps.Simulation" %>

<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code:'simulation.label', default:'Simulation')}" />
  <title>Sample title</title>
</head>
<body>
  <h1>Executar Simulação</h1>
  <div class="nav" role="navigation">
    <ul>
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="simulationDone"><g:message code="Criar simulação"  /></g:link></li>
      <li><g:link class="create" action="create"><g:message code="Cadastrar simulação"  /></g:link></li>
      <li><g:link class="list" action="list"><g:message code="Listar simulação"  /></g:link></li>
    </ul>
  </div>
</body>
</html>