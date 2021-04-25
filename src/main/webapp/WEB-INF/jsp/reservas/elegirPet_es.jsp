<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<petclinic:layout_es pageName="choosePet">

<h2> ¿Que habitación quieres reservar para tu mascota?</h2>
    <table id="choosePetTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre mascota</th>
           <th>Habitacion reservada</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pets}" var="pet">
            <tr>
                <td>
                    <c:out value="${pet.name}"/>
                </td>
                <td>
             		<spring:url value="/reservas/{reservaId}/todasLasHabitacionesDisponibles/{habitacionId}/{petId}" var="choosePetUrl">
	                        <spring:param name="reservaId" value="${reservaId}"/>
	                        <spring:param name="habitacionId" value="${habitacionId}"/>
	                        <spring:param name="petId" value="${pet.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(choosePetUrl)}" class="btn btn-default">Reservar una habitación para esta mascota!</a>
             	</td>
        </c:forEach>
        
        </tbody>
    </table>
   
</petclinic:layout_es>