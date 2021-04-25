<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="visits">
    <h2>Visits</h2>

    <table id="visitTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 200px;">Address</th>
            <th>City</th>
            <th style="width: 120px">Telephone</th>
            <th>Pets</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="visit">
            <tr>
                <td>
                    <spring:url value="/visits/new/{visitId}" var="visitUrl">
                        <spring:param name="visitId" value="${visit.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(visitUrl)}"><c:out value="${visit.date}"/></a>
                </td>
                <td>
                    <c:out value="${visit.description}"/>
                </td>              
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
