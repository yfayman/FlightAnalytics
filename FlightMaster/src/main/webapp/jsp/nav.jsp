<%-- 
    Document   : nav
    Created on : Oct 9, 2015, 2:54:18 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<nav class="navbar navbar-inverse navbar-fixed-top" id="topNavBar">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">FlightMaster</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="about">About</a></li>

            </ul>
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                <form class="navbar-form navbar-right" action="j_spring_security_check" method="POST">
                    <div class="form-group">
                        <input type="text" name="username" placeholder="Username" class="form-control" >
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" placeholder="Password" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-success">Sign in</button>
                    <button data-toggle="modal" data-target="#myModal" type="button" class="btn btn-success">Register</button>
                </form>
            </sec:authorize>

        </div>

    </div>
</nav>
<!-- Registration modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center">Register</h4>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal"  action="${pageContext.request.contextPath}/user/register" method="POST" role="form">

                    <div class="form-group">
                        <label for="reg-first-name" class="col-md-4 control-label">First Name:</label>
                        <div class="col-md-8">
                            <input type="text" name="reg-first-name" class="form-control reg-input" id="reg-first-name" placeholder="First Name" />
                        </div>
                        <div class="error col-md-offset-5" id="reg-first-name-error"></div>
                    </div>
                    <div class="form-group">
                        <label for="reg-last-name" class="col-md-4 control-label">Last Name:</label>
                        <div class="col-md-8">
                            <input type="text" name="reg-last-name" class="form-control reg-input" id="reg-last-name" placeholder="Last Name" />
                        </div>
                        <div class="error col-md-offset-5" id="reg-last-name-error"></div>
                    </div>
                    <div class="form-group">
                        <label for="reg-user-name" class="col-md-4 control-label">Username:</label>
                        <div class="col-md-8">
                            <input type="text" name="reg-user-name" class="form-control reg-input" id="reg-user-name" placeholder="Username" />
                        </div>
                        <div class="error col-md-offset-5" id="reg-user-name-error"></div>
                    </div>
                    <div class="form-group">
                        <label for="reg-email" class="col-md-4 control-label"> Email: </label>
                        <div class="col-md-8">
                            <input type="text" name="reg-email" class="form-control reg-input" id="reg-email" placeholder= "Email"/>
                        </div>
                        <div class="error col-md-offset-5" id="reg-email-error"></div>
                    </div>
                    <div class="form-group">
                        <label for="reg-password" class="col-md-4 control-label"> Password: </label>
                        <div class="col-md-8">
                            <input type="password" name="reg-password" class="form-control reg-input" id="reg-password" placeholder= "Password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="reg-confirmpassword" class="col-md-4 control-label"> Confirm Password: </label>
                        <div class="col-md-8">
                            <input type="password" name="reg-confirmpassword" class="form-control reg-input" id="reg-confirmpassword" placeholder= "Confirm Password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button id = registerUser type="submit"
                                    class="btn btn-primary">
                                Register
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div  id="password-error" class="col-md-offset-3 error">

                        </div>
                    </div>

                </form:form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
