<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Edit Contact</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <style>
        div {
            position: relative;
            overflow: hidden;
        }

        #file {
            position: absolute;
            font-size: 50px;
            opacity: 0;
            right: 0;
            top: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
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
<section class="container">
    <div class="d-flex">
        <h2 class="text-success">Create Contact</h2>
    </div>
    <p class="font-italic mt-2">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eos eaque saepe nisi soluta
        adipisci ipsum asperiores minus, quod voluptatem quas assumenda quaerat quis odit obcaecati quasi
        dignissimos modi voluptates numquam?
        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quod mollitia, quam ducimus sit maiores
        consequatur modi! Cum vel placeat exercitationem rerum soluta id, tempora assumenda. Sit excepturi quam
        incidunt ratione!
    </p>
</section>

<form action="" method="post" enctype="multipart/form-data">
    <div class="container mt-3">
        <div class="row">
            <div class="col-md-5">
                <div class="d-flex flex-column">
                    <input type="text" name="name" class="mb-2 form-control" placeholder="Name" value="${requestScope.contact.name}">
                    <input type="text" name="avatar" class="mb-2 form-control" value="${requestScope.contact.avatar}">
                    <input type="text" name="mobile" class="mb-2 form-control" placeholder="Mobile" value="${requestScope.contact.mobile}">
                    <input type="email" name="email" class="mb-2 form-control" placeholder="Email" value="${requestScope.contact.email}">
                    <input type="text" name="company" class="mb-2 form-control" placeholder="Company" value="${requestScope.contact.company}">
                    <input type="text" name="title" class="mb-2 form-control" placeholder="Title" value="${requestScope.contact.title}">
                    <label>
                        <select name="group_id" class="mb-2 form-control">
                            <c:forEach items="${groups}" var="group">
                                <option <c:if test="${requestScope.contact.groups.id==group.id}">selected</c:if> value="${group.id}">${group.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
                <div class="d-flex justify-content-between align-items-start flex-column">
                    <div>
                        <button class="btn btn-primary">Update</button>
                        <a href="${pageContext.request.contextPath}/contacts" type="button" class="btn btn-dark">Close</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="d-flex flex-column">
                    <p><img id="output" width="300" src="../avatars${requestScope.contact.avatar}" /></p>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>

<script>
    const contact = ${contact};
    const contacts = ${contactJSON};
    const group = ${groups};

    function changeImage() {
        var inputImage = document.getElementById('image-input');
        var imgPreview = document.getElementById('img-preview');

        if (inputImage.value === "") {
            imgPreview.src = "default-image.jpg";
        } else {
            imgPreview.src = inputImage.value;
        }
    }

    var loadFile = function (event) {
        var image = document.getElementById('output');
        image.src = URL.createObjectURL(event.target.files[0]);
    };
</script>
</body>
</html>
