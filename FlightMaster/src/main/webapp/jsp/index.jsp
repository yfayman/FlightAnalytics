<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>FlightMaster Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <c:import url="nav.jsp"></c:import>
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3">
                    <div class="col-md-6" id="login">
                        <form action="signin" method="POST" class="form-horizontal">
                            <h1 class="formtext">Log In</h1>
                            <div class="form-group">
                                <label for="userName1" class="control-label col-md-4">Username:</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" id="userName1" placeholder="Username" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password1" class="control-label col-md-4">Password</label>
                                <div class="col-md-6">
                                    <input type="password" class="form-control" id="password1" placeholder="Password" >
                                </div>
                            </div>
                            <div class="col-md-offset-4 col-md-6">
                                <label>
                                    <input type="checkbox" id="rememberUser"> Remember me
                                </label>
                            </div>
                            <div class="col-md-offset-4 col-md-6">
                                <button type="submit" class="btn btn-success">Sign in</button>
                            </div>   
                            <div class="col-md-offset-4 col-md-6" id="regLink1">
                                <p>Not a member?<a href="register" >Register</a></p>
                            </div>
                        </form>

                    </div>


                </div>
                <div class="col-md-4">

                </div>

            </div>

        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

