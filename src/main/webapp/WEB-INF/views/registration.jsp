<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: KSOFTNOUT
  Date: 03.11.2018
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


<link rel="stylesheet" type="text/css" href="resources/css/common.css">


<link rel="stylesheet" type="text/css" href="resources/css/headers.css">



<nav class="navbar navbar-expand-lg navbar-light bg-dark" id="navbar">

    <div class="first">
        <a href="index">Login</a>
    </div>

</nav>

<body>


<div class="container">

    <form method="POST" modelAttribute="userForm" class="form-signin" action="registration">
        <h2 class="form-signin-heading" style="color: white">Create your account</h2>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <input  pattern ="^[a-zA-Z0-9_]+$" title="English letter,number and _" minlength="8" maxlength="32" type="text" required name="username"  path="username" class="form-control" placeholder="Username" autofocus="true">
            </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <input minlength="2" maxlength="90" required type="email" name="email"  path="email" class="form-control" placeholder="Email"
                       autofocus="true">
            </div>
            <h6 id="message1">password will be send to your email</h6>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input minlength="2" type="text" title="English letter,number and -" pattern ="^[a-zA-Z0-9-]+$" maxlength="90" name="name" required path="name" class="form-control" placeholder="Name"
                   autofocus="true">
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input minlength="2" maxlength=" 90" title="English letter,number and -"  pattern ="^[a-zA-Z0-9-]+$"  type="text" required name="surname"  path="surname" class="form-control" placeholder="Surname"
                   autofocus="true">
        </div>


        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input minlength="2"  type="text" name="phone" required  pattern="^\+375 \((17|29|33|44)\) [0-9]{7}$" title="+375 (XX) XXXXXXX" path="phone" class="form-control" placeholder="Phone(+375 (XX) XXXXXXX)"
                   autofocus="true">
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        <h3 id = "message">${message}</h3>
    </form>

</div>
</body>



</html>
