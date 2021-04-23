<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cause">

    <h2>Cause Info</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><c:out value="${cause.name}"/></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${cause.description}"/></td>
        </tr>
        <tr>
            <th>BudgetTarget</th>
            <td><c:out value="${cause.budgetTarget}"/></td>
        </tr>
        <tr>
            <th>Organization</th>
            <td><c:out value="${cause.organization}"/></td>
        </tr>
    </table>




    <br/>
    <h2>Donations</h2>
    
<table id="dontaionsTable" class="table table-striped">
		<thead>
			<tr>
				<th>Name </th>
				<th>Date </th>
				<th>Amount </th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${donations}" var="donation">
				<tr>
					<td><c:out value="${donation.client}" /></td>
					<td><c:out value="${donation.date}" /></td>
					<td><c:out value="${donation.amount}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</petclinic:layout>
    