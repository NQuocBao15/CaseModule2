<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Teacher</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <div class="container">
    <h3>Danh sách giáo viên</h3>
    <a href="/teachers?action=create" class="btn btn-outline-success">Them moi</a>
    <form method="get" class="d-flex">
      <label for="gender"></label><select name="gender" id="gender">
      <c:forEach items="${genders}" var="gender">
        <option value="${gender}">${gender}</option>
      </c:forEach>
    </select>
      <label for="degree"></label><select name="degree" id="degree">
      <c:forEach items="${degrees}" var="degree">
        <option value="${degree.id}">${degree.name}</option>
      </c:forEach>
    </select>
      <button>Search</button>
    </form>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Ten</th>
          <th>So thich</th>
          <th>Ngay sinh</th>
          <th>Gioi tinh</th>
          <th>Bang cap</th>
          <th>Ation</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${teachers}" var="teacher">
          <tr>
            <td>${teacher.name}</td>
            <td>${teacher.dob}</td>
            <td>${teacher.hobbie}</td>
            <td>${teacher.gender}</td>
            <td>${teacher.degree.name}</td>
            <td>
              <a href="/teachers?action=edit&id=${teacher.id}"><i class="fa-solid fa-pen-to-square"></i></a>
              <a href="/teachers?action=delete&id=${teacher.id}" onclick="return confirm('Ban co muon xoa giao vien '+ ${teacher.name} + ' khong?')"><i class="fa-solid fa-trash-can"></i></a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>
