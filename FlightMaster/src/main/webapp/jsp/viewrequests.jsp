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

        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

        <script type="text/javascript"
          src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart']
            }]
          }"></script>


    </head>
    <body class="light-primary-color">
        <c:import url="nav.jsp"></c:import>
            <div class="container " id="request-input-form-container">
                <div class="col-md-offset-2">

                    <div>

                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
                            <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
                            <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a></li>
                            <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a></li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="home">...</div>
                            <div role="tabpanel" class="tab-pane" id="profile">...</div>
                            <div role="tabpanel" class="tab-pane" id="messages">...</div>
                            <div role="tabpanel" class="tab-pane" id="settings">...</div>
                        </div>

                    </div>
                    <div id="chart_div">
                        
                    </div>


                </div>

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