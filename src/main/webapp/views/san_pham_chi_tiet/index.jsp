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
    <a href="#" class="btn btn-success">Thêm mới</a>
    <table class="table table-stripped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Mã</th>
            <th>Sản phẩm</th>
            <th>Màu sắc</th>
            <th>Kích thước</th>
            <th>Số lượng</th>
            <th>Đơn giá</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ data }" var="sp">
            <tr>
                <td>${sp.id}</td>
                <td>${sp.maSPCT}</td>
                <td>${sanPham.ten}</td>
                <td>${sp.tenMauSac}</td>
                <td>${sp.tenKichThuoc}</td>
                <td>${sp.soLuong}</td>
                <td>${sp.donGia}</td>
                <td>${sp.trangThai}</td>
                <td>
                    <a href="#" class="btn btn-primary">Update</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </table>
</div>
</body>
</html>
