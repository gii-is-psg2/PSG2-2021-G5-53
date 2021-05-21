<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <spring:url value="bienvenido" var="bienvenido">
    </spring:url>
    <a href="${fn:escapeXml(bienvenido)}" class="btn btn-default">Español</a>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/lion-png.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" src="${petsImage}" width="200" height="200"/>
        </div>
    </div>
    
   <h1>Sobre Nosotros</h1> 
   <h1> - Veterinarios: Podemos ver los distintos veterinarios, borrarlos o modificarlos</h1>
   <h1> - Owners: Podemos buscar un owner, ver sus distintas mascotas y modificarlas</h1>
   <h1> - Habitaciones: Podemos reservar una habitación para nuestras mascotas eligiendo la fecha que mas nos interese</h1>
   <h1> - Adopciones: Podemos adoptar las mascotas que otros owners tienen en adopcion, y también podemos poner nuestras mascotas en adopción </h1>
   <h1> - Causas: Podemos crear causas, y realizar donaciones hasta alcanzar el límite propuesto</h1>
</petclinic:layout>