<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/custom-styles.css" rel="stylesheet"/>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>

    <h2 align="center">
        <fmt:message key="app.header"/>
    </h2>
</head>

<body>
    <div class="topnav">
        <a class="active" href="#home">Home</a>
        <a href="#about">About</a>
        <a href="#contact">Contact</a>
        <button id="admin" type="button" class="btn btn-default btn-primary">
            Show Admins
        </button>

        <button id="user" type="button" class="btn btn-default btn-primary">
            Show Users
        </button>

        <div class="login-container">
            <form id="loginForm" action="login" method="POST">
                <input type="text" placeholder="Username" name="username">
                <input type="password" placeholder="Password" name="password">
                <button id="login" type="button">Login</button>
            </form>
        </div>
    </div>
    
    <div id="adminSection">
        <h1 id="header"/>
    </div>

    <input id="jwtToken" type="hidden"/>

    <script type="text/javascript">
        $(document).ready(function(){
            $('#login').on('click', function(){
                var authRequest = {};
                authRequest['username'] = $('input[name="username"]').val();
                authRequest['password'] = $('input[name="password"]').val();

                $.ajax({
                    type: "POST",
                    url: '/login',
                    data: JSON.stringify(authRequest),
                    contentType : "application/json",
                    beforeSend: function(){
                    },
                    success: function(data){
                        $('#loginForm').remove();

                        $('#jwtToken').val(data.jwt);
                    }
                }).always(function(){
                })
            });

            $('#admin').on('click', function(){
                $.ajax({
                    type: "GET",
                    url: '/admin',
                    beforeSend: function(){
                    },
                    headers: {
                        "Authorization": "Bearer " + $('#jwtToken').val()
                    },
                    async: false,
                    success: function(data){
                        $('#adminSection').find('#header').html(data.status);
                    }
                }).always(function(){
                })
            });
        });
    </script>
    </body>

</html>
