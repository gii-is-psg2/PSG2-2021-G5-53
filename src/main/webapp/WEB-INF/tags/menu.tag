<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true" description="Name of the active menu: home, owners, vets, adoption or error"%>

<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<spring:url value=" /" htmlEscape="true" />"><span></span></a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-navbar">
                <span class="sr-only">
                    <os-p>Toggle navigation</os-p>
                </span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
        </div>

        <div class="navbar-collapse collapse" id="main-navbar">
            <ul class="nav navbar-nav">
                <petclinic:menuItem active="${name eq 'owners'}" url="/owners/find" title="find owners">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    <span>Owners</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'vets'}" url="/vets" title="veterinarians">
                    <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                    <span>Vets</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'reservas'}" url="/reservas/new" title="petHotel">
                    <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
                    <span>Book</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'adoption'}" url="/adoption" title="Enter here if you want to adopt a pet">
                    <span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
                    <span>Adoption</span>
                </petclinic:menuItem>

            </ul>

            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="!isAuthenticated()">
                    <li><a href="<c:url value=" /login" />">Login</a></li>
                    <li><a href="<c:url value=" /users/new" />">Register</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
                            <strong>
                                <sec:authentication property="name" />
                            </strong> <span class="glyphicon glyphicon-chevron-down"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="navbar-login">
                                        <div class="col-lg-12">
                                            <p class="text-left">
                                                <a href="<c:url value="/logout" />" class="btn btn-primary btn-block btn-lg">Logout</a>
                                            </p>
                                        </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>