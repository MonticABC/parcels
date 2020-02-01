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
<link rel="stylesheet" type="text/css" href="resources/css/User.css">
<body>



<nav class="navbar navbar-expand-lg navbar-light bg-light" id="navbar">

    <div class="start">
        <a href="administratorDiscount">Discount</a>
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
    $(document).ready( function () {
        $('#myTable').DataTable();
        $('#table').DataTable();
    } );

</script>

<div class="users">
<form action="administratorUser" method="post">
    <table class="table table-hover table-bordered"  id="table">
        <thead>
        <tr class="bg-primary" >
            <th>Username</th>
            <th>Email</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Phone</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items= "${users}" var="user" >
            <tr id ="${user.getId()}" onclick="myFunction(${user.getId()})">
                <td>${user.getUsername()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getName()}</td>
                <td>${user.getSurname()}</td>
                <td>${user.getPhone()}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p></p>
    <input type = "submit" value="delete" name="delete" class="btn btn-primary">
    <input type = "submit" value="update Password" name="update" class="btn btn-primary">
    <input type="submit" value="check Parcels" name="parcels" class="btn btn-primary">
<p></p>
    <a href="#updateUser" onclick="func()" class="btn btn-primary" data-toggle="modal" >update User</a>
    <a href="#updateUser" onclick="func1()" class="btn btn-primary" data-toggle="modal">add User</a>
    <input type="submit" value="check Orders" name="orders" class="btn btn-primary">

    <input id="abc" name="command" type="hidden" value="">
    <p><h3 id = "message">${message}</h3></p>
</form>
<p></p>
    <p></p>
</div>
<c:forEach items= "${orders}" var="order">
    <h3>Total price ${order.getTotalpPrice()}</h3>
    <table class="table-bordered" id="tableFont"  >

        <thead>
        <tr  class="bg-primary">
            <th width="70px"><b> date</b></th>
            <th  ><b> CountrySender </b></th>
            <th  ><b> Country Recipient </b></th>
            <th ><b> Weight(kg) </b></th>
            <th  ><b> Tarif </b></th>
            <th  ><b> Price 0.1 kg</b></th>
            <th width="70px"><b>Delivery time(day)</b></th>
            <th ><b>Express</b></th>
            <th ><b>Price</b></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items= "${order.getParcelsById()}" var="parcel" >
            <tr>
                <td>${parcel.getDateOfSend()}</td>
                <td>${parcel.getCountryByCountrySender().getCountry()}</td>
                <td>${parcel.getCountryByCountryRecipient().getCountry()}</td>
                <td>${parcel.getWeight()}</td>
                <td>${parcel.getTarifByTarifId().getName()}</td>
                <td>${parcel.getTarifByTarifId().getPrice()}</td>
                <td>${parcel.getDeliveryTime()}</td>
                <td>${parcel.getExpress()}</td>
                <td>${parcel.getTotalPrice()}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <p></p>
</c:forEach>



<div class="parcel" >

<c:choose>
    <c:when test="${parcels.size()!=0 && parcels!=null}">

    <table class="table-bordered" id="myTable" >

        <thead>
        <tr  class="bg-primary">
            <th width="70px"><b> date</b></th>
            <th  ><b> CountrySender </b></th>
            <th  ><b> Country Recipient </b></th>
            <th ><b> Weight(kg) </b></th>
            <th  ><b> Tarif </b></th>
            <th  ><b> Price 0.1 kg</b></th>
            <th width="70px"><b>Delivery time(day)</b></th>
            <th ><b>Express</b></th>
            <th ><b>Price</b></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items= "${parcels}" var="parcel" >
        <tr onclick="Update(${userIdParcel},${parcel.getWeight()},${parcel.getId()},${parcel.getCountryByCountrySender().getId()},${parcel.getCountryByCountryRecipient().getId()},${parcel.getExpress()})">
            <td id="${parcel.getId()}">${parcel.getDateOfSend()}</td>
            <td>${parcel.getCountryByCountrySender().getCountry()}</td>
            <td>${parcel.getCountryByCountryRecipient().getCountry()}</td>
            <td>${parcel.getWeight()}</td>
            <td>${parcel.getTarifByTarifId().getName()}</td>
            <td>${parcel.getTarifByTarifId().getPrice()}</td>
            <td>${parcel.getDeliveryTime()}</td>
            <td>${parcel.getExpress()}</td>
            <td>${parcel.getTotalPrice()}</td>
        </tr>
        </c:forEach>
            </tbody>
            </table>
        <p><a href="#update"  class="btn btn-primary"  data-toggle="modal" >update parcel</a></p>

    </c:when>
