<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="support">
   <h2> Support </h2>

    <table id="supportTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
                <th style="width: 200px;">Email</th>
                <th style="width: 200px;">Telephone</th>
                
        </tr>
        </thead>
        <tbody>
                 <c:forEach items="${listaDatos}" var="personInfo">
                 <tr>
                     <td>
                        <c:out value="${personInfo.name}"/>
                     </td>

                     <td>
                        <c:out value="${personInfo.email}"/>
                     </td>

                     <td>
                         <c:out value="${personInfo.phone}"/>
                     </td>
                 </tr>
            </c:forEach>
        </tbody>
    </table>
    
     <p><b>iTop</b></p>
    <p><a href="http://localhost/Itop/web/pages/UI.php">iTopURL</a></p>
</petclinic:layout>