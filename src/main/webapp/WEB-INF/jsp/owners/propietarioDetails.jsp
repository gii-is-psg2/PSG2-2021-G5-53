<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout_es pageName="owners">

    <h2>Informacion Propietario</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${owner.firstName} ${owner.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Direccion</th>
            <td><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th>Ciudad</th>
            <td><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr>
    </table>

    <spring:url value="{ownerId}/editar" var="editUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar propietario</a>
    
    <spring:url value="{ownerId}/eliminar" var="eliminaUrl">
    	<spring:param name="ownerId" value="${owner.id}"/>
	</spring:url>
    <a href="${fn:escapeXml(eliminaUrl)}" class="btn btn-default">Eliminar propietario</a>

    <spring:url value="{ownerId}/mascotas/nueva" var="addUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Añadir nueva mascota</a>

    <br/>
    <br/>
    <br/>
    <h2>Animales y visitas</h2>

    <table class="table table-striped">
        <c:forEach var="pet" items="${owner.pets}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Nombre</dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt>Fecha nacimiento</dt>
                        <dd><petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Tipo</dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Fecha visita</th>
                            <th>Descripcion</th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${pet.visits}">
                            <tr>
                                <td><petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${visit.description}"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <spring:url value="/propietarios/{ownerId}/mascotas/{petId}/editar" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(petUrl)}">Editar mascota</a>
                            </td>
                            <td>
                                <spring:url value="/propietarios/{ownerId}/mascotas/{petId}/visitas/nueva" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}">Añadir visita</a>
                            </td> 
                        </tr>
                         <td>
                            <spring:url value="/owners/{ownerId}/pets/{petId}/delete" var="petRemoveUrl">
                                <spring:param name="ownerId" value="${owner.id}"/>
                                <spring:param name="petId" value="${pet.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(petRemoveUrl)}">Eliminar mascota</a>
                        </td> 
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>

</petclinic:layout_es>
