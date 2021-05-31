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
   <h2><fmt:message key="support"/></h2>

    <table id="supportTable" class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="telephone"/></th>
        </tr>
        </thead>
        <tbody>
                 <tr>
                     <td>
                        <c:out value="${developersInfoList.name}"/>
                     </td>

                     <td>
                        <c:out value="${developersInfoList.email}"/>
                     </td>

                     <td>
                         <c:out value="${developersInfoList.phone}"/>
                     </td>
                 </tr>
        </tbody>
    </table>
    
     <p><b><fmt:message key="iTopData"/></b></p>
    <p><fmt:message key="iTopURLText"/><a href="http://localhost/iTop"><fmt:message key="iTopURL"/></a></p>
</petclinic:layout>