<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout_es pageName="casa">
    <h2>Bienvenido a PSG2-2021-G5-53 Petclinic</h2>
    <spring:url value="/" var="bienvenido">
    </spring:url>
    <a href="${fn:escapeXml(bienvenido)}" class="btn btn-default">Inglés</a>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/fotogato.jpeg" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}" width="200" height="200"/>
        </div>
    </div>
</petclinic:layout_es>
