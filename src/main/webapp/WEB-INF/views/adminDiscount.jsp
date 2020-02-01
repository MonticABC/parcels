<%--
  Created by IntelliJ IDEA.
  User: KSOFTNOUT
  Date: 01.12.2018
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Discount</title>
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
        <a href="administratorUser">User</a>
    </div>

    <div class="first">
        <a href="administratorTarif">Tarif</a>
    </div>


    <div class="second">
        <a href="administratorCountry">Country</a>
    </div>



    <form method="POST" action="logout" class="logout">
        <input type ="submit" name="logout" class="btn btn-outline-danger" value="logout" >
    </form>
</nav>


<script>
    function myFunction(x) {
        document.getElementById("hid").value = x.id;
    }
</script>

<script>
    $(document).ready( function () {
        $('#myTable').DataTable();
    } );

</script>
<div class="tarifs" style="width: 500px">
<table class="table table-bordered table-hover" id="myTable">
   <thead>
    <tr class="bg-primary">
        <th>Month</th>
        <th>Name</th>
        <th>Discount</th>
    </tr>
   </thead>
    <tbody>
    <c:forEach items= "${discounts}" var="discount">
        <tr id="${discount.getId()}" onclick="myFunction(this)">
            <td>${discount.getMonth()}</td>
            <td>${discount.getNameT()}</td>
            <td>${discount.getDis()}</td>
        </tr>

    </c:forEach>
    </tbody>
</table>
</div>

<form action="adminDeleteDiscount" method="post">
    <input type="hidden" name="hid" id = "hid">
    <input type="submit" class=" btn btn-primary" value="Delete" name="delete">
    <a href="#addModal" class="btn btn-primary" data-toggle="modal" >add discount</a>
</form>


<div class="container">

    <div id="addModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">

                    <h4 class="modal-title">Discount add</h4>
                </div>
                <div class="modal-body">
                    <form method = "POST" action = "administratorAddDiscount" >

                        <div class="col-auto my-1">
                            <label class="mr-sm-3" for="inlineFormCustomSelect2">Region sender</label>
                            <select name="regionS" path ="regionS" class="custom-select mr-sm-2" id="inlineFormCustomSelect2" >
                                <option selected>Europe</option>
                                <option >Africa</option>
                                <option >Asia</option>
                                <option >North America</option>
                                <option >South America</option>
                                <option >Australia and Oceania</option>
                            </select>
                        </div>



                        <div class="col-auto my-1">
                            <label class="mr-sm-3" for="inlineFormCustomSelect">Region recipient</label>
                            <select name="regionR" path ="regionR" class="custom-select mr-sm-2" id="inlineFormCustomSelect" >
                                <option selected>Europe</option>
                                <option >Africa</option>
                                <option >Asia</option>
                                <option >North America</option>
                                <option >South America</option>
                                <option >Australia and Oceania</option>
                            </select>
                        </div>

                        <div class="col-auto my-1">
                            <label class="mr-sm-3" for="inlineFormCustomSelect1">Month</label>
                            <select name="month" path ="month" class="custom-select mr-sm-2" id="inlineFormCustomSelect1" >
                                <option selected value="1">Junuary</option>
                                <option value="2">February</option>
                                <option value="3">March</option>
                                <option value = "4">April</option>
                                <option value = "5">May</option>
                                <option value = "6">June</option>
                                <option value = "7">July</option>
                                <option value = "8">August</option>
                                <option value = "9">September</option>
                                <option value = "10">October</option>
                                <option value = "11">November</option>
                                <option value = "12">December</option>
                            </select>
                        </div>

                        <div class="col-auto my-1">
                            <label class="mr-sm-3" for="dis">Discount</label>
                            <input id="dis" type="number" required min="1" max="99" placeholder="%" name="discount">

                            <button class="btn btn-block" style="width: 100%; background-color: #5BDD5B;
                        color: white;" name="add">add</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<h2 id = "message">${message}</h2>
</body>
</html>
