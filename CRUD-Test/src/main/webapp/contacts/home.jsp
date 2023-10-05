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
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>


    <style>
        .modal-confirm {
            color: #636363;
            width: 400px;
        }
        .modal-confirm .modal-content {
            padding: 20px;
            border-radius: 5px;
            border: none;
            text-align: center;
            font-size: 14px;
        }
        .modal-confirm .modal-header {
            border-bottom: none;
            position: relative;
        }
        .modal-confirm h4 {
            text-align: center;
            font-size: 26px;
            margin: 30px 0 -10px;
        }
        .modal-confirm .close {
            position: absolute;
            top: -5px;
            right: -2px;
        }
        .modal-confirm .modal-body {
            color: #999;
        }
        .modal-confirm .modal-footer {
            border: none;
            text-align: center;
            border-radius: 5px;
            font-size: 13px;
            padding: 10px 15px 25px;
        }
        .modal-confirm .modal-footer a {
            color: #999;
        }
        .modal-confirm .icon-box {
            width: 80px;
            height: 80px;
            margin: 0 auto;
            border-radius: 50%;
            z-index: 9;
            text-align: center;
            border: 3px solid #f15e5e;
        }
        .modal-confirm .icon-box i {
            color: #f15e5e;
            font-size: 46px;
            display: inline-block;
            margin-top: 13px;
        }
        .modal-confirm .btn, .modal-confirm .btn:active {
            color: #fff;
            border-radius: 4px;
            background: #60c7c1;
            text-decoration: none;
            transition: all 0.4s;
            line-height: normal;
            min-width: 120px;
            border: none;
            min-height: 40px;
            margin: 0 5px;
        }
        .modal-confirm .btn-secondary {
            background: #c1c1c1;
        }
        .modal-confirm .btn-secondary:hover, .modal-confirm .btn-secondary:focus {
            background: #a8a8a8;
        }
        .modal-confirm .btn-danger {
            background: #f15e5e;
        }
        .modal-confirm .btn-danger:hover, .modal-confirm .btn-danger:focus {
            background: #ee3535;
        }
        .trigger-btn {
            display: inline-block;
            /*margin: 100px auto;*/
        }
    </style>
</head>
<body class="bg-warning-subtle">
<!-- modal confirm xoa -->
<div id="myModal" class="modal fade">
    <div class="modal-dialog modal-confirm">
        <div class="modal-content">
            <div class="modal-header flex-column">
                <div class="icon-box">
                    <i class="material-icons">&#xE5CD;</i>
                </div>
                <h4 class="modal-title w-100">Are you sure?</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <p>Do you really want to delete these records? This process cannot be undone.</p>
            </div>
            <div class="modal-footer justify-content-center">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger">Delete</button>
            </div>
        </div>
    </div>
</div>

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
        <h2 class="mt-3">Contact Manager</h2>
        <a href="${pageContext.request.contextPath}/contacts?action=create">
            <button class="btn btn-primary ml-3 mt-3"><i class="fa-solid fa-plus" style="color: #ffffff;"></i>NEW</button>
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
<div class="container mt-3">
    <div class="row justify-content-start">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="input-group">
                    <input type="search" name="search" value="${pageable.search}" class="form-control" placeholder="Search">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Search</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container ">
    <div class="row">
        <c:forEach items="${contacts}" var="contact">
            <div class="col-6 mt-4">
                <div class="card">
                    <div class="card-body row">
                        <div class="col-3" style="display: flex; align-items: center;">
                            <img src="../avatars${contact.avatar}" alt="Contact Image" style="width: 102px;height: 102px" class="rounded-circle img-fluid align-items-center">
                        </div>
                        <div class="col-7">
                            <table class="table-bordered" style="width: 100%; height: 100%;">
                                <tbody>
                                <tr>
                                    <td>
                                        <p class="card-text ml-2">Name: <strong>${contact.name}</strong></p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p class="card-text ml-2">Mobile: <strong>${contact.mobile}</strong></p>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p class="card-text ml-2">Email: <strong>${contact.email}</strong></p>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-2">
                            <a href="${pageContext.request.contextPath}/contacts?action=info&id=${contact.id}"><button class="btn btn-warning mt-2"><i class="fas fa-eye"></i></button></a>
                            <a href="${pageContext.request.contextPath}/contacts?action=edit&id=${contact.id}"><button class="btn btn-primary mt-2"><i class="fas fa-edit"></i></button></a>
                            <a href="${pageContext.request.contextPath}/contacts?action=delete&id=${contact.id}" class="trigger-btn"><button class="btn btn-danger mt-2"><i class="fas fa-trash"></i></button></a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>

