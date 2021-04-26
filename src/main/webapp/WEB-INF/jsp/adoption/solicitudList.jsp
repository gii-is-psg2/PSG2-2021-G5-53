<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="adopciones">

    <h2>Pets waiting for a family</h2><br>

    <table id="adoptionList" class="table table-striped">

        <!-- Header table for pets on adoption -->
        <thead>
            <tr>
                <th style="width: 150px;">Name</th>
                <th style="width: 200px;">Type</th>
                <th style="width: 200px;">Birthdate</th>
                <th style="width: 200px;">Adoption</th>
            </tr>
        </thead>

        <!-- Display pets for adoption -->
        <tbody>
            <c:forEach items="${adoptionList}" var="pet">
                        <tr>
                            <td>
                                <c:out value="${pet.name}" />
                            </td>
                            <td>
                                <c:out value="${pet.type}" />
                            </td>
                            <td>
                                <c:out value="${pet.birthDate}" />
                            </td>
                            <td>
                            	<spring:url value="adoption/save/{ownerId}/{petId}" var="adoptUrlSave">
                           		<spring:param name="ownerId" value="${ownerId}"/>
                           		<spring:param name="petId" value="${pet.id}"/>
                           		</spring:url>
                           		<a href="${fn:escapeXml(adoptUrlSave)}">Adopt</a>
                           	</td> 
                        </tr>
            </c:forEach>
        </tbody>
    </table><br>
</petclinic:layout>