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
       Sorry for the inconvenience!
    </h2>
   <p>You have an active booking for this pet. Details of your current booking: </p>
   <table id="choosePetTable" class="table table-striped">
        <thead>
        <tr>
            <th>Start Date</th>
           <th>End Date</th>
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
        
        </tbody> 
    </table>
    <p> If you want to book a room for this pet, please choose another dates.</p>
    <spring:url value="/reservas/{petId}/new" var="pethotelform">
	                        <spring:param name="petId" value="${petId}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(pethotelform)}" class="btn btn-default">Return to Pet Hotel form</a>
    </jsp:body>
   
</petclinic:layout>