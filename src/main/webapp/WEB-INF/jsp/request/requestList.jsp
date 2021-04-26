<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="requests">

    <h2>Adoption applications</h2><br>

    <table id="requestAdoption" class="table table-striped">

        <!-- Header table for pets on adoption -->
        <thead>
            <tr>
                <th style="text-align: center; width: 150px;">Pet name</th>
                <th style="text-align: center; width: 200px;">Info</th>
                <th style="text-align: center; width: 200px;">Application owner</th>
                <th style="text-align: center; width: 200px;">Pet owner</th>
                <th style="text-align: center; width: 200px;">Confirm</th>
            </tr>
        </thead>

        <!-- Display pets for adoption -->
        <tbody>
            <c:forEach items="${requestAdoption}" var="request">
                        <tr>
                            <td style="text-align: center;">
                                <c:out value="${request.pet}" />
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${request.applicationInfo}" />
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${request.applicationOwner.firstName}" />
                            </td>
                            <td style="text-align: center;">
	                       		<c:out value="${request.petOwner.firstName}" />
                       		</td>
                       		<td style="text-align: center;">
	                        	<spring:url value="/adoption/request/new/{petId}/{oldOwnerId}/{newOwnerId}" var="confirmApplicationURL">
	                       		<spring:param name="petId" value="${request.pet.id}"/>
	                       		<spring:param name="oldOwnerId" value="${request.petOwner.id}"/>
	                       		<spring:param name="newOwnerId" value="${request.applicationOwner.id}"/>
	                       		</spring:url>
	                       		<a href="${fn:escapeXml(confirmApplicationURL)}">Confirm</a>
                       		</td>
                        </tr>
            </c:forEach>
        </tbody>
    </table><br>
</petclinic:layout>