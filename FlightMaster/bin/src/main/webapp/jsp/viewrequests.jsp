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
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/plane-icon.png">

    </head>
    <body>
        <c:import url="nav.jsp"></c:import>
            <div class="container" id="request-view">



            <c:if test="${not empty requests}" >
                <div class="row">
                    <div class="col-md-offset-1">
                        <div class="btn-group col-md-10" role="group" aria-label="Requests" id="request-container">
                            <div class="row">
                                
                                    <div style="text-align:center;">
                                        <c:forEach items="${requests}" var="request" >
                                            <a href="#" class="btn request-button" id="request-${request.requestId}">${request.origin} to ${request.destination}</a>
                                        </c:forEach>
                                    </div>
                                
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <h3 class="data-header">Chart</h3>
                                    <div id="chart">

                                    </div>
                                </div>
                                <div class="col-md-6" id="stats">
                                    <h3 class="data-header">Stats</h3>
                                    <div id="stats">

                                    </div>
                                </div>
                            </div>
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