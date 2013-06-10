<%@ page import="br.cesjf.dps.Simulation" %>



<div class="fieldcontain ${hasErrors(bean: simulationInstance, field: 'file', 'error')} ">
	<label for="file">
		<g:message code="simulation.file.label" default="File" />
		
	</label>
	<g:field type="file" name="file" value="${fieldValue(bean: simulationInstance, field: 'file')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: simulationInstance, field: 'timeToConclude', 'error')} required">
	<label for="timeToConclude">
		<g:message code="simulation.timeToConclude.label" default="Time To Conclude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="timeToConclude" value="${fieldValue(bean: simulationInstance, field: 'timeToConclude')}" required=""/>
</div>

