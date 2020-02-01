
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: KSOFTNOUT
  Date: 14.11.2018
  Time: 12:50
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">


<link rel="stylesheet" type="text/css" href="resources/css/headers.css">

<link rel="stylesheet" type="text/css" href="resources/css/Country.css">
<body>



<nav class="navbar navbar-expand-lg navbar-light bg-light" id="navbar">


    <div class="start">
        <a href="administratorDiscount">Discount</a>
    </div>


    <div class="first">
    <a href="administratorTarif">Tarif</a>
    </div>


    <div class="second">
    <a href="administratorUser">User</a>
    </div>

    <div class="third">
        <a href="#updatePassword" data-toggle="modal">update Password</a>
    </div>

    <form method="POST" action="logout"  class="logout">
        <input type ="submit" name="logout" class="btn btn-outline-danger" value="logout" >
    </form>
</nav>

<div class="container">
    <div id="updatePassword" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 250px;">
                <div class="modal-header">
                    <h4 class="modal-title">update password</h4>
                </div>
                <div class="modal-body">
                    <form action="parcelupdatePassword" method="post">
                        <input type="password" name="password" required  pattern ="^[a-zA-Z0-9_]+$" minlength="8" maxlength="32" placeholder="Password">
                        <p></p><input  title="English letter,number and _" type="password" name="confirmPassword" required  pattern ="^[a-zA-Z0-9_]+$" minlength="8" maxlength="32" placeholder="Confirm Password">
                        <p></p><input  title="English letter,number and _" type="submit" name = "button" value="update Password" class="btn btn-success">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="countries">
<p></p>
<table class="table table-bordered"  id="myTable">
<thead>
    <tr class="bg-primary">
        <th>Country</th>
        <th>Region</th>
    </tr>

</thead>
    <tbody>
    <c:forEach items= "${countries}" var="country">
        <tr>
            <td>${country.getCountry()}</td>
            <td>${country.getRegion()}</td>
        </tr>

    </c:forEach>
    </tbody>
</table>

    <p><a href="#addModal" class="btn btn-primary" data-toggle="modal" >add Country</a></p>

</div>
<script>
    $(document).ready( function () {
        $('#myTable').DataTable();
    } );

</script>

<div class="container">

    <div id="addModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Country add</h4>
                </div>
                <div class="modal-body">
                    <form method = "POST" action = "administratorUpdateCountry" modelAttribute="countryO">
                        <div class="col-auto my-1">
                        <label class="mr-sm-3" for="Countries">Country</label>
                        <input  name = "country"   pattern ="^[a-zA-Z0-9-]+$" minlength="4" maxlength="48" path = "country" required="required" id="Countries" class="form-control" pattern="[a-zA-Z-]+"/>
                        </div>
                        <div class="col-auto my-1">
                            <label class="mr-sm-3" for="inlineFormCustomSelect">Region</label>
                            <select name="region" path ="region" class="custom-select mr-sm-2" id="inlineFormCustomSelect" >
                                    <option selected>Europe</option>
                                    <option >Africa</option>
                                    <option >Asia</option>
                                    <option >North America</option>
                                    <option >South America</option>
                                    <option >Australia and Oceania</option>
                            </select>
                        </div>
                        <button id="button" class="btn btn-block" name="add">add</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>



<h2 id = "message">${message}</h2>

</body>
</html>
