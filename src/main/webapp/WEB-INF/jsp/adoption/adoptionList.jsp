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
                <th style="text-align: center; width: 150px;">Name</th>
                <th style="text-align: center; width: 200px;">Type</th>
                <th style="text-align: center; width: 200px;">Birthdate</th>
                <th style="text-align: center; width: 200px;">Adoption</th>
            </tr>
        </thead>

        <!-- Display pets for adoption -->
        <tbody>
            <c:forEach items="${adoptionList}" var="pet">
                        <tr>
                            <td style="text-align: center;">
                                <c:out value="${pet.name}" />
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${pet.type}" />
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${pet.birthDate}" />
                            </td>
                            <td style="text-align: center;">
	                        	<spring:url value="adoption/application/{petId}" var="applicationURL">
	                       		<spring:param name="petId" value="${pet.id}"/>
	                       		</spring:url>
	                       		<a href="${fn:escapeXml(applicationURL)}">Apply</a>
                       		</td>
                        </tr>
            </c:forEach>
        </tbody>
    </table><br>
</petclinic:layout>