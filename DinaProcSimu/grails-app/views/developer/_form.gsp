<%@ page import="br.cesjf.dps.Developer" %>



<div class="fieldcontain ${hasErrors(bean: developerInstance, field: 'name', 'error')} required">
  <label for="name">
    <g:message code="developer.name.label" default="Nome" />
    <span class="required-indicator">*</span>
  </label>
  <g:textField name="name" maxlength="50" required="" value="${developerInstance?.name}"/>
</div>