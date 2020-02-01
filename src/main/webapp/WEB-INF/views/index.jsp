<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


<link rel="stylesheet" type="text/css" href="resources/css/common.css">

<link rel="stylesheet" type="text/css" href="resources/css/headers.css">



<nav class="navbar navbar-expand-lg navbar-light bg-dark" id="navbar">

 <div class="first">
  <a href="registration">Registration</a>
 </div>

 <div class="second">
  <a href="parcel">Parcel</a>
 </div>

</nav>
<head>
 <title>login</title>
</head>

<body>


<div class="container">

 <form method="POST" modelAttribute="user" class="form-signin" action ="Login">
  <h2 class="form-signin-heading" style="color: white">Login</h2>

  <div class="form-group ${status.error ? 'has-error' : ''}">
   <input type="text" name="username" required  path="username" pattern ="^[a-zA-Z0-9_]+$" title="English letter,number and _" minlength="8" maxlength="32" class="form-control" placeholder="Username"  autofocus="true"></input>
  </div>

  <div class="form-group ${status.error ? 'has-error' : ''}">
   <input type="password" name="password" required path="password" class="form-control" title="English letter,number and _" pattern ="^[a-zA-Z0-9_]+$" minlength="8" maxlength="32" placeholder="Password"></input>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  <h2 id = "message">${message}</h2>
 </form>

</div>
</body>
</html>