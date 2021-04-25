<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="application">

<form:form  modelAttribute="adoption" class="form-horizontal" id="send-application">
	<div class="form-group has-feedback">
	    <petclinic:inputField label="Info" name="applicationInfo"/>
	</div>
	
	<div class="form-group">
	    <button class="btn btn-default" type="submit">Send application</button>
	</div>
</form:form>

</petclinic:layout>