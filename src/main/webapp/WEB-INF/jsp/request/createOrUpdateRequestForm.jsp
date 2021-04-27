<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<petclinic:layout pageName="request">
    <h2>
        Are you sure?
    </h2>
    <form:form modelAttribute="pet" class="form-horizontal" id="add-request-form">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
             	<button class="btn btn-default" type="submit">YES</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
