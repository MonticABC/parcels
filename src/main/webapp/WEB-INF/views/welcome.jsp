<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: KSOFTNOUT
  Date: 06.11.2018
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">


<link rel="stylesheet" type="text/css" href="resources/css/parcel.css">
<link rel="stylesheet" type="text/css" href="resources/css/headers.css">


<body>


<nav class="navbar navbar-expand-lg navbar-light bg-light" id="navbar">


    <div class="second">
        <a href="#addModal" data-toggle="modal">Discounts</a>
    </div>



    <c:choose>
        <c:when test="${user.getRole()==1}">
            <div class="first">
                <a href="orders">My orders</a>

            </div>

            <div class="third">
                <a href="#updatePassword" data-toggle="modal">update Password</a>
            </div>

            <form method="POST" action="logout"  class="logout">
                <input type ="submit" name="logout" class="btn btn-outline-danger" value="logout" >
            </form>

        </c:when>

        <c:otherwise>
            <form method="POST" action="logout"  class="logout">
                <input type ="submit" name="login" class="btn btn-outline-danger" value="login" >
            </form>
        </c:otherwise>
    </c:choose>



</nav>


<div class="inputForm">

<form method="POST" action="parcel/Parcels">

        <div class="form-group">
            <label for="sender" class="labels">Country sender</label>
            <select name="countrySender"  required class="custom-select mr-sm-2" id ="sender" >
                <c:forEach items="${countries}" var="name" >
                    <option value="${name.getId()}"  ${name.getId() == parcelUp.getCountrySender()?'selected="selected"':''} >${name.getCountry()}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label class="labels" for="recipient"  >Country recipient</label>
                <select name="countryRecipient" required class="custom-select mr-sm-2" id = "recipient" >
                    <c:forEach items="${countries}" var="name1" >
                        <option value="${name1.getId()}"  ${name1.getId() == parcelUp.getCountryRecipient()?'selected="selected"':''} >${name1.getCountry()}</option>
                    </c:forEach>
                </select>
        </div>

        <div class="form-group">
            <label for="weight" class="labels" >Weight(kg)</label>
            <td><input name = "weightS" class="inputs" type = "number" required step="0.1" min="0" max="15" value="${parcelUp.getWeight()}" id="weight"></td>
        </div>

        <div class="form-group">
            <label for="date" class="labels">Date</label>
            <input type="date" class="inputs" name="date" required value="${parcelUp.getDateOfSend()}" min="${currentDate}" id="date"/>
        </div>


        <div class="form-group">
                <label><input name = "express" id ="express" type = "checkbox" ${parcelUp.getExpress()=='true'?'checked="checked"':''}> Express</label>
        </div>



    <c:choose>
        <c:when test="${user.getRole()==1}">
            <input type = "submit" name="button" value = "add" class="btn btn-success">

        </c:when>
    </c:choose>


    <input type = "submit" name="button" value = "price" class="btn btn-warning">
    <h6 id = "message" >${message}</h6>
</form>

    <% session.setAttribute("parcelUp",null);%>

</div>

<c:choose>
    <c:when test="${user.getRole()==1}">
        <div class="parcels">

            <form method="POST" action="parcel/listParcels">
                <table class="table-bordered" id="myTable" >

                <thead>
                <tr class="bg-primary">
                    <th width="70px"><b> date</b></th>
                    <th  ><b> CountrySender </b></th>
                    <th  ><b> Country Recipient </b></th>
                    <th ><b> Weight(kg) </b></th>
                    <th  ><b> Tarif </b></th>
                    <th  ><b> Price 0.1 kg</b></th>
                    <th width="70px"><b>Delivery time(day)</b></th>
                    <th ><b>Express</b></th>
                    <th ><b>Price </b></th>
                    <th  ><b></b></th>
                </tr>
                </thead>
                    <tbody>
                <c:forEach items= "${parcels}" var="parcel" >
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
                        <td><input type ="checkbox" name="checkbox" value=${parcel.getId()}></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

                <p><h4 id="total" >Total price ${price}. Total weight ${weight}</h4></p>
                <div class="buttons">
                <input type="submit" name = "button" value="create order" class="btn btn-success">

                <input type="submit" name = "button" value="Delete" class="btn btn-danger">
                <input type="submit" name = "update" value="update" class="btn btn-primary">
                </div>

            </form>
        </div>

    </c:when>
</c:choose>


<script>
    $(document).ready( function () {
        $('#myTable').DataTable();
        $('#myTable1').DataTable();
    } );

</script>




<div class="container" >
    <div id="updatePassword" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 250px;">
                <div class="modal-header">
                    <h4 class="modal-title">password update</h4>
                </div>
                <div class="modal-body">
                    <form action="parcelupdatePassword" method="post">
                        <input type="password" name="password" required  pattern ="^[a-zA-Z0-9_]+$" minlength="8" maxlength="32" placeholder="Password">
                        <p></p><input type="password" title="English letter,number and _" name="confirmPassword" required  pattern ="^[a-zA-Z0-9_]+$" minlength="8" maxlength="32" placeholder="Confirm Password">
                        <p></p><input type="submit" name = "button" title="English letter,number and _" value="update Password" class="btn btn-success">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



<div class="container">
    <div id="addModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Discounts</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered" id="myTable1" >
                        <thead>
                        <tr class="bg-primary" >
                            <th>Month</th>
                            <th>Name</th>
                            <th>Discount</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items= "${discounts}" var="discount">
                            <tr >
                                <td>${discount.getMonth()}</td>
                                <td>${discount.getNameT()}</td>
                                <td>${discount.getDis()}</td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>



</body>
</html>
