<%--
  Created by IntelliJ IDEA.
  User: Bao
  Date: 8/28/2023
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Info Contact</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <style>
        .modal {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            /* Add other styles like width, height, background, etc. */
        }
    </style>
</head>

<body class="bg-warning-subtle">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <div class="d-flex">
            <i class="fa-solid fa-phone d-flex align-items-center" style="color: #fbff0a;"></i>
            <a class="nav-link"
               style="color: blue;font-family: Verdana, Geneva, Tahoma, sans-serif;font-weight: 700;"
               href="${pageContext.request.contextPath}/contacts">CodeGym</a>
            <h2 class="nav-link" style="color: white;">Contact</h2>
        </div>
    </div>
</nav>
<section class="container mt-2">
    <div class="d-flex">
        <h2 class="justify-content-start d-flex text-warning">Contact Manager</h2>
        <a href="${pageContext.request.contextPath}/contacts">
            <button class="btn btn-warning ml-3"><i class="fa-solid fa-backward" style="color: #000000;"></i>
                BACK</button>
        </a>
    </div>
    <p class="font-italic mt-2">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eos eaque saepe nisi soluta
        adipisci ipsum asperiores minus, quod voluptatem quas assumenda quaerat quis odit obcaecati quasi
        dignissimos modi voluptates numquam?
        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quod mollitia, quam ducimus sit maiores
        consequatur modi! Cum vel placeat exercitationem rerum soluta id, tempora assumenda. Sit excepturi quam
        incidunt ratione!
    </p>
</section>
<div class="container">
    <div class="row">
        <div class="col-12 mt-4">
            <div class="">
                <div class="row justify-content-around">
                    <div class="col-3 card d-flex align-items-center bg-white pt-2 pb-2">
                        <img src="../avatars${requestScope.contact.avatar}" alt="Contact Image" class="img-fluid" style="margin-top: 4px;margin-bottom: 4px">
                    </div>
                    <div class="col-8 card bg-white pt-2 pb-2">
                        <table class="table-bordered" style="width: 100%; height: 100%;">
                            <tbody>
                            <tr>
                                <td>
                                    <p class="card-text ml-2">Name: <strong>${requestScope.contact.name}</strong></p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p class="card-text ml-2">Mobile: <strong>${requestScope.contact.mobile}</strong></p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p class="card-text ml-2">Email: <strong>${requestScope.contact.email}</strong></p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p class="card-text ml-2">Company: <strong>${requestScope.contact.company}</strong></p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p class="card-text ml-2">Title: <strong>${requestScope.contact.title}</strong></p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p class="card-text ml-2">Group: <strong>${requestScope.contact.groups.name}</strong></p>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>






<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>

<script>
    var modalConfirm = function (callback) {

        $("#btn-confirm").on("click", function () {
            $("#mi-modal").modal('show');
        });

        $("#modal-btn-si").on("click", function () {
            callback(true);
            $("#mi-modal").modal('hide');
        });

        $("#modal-btn-no").on("click", function () {
            callback(false);
            $("#mi-modal").modal('hide');
        });
    };

    modalConfirm(function (confirm) {
        if (confirm) {
            //Acciones si el usuario confirma
            $("#result").html("CONFIRMADO");
        } else {
            //Acciones si el usuario no confirma
            $("#result").html("NO CONFIRMADO");
        }
    });

    const message = document.getElementById('message');
    const btnToast = document.getElementById('liveToastBtn');
</script>
</body>
</html>
