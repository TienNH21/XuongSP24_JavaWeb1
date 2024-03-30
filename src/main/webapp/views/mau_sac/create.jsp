<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tiennh
  Date: 3/12/24
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${ not empty sessionScope.error }">
    <p style="color: red">${sessionScope.error}</p>
    <c:remove var="error" scope="session"></c:remove>
</c:if>

<form method="POST" action="/mau-sac/store">
    <div>
        <label>Mã</label>
        <input type="text" name="ma" />
    </div>
    <div>
        <label>Tên</label>
        <input type="text" name="ten" />
    </div>
    <div>
        <label>Trạng thái</label>
        <input type="radio" name="trangThai" value="1" />
        <label>Đang hoạt động</label>
        <input type="radio" name="trangThai" value="0" />
        <label>Ngừng hoạt động</label>
    </div>
    <div>
        <button>Thêm</button>
    </div>
</form>
</body>
</html>
