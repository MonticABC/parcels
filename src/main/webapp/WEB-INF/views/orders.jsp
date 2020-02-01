<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
  Created by IntelliJ IDEA.
  User: KSOFTNOUT
  Date: 09.11.2018
  Time: 18:24
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

<link rel="stylesheet" type="text/css" href="resources/css/order.css">

<link rel="stylesheet" type="text/css" href="resources/css/headers.css">

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light" id="navbar">
    <div class="first">
    <a href="parcel">My parcels</a>
    </div>

    <div class="second">
    <a href="#addModal" data-toggle="modal">Personal information</a>
    </div>

    <div class="third">
        <a href="#support" data-toggle="modal">Support</a>
    </div>

    <form method="POST" action="logout"  class="logout">
        <input type ="submit" name="logout" class="btn btn-outline-danger" value="logout" >
    </form>


</nav>


<script>
    function myFunction(x) {
        document.getElementById("abc").value = x.id;
    }
</script>
<h3 id="message">${message}</h3>
<p></p>
<div class="orders">
    <form method="post" action="order/deleteOrder">
        <c:forEach items= "${orders}" var="order">
            <h3>Order price ${order.getTotalpPrice()}. Order weight ${weghts.get(order.getId())} </h3>
            <table class="table-bordered table-hover "  style="width: 90%;font-size:18px" >
                <tbody id="${order.getId()}" onclick="myFunction(this)">
                <tr class="bg-primary">
                    <td ><b> date</b></td>
                    <td ><b> CountrySender </b></td>
                    <td ><b> Country Recipient </b></td>
                    <td ><b> Weight(kg) </b></td>
                    <td ><b> Tarif </b></td>
                    <td ><b> Price 0.1 kg</b></td>
                    <td><b> Delivery Time(day)</b></td>
                    <td ><b>Express</b></td>
                    <td ><b>Price</b></td>
                </tr>
                <c:forEach items= "${order.parcelsById}" var="parcel" >
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
            <p></p>
        </c:forEach>

        <h2>Price of all orders ${total}</h2>
        <p></p>
        <input id="abc" name="command" type="hidden" value="">
        <input type="submit" name="delete" value="delete order"  class="btn btn-danger">
    </form>



</div>



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
                                <input class="form-control"  pattern ="^[a-zA-Z0-9_]+$" title="English letter,number and _" minlength="8" maxlength="32" required name="username" path="username" value="${user.getUsername()}" type="text" id="example-text-input">
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
                                <input class="form-control"  pattern ="^[a-zA-Z0-9-]+$" title="English letter,number and -" minlength="2" maxlength="90" name="name" path="name" value="${user.getName()}" type="text" value="Artisanal kale" id="example-text-input2">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="example-text-input3" class="col-2 col-form-label">Surname</label>
                            <div class="col-10">
                                <input class="form-control"  pattern ="^[a-zA-Z0-9-]+$" title="English letter,number and -" minlength="2" maxlength="32" name="surname" path="surname" value="${user.getSurname()}" type="text" value="Artisanal kale" id="example-text-input3">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="example-text-input4" class="col-2 col-form-label">Phone</label>
                            <div class="col-10">
                                <input class="form-control" name="phone" path="phone" placeholder="Phone(+375 (XX) XXXXXXX)" title = "+375 (XX) XXXXXXX" value="${user.getPhone()}" pattern="^\+375 \((17|29|33|44)\) [0-9]{7}$" type="text" value="Artisanal kale" id="example-text-input4">
                            </div>
                        </div>

                        <input type="submit" class="btn btn-success" name="save" value="save">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>





<div class="container">

    <div id="support" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Message to support</h4>
                </div>
                <div class="modal-body">
                    <form action="ordersMessage" method="post" >
                        <textarea name="comment" style="width: 400px;height: 100px"  maxlength="500" required></textarea>
<P></P>
                        <p><input type="submit" value="send" class="btn bg-success"></p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
