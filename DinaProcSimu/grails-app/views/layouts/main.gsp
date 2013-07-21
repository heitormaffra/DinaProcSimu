<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="Grails" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir: 'images', file: 'favicon.ico')}"
	type="image/x-icon">
<link rel="apple-touch-icon"
	href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
<link rel="apple-touch-icon" sizes="114x114"
	href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'paginas.css')}" type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'menu.css')}"
	type="text/css">
<g:layoutHead />
<r:layoutResources />
</head>
<body>
	<div id="grailsLogo" role="banner">
		<div id="grailsLogoImage" role="banner">
			<img src="${resource(dir: 'images', file: 'DinaProcSimuLogo.png')}"
				alt="Logo" />
		</div>
	</div>
	<div id="menu">
		<ul class="menu">
			<li><a class="home" href="${createLink(uri: '/')}"><span><g:message
							code="default.home.label" /></a></span></li>
			<li><a href="#" class="parent"><span>Cadastros</span></a>
				<ul>
					<g:each var="c"
						in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<g:if test="${c.name != 'Dbdoc'}">
							<li class="controller"><g:link
									controller="${c.logicalPropertyName}">
									${c.name}
								</g:link></li>
						</g:if>
					</g:each>
				</ul></li>
			<li class="last"><a href="#"><span>Simulações</span></a></li>
		</ul>
	</div>
	<g:layoutBody />
	<div class="footer" role="contentinfo"></div>
	<div id="spinner" class="spinner" style="display: none;">
		<g:message code="spinner.alt" default="Loading&hellip;" />
	</div>
	<g:javascript library="application" />
	<r:layoutResources />
</body>
</html>
