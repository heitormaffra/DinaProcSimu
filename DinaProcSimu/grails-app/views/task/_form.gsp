<%@ page import="br.cesjf.dps.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="task.name.label" default="Tarefa" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" 
                     maxlength="20" 
                     required="" 
                     value="${taskInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'parentTask', 'error')} ">
	<label for="parentTask">
		<g:message code="task.parentTask.label" 
                           default="Tarefa Pai" />
	</label>
	<g:select id="parentTask" 
                  name="parentTask.id" 
                  from="${br.cesjf.dps.Task.list()}" 
                  optionKey="id" 
                  value="${taskInstance?.parentTask?.id}" 
                  optionValue="name"
                  class="many-to-one" 
                  noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'developers', 'error')} ">
	<label for="developers">
		<g:message code="task.developers.label" 
                           default="Desenvolvedor(es)" />
		
	</label>
	<g:select name="developers" from="${br.cesjf.dps.Developer.list()}" 
                  multiple="multiple" 
                  optionKey="id" 
                  size="5" 
                  value="${taskInstance?.developers*.id}" 
                  optionValue="name"
                  class="many-to-many"/>
</div>

