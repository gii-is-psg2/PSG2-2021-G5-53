<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="reservas">

    <jsp:body>
    <h2>
       ¡Lo sentimos!
    </h2>
   <p>Ya tienes una reserva activa para esta mascota. </p>
   <table id="choosePetTable" class="table table-striped">
        <thead>
        <tr>
            <th>Fecha de inicio</th>
           <th>Fecha de fin</th>
           <th>Descripcion</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <c:out value="${reserva.fechaInicio}"/>
                </td>
                <td>
                <c:out value="${reserva.fechaFin}"/>
             	</td>
             	<td>
                <c:out value="${reserva.descripcion}"/>
             	</td>
             	
        
        </tbody> 
    </table>
    <p>Si quieres reservar una habitación para esta mascota, por favor seleccione otras fechas.</p>
    <spring:url value="/reservas/{petId}/nueva" var="pethotelform">
	                        <spring:param name="petId" value="${petId}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pethotelform)}" class="btn btn-default">Volver al formulario del hotel para mascotas</a>
    </jsp:body>
   
</petclinic:layout>