</c:choose>
</div>
<script>


    function func1()
    {
        document.getElementById("username").value = "";
        document.getElementById("email").value = "";
        document.getElementById("name").value = "";
        document.getElementById("surname").value = "";
        document.getElementById("phone").value = "";
        document.getElementById("id").value = 0;
        document.getElementById("radio").hidden = "";

    }


    function func() {
        var id=document.getElementById("id").value;
        if(id!="")
        {
            document.getElementById("radio").hidden = "hidden";
        }
        else {
            document.getElementById("radio").hidden = "";
            document.getElementById("id").value = 0;
        }


    }

    function myFunction(id) {
        document.getElementById("abc").value = id;
        var myTable = document.getElementById(id);
        document.getElementById("username").value = myTable.cells[0].innerHTML;
        document.getElementById("email").value = myTable.cells[1].innerHTML;
        document.getElementById("name").value = myTable.cells[2].innerHTML;
        document.getElementById("surname").value = myTable.cells[3].innerHTML;
        document.getElementById("phone").value = myTable.cells[4].innerHTML;
        document.getElementById("id").value = id;
        document.getElementById("radio").hidden = "hidden";
    }

    function Update(idUser,weight,id,send,rec,exp) {


        document.getElementById("weight").value = weight;

        document.getElementById("date").value = document.getElementById(id).innerText ;

        document.getElementById("send").selectedIndex= send-1;


        document.getElementById("rec").selectedIndex= rec-1;

        var x = exp.toString();
        if(x === "true")
        {
            document.getElementById("express").checked = true;
        }

        document.getElementById("ab").value = id;

        document.getElementById("userId").value = idUser;

    }
</script>

<div class="container">

    <div id="update" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Parcel update</h4>
                </div>
                <div class="modal-body">
                    <form method = "POST" action = "administratorUpdateParcel">

                        <div class="form-group">
                            <label for="send"  class = "labels">Country sender</label>
                            <select name="countrySender" required class="custom-select mr-sm-2" id ="send" class ="inputs">
                                <c:forEach items="${countries}" var="name" >
                                    <option value="${name.getId()}"  ${name.getId() == parcelUp.getCountrySender()?'selected="selected"':''} >${name.getCountry()}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="rec" class = "labels" >Country recipient</label>
                            <select name="countryRecipient" required class="custom-select mr-sm-2"  id = "rec" class = "inputs">
                                <c:forEach items="${countries}" var="name1" >
                                    <option value="${name1.getId()}"  ${name1.getId() == parcelUp.getCountryRecipient()?'selected="selected"':''} >${name1.getCountry()}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="weight" class = "labels" >Weight(kg)</label>
                            <td><input name = "weightS" class = "inputs" type = "number" required step="0.1" min="0" max="15" value="${parcelUp.getWeight()}" id="weight"></td>
                        </div>

                        <div class="form-group">
                            <label for="date" class = "labels" >Date</label>
                            <input type="date" class = "inputs" name="date" required value="${parcelUp.getDateOfSend()}" min="${currentDate}" id="date"/>
                        </div>


                        <div class="form-group">
                            <label><input name = "express" id ="express" type = "checkbox" ${parcelUp.getExpress()=='true'?'checked="checked"':''}> Express</label>
                        </div>

                        <input type="submit" value="save" class="btn btn-primary">
                        <input type="hidden" id="ab" value="" name="id">
                        <input type="hidden" id="userId" value="" name="userId">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



<div class="container">

    <div id="updateUser" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">User actions</h4>
                </div>
                <div class="modal-body">
                    <form action="administratorUpdateUser" method="post" modelAttribute="user" >
                        <input type="hidden" path="id" id="id" name="id" value="">
                        <div class="form-group row">
                            <label for="example-text-input" class="col-2 col-form-label">Username</label>
                            <div class="col-10">
                                <input required title="English letter,number and _" class="form-control"  pattern ="^[a-zA-Z0-9_]+$" minlength="8" maxlength="32" value ="" id = "username" name="username" path="username" value="${user.getUsername()}" type="text" value="Artisanal kale" id="example-text-input">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="example-text-input6" class="col-2 col-form-label">Email</label>
                            <div class="col-10">
                                <input required  class="form-control" value ="" name="email" id ="email" path="email" value="${user.getEmail()}" type="email" value="Artisanal kale" id="example-text-input6">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="example-text-input2" class="col-2 col-form-label">Name</label>
                            <div class="col-10">
                                <input required  class="form-control"  pattern ="^[a-zA-Z0-9-]+$" minlength="2" maxlength="90" value ="" id="name" name="name" path="name" value="${user.getName()}" type="text" value="Artisanal kale" id="example-text-input2">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="example-text-input3" class="col-2 col-form-label">Surname</label>
                            <div class="col-10">
                                <input required  title="English letter,number and -" class="form-control" value ="" id="surname"  pattern ="^[a-zA-Z0-9-]+$" minlength="2" maxlength="90" name="surname" path="surname" value="${user.getSurname()}" type="text" value="Artisanal kale" id="example-text-input3">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="example-text-input4" class="col-2 col-form-label">Phone</label>
                            <div class="col-10">
                                <input required   class="form-control" value ="" id ="phone" name="phone" path="phone" value="${user.getPhone()}" pattern="^\+375 \((17|29|33|44)\) [0-9]{7}$" placeholder="+375 (XX) XXXXXXX" title="+375 (XX) XXXXXXX" type="text" value="Artisanal kale" id="example-text-input4">
                            </div>
                        </div>
                        <div id="radio"  >
                            <input type="radio" name="radio"> Admin
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
