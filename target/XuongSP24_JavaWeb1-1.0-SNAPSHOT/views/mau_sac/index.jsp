<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/mau-sac/create">Thêm mới</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Mã</th>
        <th>Tên</th>
        <th>Trạng thái</th>
        <th>Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ data }" var="ms">
        <tr>
            <td>${ms.id}</td>
            <td>${ms.ma}</td>
            <td>${ms.ten}</td>
            <td>${ms.trangThai}</td>
            <td>
                <a href="/mau-sac/edit?id=${ms.id}">Update</a>
            </td>
            <td>
                <a href="/mau-sac/delete?id=${ms.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
