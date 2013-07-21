<%@ page import="br.cesjf.entities.Tarefa"%>



<div
	class="fieldcontain ${hasErrors(bean: tarefaInstance, field: 'desenvolvedores', 'error')} ">
	<label for="desenvolvedores"> <g:message
			code="tarefa.desenvolvedores.label" default="Desenvolvedores" />

	</label>
	<g:select name="desenvolvedores"
		from="${br.cesjf.entities.Desenvolvedor.list()}" multiple="multiple"
		optionKey="id" size="5" value="${tarefaInstance?.desenvolvedores*.id}"
		class="many-to-many" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: tarefaInstance, field: 'nomeTarefa', 'error')} ">
	<label for="nomeTarefa"> <g:message
			code="tarefa.nomeTarefa.label" default="Nome Tarefa" />

	</label>
	<g:textField name="nomeTarefa" value="${tarefaInstance?.nomeTarefa}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: tarefaInstance, field: 'tempoConcluirTarefa', 'error')} required">
	<label for="tempoConcluirTarefa"> <g:message
			code="tarefa.tempoConcluirTarefa.label"
			default="Tempo Concluir Tarefa" /> <span class="required-indicator">*</span>
	</label>
	<g:field name="tempoConcluirTarefa"
		value="${fieldValue(bean: tarefaInstance, field: 'tempoConcluirTarefa')}"
		required="" />
</div>

