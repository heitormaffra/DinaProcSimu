<%@ page import="br.cesjf.entities.Desenvolvedor"%>



<div
	class="fieldcontain ${hasErrors(bean: desenvolvedorInstance, field: 'nome', 'error')} required">
	<label for="nome"> <g:message code="desenvolvedor.nome.label"
			default="Nome" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required=""
		value="${desenvolvedorInstance?.nome}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: desenvolvedorInstance, field: 'experiencia', 'error')} required">
	<label for="experiencia"> <g:message
			code="desenvolvedor.experiencia.label" default="Experiencia" /> <span
		class="required-indicator">*</span>
	</label>
	<g:field name="experiencia"
		value="${fieldValue(bean: desenvolvedorInstance, field: 'experiencia')}"
		required="" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: desenvolvedorInstance, field: 'tarefas', 'error')} ">
	<label for="tarefas"> <g:message
			code="desenvolvedor.tarefas.label" default="Tarefas" />

	</label>

</div>

