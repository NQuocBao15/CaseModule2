<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Teacher</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<div class="container">
    <div class="card container px-6" style="height: 100vh">
        <h3 class="text-center" style="padding-top: 40px;">Sua giao vien</h3>
        <form action="/teachers?action=edit&id=${teacher.id}" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Tên</label>
                <input type="text" class="form-control" id="name" name="name" value="${teacher.name}" required>
            </div>
            <div class="mb-3">
                <label for="hobbie" class="form-label">Sở thích</label>
                <input type="text" class="form-control" id="hobbie" name="hobbie" value="${teacher.hobbie}" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Giới tính</label><br>
                <c:forEach var="gender" items="${genders}">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input radio" name="gender" type="radio" id="gender" value="${gender}"
                            <c:if test="${teacher.gender == gender}">checked</c:if>>
                        <label for="gender">${gender}</label>
                    </div>
                </c:forEach>
            </div>
            <div class="row d-flex">
                <div class="col-6">
                    <label for="dob" class="form-label">Ngày sinh</label>
                    <input type="date" name="dob" id="dob" class="form-control" value="${teacher.dob}">
                </div>
                <div class="col-6">
                    <label for="degree" class="form-label">Bằng cấp</label>
                    <select class="form-control" name="degree" id="degree">
                        <c:forEach var="degree" items="${degrees}">
                            <option <c:if test="${teacher.degree.id == degree.id}">selected</c:if> value="${degree.id}">${degree.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="mb-3" style=" margin-top: 15px;">
                <a class="btn btn-danger" href="/teacher">Quay lai</a>
                <button class="btn btn-primary">Sua</button>
            </div>
        </form>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous"></script>
</body>
</html>
