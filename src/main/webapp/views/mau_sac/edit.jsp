<%--
  Created by IntelliJ IDEA.
  User: tiennh
  Date: 3/12/24
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST" action="/mau-sac/update?id=${data.id}">
    <div>
        <label>Mã</label>
        <input type="text" name="ma" value="${data.ma}" />
    </div>
    <div>
        <label>Tên</label>
        <input type="text" name="ten" value="${data.ten}" />
    </div>
    <div>
        <label>Trạng thái</label>
        <input type="radio" name="trangThai" value="1"
        ${ data.trangThai == 1 ? "checked" : "" }/>
        <label>Đang hoạt động</label>
        <input type="radio" name="trangThai" value="0"
        ${ data.trangThai == 0 ? "checked" : "" }/>
        <label>Ngừng hoạt động</label>
    </div>
    <div>
        <button>Thêm</button>
    </div>
</form>
</body>
</html>