<script>
    const contacts = ${contactsJSON};
    const groups = ${groupsJSON};


    const message = document.getElementById('message');
    const btnToast = document.getElementById('liveToastBtn');
    const form = document.getElementById('form');
    const tileModal = document.getElementById("exampleModalLabel");
    window.onload = () => {
        if(message.innerHTML.trim() !== ''){
            toastr.success(message.innerHTML);
        }
    }

    // var modalConfirm = function (callback) {
    //     $("#btn-confirm").on("click", function () {
    //         $("#mi-modal").modal('show');
    //     });
    //
    //     $("#modal-btn-si").on("click", function () {
    //         callback(true);
    //         $("#mi-modal").modal('hide');
    //     });
    //
    //     $("#modal-btn-no").on("click", function () {
    //         callback(false);
    //         $("#mi-modal").modal('hide');
    //     });
    // };
    //
    // modalConfirm(function (confirm) {
    //     if (confirm) {
    //         //Acciones si el usuario confirma
    //         $("#result").html("CONFIRM");
    //     } else {
    //         //Acciones si el usuario no confirma
    //         $("#result").html("NO CONFIRM");
    //     }
    // });

    <%--document.addEventListener('DOMContentLoaded', function() {--%>
    <%--    const deleteButtons = document.querySelectorAll('.delete-button');--%>

    <%--    deleteButtons.forEach(button => {--%>
    <%--        button.addEventListener('click', function(event) {--%>
    <%--            event.preventDefault();--%>

    <%--            const contactId = this.dataset.contactId;--%>

    <%--            const modalConfirmCallback = function (confirm) {--%>
    <%--                if (confirm) {--%>
    <%--                    // Xác nhận xóa--%>
    <%--                    deleteContact(contactId);--%>
    <%--                }--%>
    <%--            };--%>

    <%--            modalConfirm(modalConfirmCallback);--%>
    <%--        });--%>
    <%--    });--%>

    <%--    function deleteContact(contactId) {--%>
    <%--        // Gửi yêu cầu xóa đến máy chủ hoặc thực hiện bất kỳ thao tác xóa nào bạn cần--%>
    <%--        // Ví dụ:--%>
    <%--        fetch(`/contacts?action=delete&id=${contactId}`, {--%>
    <%--          method: 'DELETE',--%>
    <%--        })--%>
    <%--        .then(response => response.json())--%>
    <%--        .then(data => {--%>
    <%--          if (data.success) {--%>
    <%--            // Xóa thành công, cập nhật giao diện người dùng--%>
    <%--            // Ví dụ:--%>
    <%--            window.location.reload();--%>
    <%--          } else {--%>
    <%--            // Xóa không thành công, xử lý lỗi nếu cần--%>
    <%--          }--%>
    <%--        })--%>
    <%--        .catch(error => {--%>
    <%--          console.error('Error:', error);--%>
    <%--        });--%>

    <%--        // Ví dụ: Hiển thị thông báo toast (đây là một phần của mã ban đầu của bạn)--%>
    <%--        toastr.success('Contact deleted successfully');--%>
    <%--    }--%>

    <%--    function modalConfirm(callback) {--%>
    <%--        $("#btn-confirm").modal('show');--%>

    <%--        $("#modal-btn-si").on("click", function () {--%>
    <%--            callback(true);--%>
    <%--            $("#mi-modal").modal('hide');--%>
    <%--        });--%>

    <%--        $("#modal-btn-no").on("click", function () {--%>
    <%--            callback(false);--%>
    <%--            $("#mi-modal").modal('hide');--%>
    <%--        });--%>
    <%--    }--%>
    <%--});--%>

</script>
</body>
</html>
