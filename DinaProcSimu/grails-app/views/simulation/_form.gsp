<%@ page import="br.cesjf.dps.Simulation" %>



<div class="fieldcontain ${hasErrors(bean: simulationInstance, field: 'file', 'error')} required">
	<label for="file">
		<g:message code="simulation.file.label" default="File" />
		<span class="required-indicator">*</span>
	</label>
   <input type="file" name="file" id="file" />${simulationInstance.file}
</div>

