<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <petclinic:layout_es pageName="habitaciones">
    <h2>Habitaciones disponibles</h2>

    <table id="HabitacionTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Habitación ID</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${habitaciones}" var="hab">
            <tr>
               <td>
             		<c:out value="${hab.id}"></c:out>
             	</td>
             	
             	<td> <spring:url value="/reservas/{reservaId}/todasLasHabitacionesDisponibles/{habitacionId}" var="reservahabUrl">
	                        <spring:param name="reservaId" value="${reservaId}"/> 
	                        <spring:param name="habitacionId" value="${hab.id}"/> 
	                </spring:url>
   					<a href="${fn:escapeXml(reservahabUrl)}" class="btn btn-default">Reservar Habitación</a> </td>
            </tr>
        </c:forEach>
        
        </tbody>
    </table>

</petclinic:layout_es>