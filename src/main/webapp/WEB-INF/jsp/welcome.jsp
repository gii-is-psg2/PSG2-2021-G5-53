<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/> to the PSG2-2021-G5-53 Petclinic </h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/lion-png.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}" width="200" height="200"/>
        </div>
    </div>
</petclinic:layout>
