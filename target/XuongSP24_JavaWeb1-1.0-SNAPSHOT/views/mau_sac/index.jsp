<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="f" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Bán hàng</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Quản lý Sản phẩm
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Sản phẩm</a></li>
                        <li><a class="dropdown-item" href="#">Sản phẩm chi tiết</a></li>
                        <li><a class="dropdown-item" href="/mau-sac/index">Màu sắc</a></li>
                        <li><a class="dropdown-item" href="#">Kích thước</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Quản lý Nhân viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Quản lý Khách hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Quản lý Hóa đơn</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="mt-5 col-10 offset-1">
    <form action="/mau-sac/index" method="GET">
        <div class="row mt-2">
            <div class="col-6">
                <label>Mã</label>
                <input type="text" name="ma" class="form-control" value="${ma}"/>
            </div>
            <div class="col-6">
                <label>Tên</label>
                <input type="text" name="ten" class="form-control" value="${ten}"/>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-6">
                <label>Trạng thái</label>
                <div class="col-12">
                    <input type="radio" name="trangThai" class="form-check-inline" value="1"
                    ${trangThai == 1 ? "checked" : ""}/>
                    <label>Đang hoạt động</label>
                    <input type="radio" name="trangThai" class="form-check-inline" value="0"
                    ${trangThai == 0 ? "checked" : ""}/>
                    <label>Ngừng hoạt động</label>
                </div>
            </div>
        </div>
        <div class="mt-2">
            <button class="btn btn-primary">Tìm kiếm</button>
            <a href="/mau-sac/index" class="btn btn-light">Làm mới</a>
        </div>
    </form>
</div>

<div class="mt-5 col-10 offset-1">
    <a href="/mau-sac/create" class="btn btn-success">Thêm mới</a>
    <table class="table table-stripped">
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
                    <a href="/mau-sac/edit?id=${ms.id}" class="btn btn-primary">Update</a>
                </td>
                <td>
                    <a href="/mau-sac/delete?id=${ms.id}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </table>
</div>
</body>
</html>
