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

        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">



    </head>
    <body class="light-primary-color">
        <c:import url="nav.jsp"></c:import>

            <div class="container " id="request-input-form-container">
                <div class="col-md-offset-2">

                    <div class ="col-md-8 " id="request-input-form" style="background-color:#03A9F4">
                        <div class ="row ">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="departingLoc" >From</label>
                                    <input type="text" class="form-control" id="departingLoc" name="origin" placeholder="Airport or City">
                                </div>
                                <div class="form-group">
                                    <label for="departingDate">Departure Date</label>
                                    <input type="text" class="form-control datepicker" id="departingDate"  placeholder="Date">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="arrivingLoc">To</label>
                                    <input type="text" class="form-control" id="arrivingLoc" placeholder="Airport or City">
                                </div>
                                <div class="form-group">
                                    <label for="arrivingDate">Return Date</label>
                                    <input type="text" class="form-control datepicker" id="arrivingDate" placeholder="Date">
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="max-stops">Max Stops</label>
                                    <select id="max-stops" class="form-control">
                                        <option value="0">non-stop</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="adult-passengers">Adults</label>
                                    <select id="adult-passengers" class="form-control">
                                        <option value="0">0</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="child-passengers">Children</label>
                                    <select id="child-passengers" class="form-control">
                                        <option value="0">0</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4">
                                <label for="num-queries">Queries</label>
                                <select id="max-stops" class="form-control">

                                    <option value="1">1</option>
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                    <option value="20">20</option>
                                    <option value="30">30</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                    <option value="80">80</option>
                                    <option value="100">100</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label for="interval">Query Interval</label>
                                <select id="interval" class="form-control">
                                    <option value="3">3 hours</option>
                                    <option value="6">6 hours</option>
                                    <option value="12">12 hours</option>
                                    <option value="23">24 hours</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <button type="button" id="submit-request" class="btn accent-color text-primary-color" style="margin-top: 15%;">Submit Request</button>
                            </div>
                        </div>

                    </div>
                </div>

            </div>

            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/register.js"></script>
        <script src="${pageContext.request.contextPath}/js/request.js"></script>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script>
            $(function () {
                $(".datepicker").datepicker();
            });
        </script>

    </body>
</html>

