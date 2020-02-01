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
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light" id="navbar">

    <div class="start">
        <a href="administratorDiscount">Discount</a>
    </div>



    <div class="first">
        <a href="administratorCountry">Country</a>
    </div>


    <div class="second">
        <a href="administratorUser">User</a>
    </div>

    <div class="third">
        <a href="#addModal" data-toggle="modal">Personal information</a>
    </div>


    <form method="POST" action="logout" class="logout">
        <input type ="submit" name="logout" class="btn btn-outline-danger" value="logout" >
    </form>
</nav>

<script>
    function myFunction(x) {
        document.getElementById("abc").value = x.id;
    }
</script>

<div class="tarifs" style="width: 450px">
    <p></p>

    <table class="table table-hover table-bordered"  id="myTable">

        <thead>
        <tr class="bg-primary">
            <th>Tarif</th>
            <th>Price of 0.1 kg</th>
            <th>Delivery time</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items= "${tarifs}" var="tarif">
            <tr id="${tarif.getId()}" onclick="myFunction(this)">
                <td>${tarif.getName()}</td>
                <td>${tarif.getPrice()}</td>
                <td>${tarif.getDeliveryTime()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="administratorTarif" method="post">
    <input type = "submit" value="update price" name="update price" class="btn btn-outline-success">
    <input type = "number" name = "tarif" step="0.1" required  style="width: 100px;" min="0.1" max="5" placeholder="new price">
    <input id="abc" name="tarifId" type="hidden" value="">
    <h2 id = "message">${message}</h2>
</form>
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
                    <h4 class="modal-title">Personal information</h4>
                </div>
                <div class="modal-body">
                    <form action="ordersinformation" method="post" modelAttribute="user" >
                        <div class="form-group row">
                            <label for="example-text-input" class="col-2 col-form-label">Username</label>
                            <div class="col-10">
                                <input class="form-control"  pattern ="^[a-zA-Z0-9_]+$" minlength="8" maxlength="32" name="username" path="username" value="${user.getUsername()}"type="text" value="Artisanal kale" id="example-text-input">
                            </div>
                        </div>


                        <div class="form-group row">
                            <label for="example-text-input6" class="col-2 col-form-label">Email</label>
                            <div class="col-10">
                                <input class="form-control" name="email" path="email" value="${user.getEmail()}" type="email" value="Artisanal kale" id="example-text-input6">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="example-text-input2" class="col-2 col-form-label">Name</label>
                            <div class="col-10">
                                <input class="form-control"  pattern ="^[a-zA-Z0-9-]+$" minlength="2" maxlength="90" name="name" path="name" value="${user.getName()}" type="text" value="Artisanal kale" id="example-text-input2">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="example-text-input3" class="col-2 col-form-label">Surname</label>
                            <div class="col-10">
                                <input class="form-control" name="surname"  pattern ="^[a-zA-Z0-9-]+$" minlength="2" maxlength="90" path="surname" value="${user.getSurname()}" type="text" value="Artisanal kale" id="example-text-input3">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="example-text-input4" class="col-2 col-form-label">Phone</label>
                            <div class="col-10">
                                <input class="form-control" name="phone" path="phone" value="${user.getPhone()}" pattern="^\+375 \((17|29|33|44)\) [0-9]{7}$" type="text" value="Artisanal kale" id="example-text-input4">
                            </div>
                        </div>
                        <input type="submit" class="btn btn-success" name="save" value="save">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
