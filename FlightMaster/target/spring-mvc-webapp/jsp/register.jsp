<%-- 
    Document   : register
    Created on : Oct 9, 2015, 2:52:15 PM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
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
                    <div class="col-md-offset-2">
                        <div class="col-md-8" id="login">
                            <form class="form-horizontal" id="signup" method="POST" >

                                <h1 class="formtext">Register</h1>
                                <div class="form-group">
                                    <label for="userName" class="control-label col-md-4">Full name:</label> 
                                    <div class="col-md-6">
                                        <input type="text" class="form-control" name="userName" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="loginName" class="control-label col-md-4">Login Id:</label> 
                                    <div class="col-md-6">
                                        <input type="text" class="form-control" name="loginName" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password1" class="control-label col-md-4">Enter Password:</label> 
                                    <div class="col-md-6">
                                        <input type="password" class="form-control" name="password1" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password2" class="control-label col-md-4">Confirm Password:</label> 
                                    <div class="col-md-6">
                                        <input type="password" class="form-control" name="password2" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class=" col-md-offset-4">
                                        <div class="col-md-6">
                                            <button type="submit" class="btn btn-lg btn-success">Register</button>
                                        </div>
                                    </div>
                                </div>

                        </div>

                        </form>
                    </div>
                </div>
            </div>

            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
