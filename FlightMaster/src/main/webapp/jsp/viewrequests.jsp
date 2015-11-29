<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>View Your Requests</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/c3.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/plane-icon.png">

    </head>
    <body class="light-primary-color">
        <c:import url="nav.jsp"></c:import>
            <div class="container " id="request-input-form-container">



            <c:if test="${not empty requests}" >
                <div class="row">
                    <div class="col-md-offset-2">
                        <div class="btn-group col-md-8" role="group" aria-label="Requests" id="request-container">
                            <c:forEach items="${requests}" var="request" >
                                <button type="button" class="btn request-button" id="request-${request.requestId}">${request.origin} to ${request.destination}</button>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-offset-2">
                        <div class="col-md-8" id="chart">
                            
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty requests}">
                <h3>You have no requests</h3>
            </c:if>


        </div>

        <!-- Placed at the end of the document so the pages load faster -->

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <!--        <script src="//code.jquery.com/jquery-1.10.2.js"></script>-->
        <script src="${pageContext.request.contextPath}/js/d3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/c3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/chart.js"></script>
    </body>
</html>