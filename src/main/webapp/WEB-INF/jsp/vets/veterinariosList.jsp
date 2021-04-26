<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<petclinic:layout_es pageName="vets">
    <h2>Veterinarios</h2>

    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
             <th>Actualizar</th>
          <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vets.vetList}" var="vet">
            <tr>
                <td>
                    <c:out value="${vet.firstName} ${vet.lastName}"/>
                </td>
               <!--  <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>

                </td>--> 
                
               <td><a href="/veterinarios/${vet.id}/editar">Actualizar</a> </td>

                </td>
                <td>
                    <spring:url value="veterinarios/{vetId}/eliminar" var="vetUrlRemove">
                   		<spring:param name="vetId" value="${vet.id}"/>
                    </spring:url>
                   		<a href="${fn:escapeXml(vetUrlRemove)}">Eliminar Veterinario</a>
                </td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>
	 <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/veterinarios/nuevo" htmlEscape="true"/>'>Añadir veterinario</a>
	</sec:authorize>
    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/veterinarios.xml" htmlEscape="true" />">View as XML</a>
            </td>            
        </tr>
    </table>
</petclinic:layout_es>
