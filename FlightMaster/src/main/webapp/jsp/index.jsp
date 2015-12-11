<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Flight Analytics Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/plane-icon.png">

    </head>
    <body>
        <c:import url="nav.jsp"></c:import>
        <div class="container" id="aboutContainer">
            <div class="col-md-offset-1">
                <div class="col-md-10" style="text-align: center; font-size: 130%; line" >
                    <h2>Welcome to Flight Analytics</h2>
                    <hr>
                    <p >Have you ever sat on a plane wondering whether you paid more
                        than the person next to you? The dirty secret of the airline industry is that prices can
                        vary dramatically. Airlines use complex algorithms to maximize revenue per flight. 
                        <strong>When is the best time to buy? </strong> That is the question that Flight Analytics
                        will answer. All you have to do is tell Flight Analytics what flights you want to track. It
                        will then check the price of the route at specified intervals. No more checking prices daily!
                        Flight Analytics will handle it for you so you can focus on more important things.
                        
                    </p>
                </div>
            </div>
            
        </div>


            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/register.js"></script>

    </body>
</html>

