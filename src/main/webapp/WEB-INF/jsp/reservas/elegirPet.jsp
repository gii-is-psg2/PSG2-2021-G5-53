
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<petclinic:layout pageName="choosePet">

<h2> What pet do you want to book a room for?</h2>
    <table id="choosePetTable" class="table table-striped">
        <thead>
        <tr>
            <th>Pet name</th>
           <th>Book room</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pets}" var="pet">
        	<c:if test="${!pet.onAdoption}">
            <tr>
                <td>
                    <c:out value="${pet.name}"/>
                </td>
                <td>
             		<spring:url value="/reservas/{petId}/new" var="choosePetUrl">
	                        <spring:param name="petId" value="${pet.id}"/>
	                </spring:url>
   					<a href="${fn:escapeXml(choosePetUrl)}" class="btn btn-default">Book!</a>
             	</td>
             	</c:if>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>