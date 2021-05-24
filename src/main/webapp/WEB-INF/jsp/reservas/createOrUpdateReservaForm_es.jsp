<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<petclinic:layout_es pageName="reservas">
	<jsp:attribute name="customScript">
  		  <script>
            $(function() {
                $("#fechaInicio").datepicker({dateFormat: 'yy/mm/dd'});
                $("#fechaFin").datepicker({dateFormat: 'yy/mm/dd'});
            });
  		</script>
  	</jsp:attribute>

    <jsp:body>
    <h2>
        <c:if test="${reserva['new']}">Nueva </c:if> reserva
    </h2>
    <form:form modelAttribute="reserva" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Fecha inicio" name="fechaInicio"/>
            <petclinic:inputField label="Fecha fin" name="fechaFin"/>
            <petclinic:inputField label="Descripcion" name="descripcion"/>
         
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-default" type="submit">Reservar</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
   
</petclinic:layout_es>