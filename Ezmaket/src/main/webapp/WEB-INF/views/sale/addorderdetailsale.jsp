<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Thêm mới chi tiết đơn| Sale</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Main CSS-->
<link href="<c:url value='/resources/admin/main.css' />"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
<!-- or -->
<link rel="stylesheet"
	href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
<!-- Font-icon css-->
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

</head>

<body class="app sidebar-mini rtl">
	<header class="app-header">
		<ul class="app-nav">
			<li><a class="app-nav__item"
				href="${pageContext.request.contextPath}/sale/">Trang chủ</a></li>
			<li><a class="app-nav__item" href="leadsale">Số điện thoại</a></li>
			<li><a class="app-nav__item" href="phonecallsale">Cuộc gọi</a></li>
			<li><a class="app-nav__item" href="ordersale">Đơn hàng</a></li>
			<!-- User Menu-->
			<c:url value='/login' var="loginUrl" />
			<li><a class="app-nav__item" href="${loginUrl}">Đăng xuất </a></li>
		</ul>
	</header>
	<main class="app-content">
		<div class="app-title">
			<ul class="app-breadcrumb breadcrumb">
				<li class="breadcrumb-item">Danh sách đơn</li>
				<li class="breadcrumb-item"><a href="#">Tạo đơn chi tiết</a></li>
			</ul>
			<span style="color: red">${error}</span>
		</div>
		<div class="row">
			<div class="col-md-12">

				<div class="tile">

					<h3 class="tile-title">Thêm mới chi tiết đơn</h3>
					<div class="tile-body">
						<f:form action="saveorderdetailsale" method="post" modelAttribute="orderDetails"
							class="row">
							
							<div class="form-group col-md-3">
								<label class="control-label">Đơn hàng</label>
								<select name="OrderID" class="form-control" id="OrderID">
									<option>Đơn hàng</option>
									<c:forEach var="orders" items="${Orders}">
										<option value="${orders.orderID}">${orders.orderID}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group col-md-3">
								<label class="control-label">sản phẩm</label>
								<select name="ProductID" class="form-control" id="ProductID">
									<option>Sản phẩm</option>
									<c:forEach var="product" items="${Product}">
										<option value="${product.productID}">${product.productName}</option>
									</c:forEach>
								</select>
								
							</div>
	
							<div class="form-group col-md-3">
								<label class="control-label">Số lượng</label>
								<f:input class="form-control" type="text" path="Quantity" />
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Tổng tiền</label>
								<f:input class="form-control" type="text" path="Subtotal" />
							</div>
							
							<div class="form-group col-md-3">
								<button class="btn btn-save" type="submit">Lưu lại</button>
								<button class="btn btn-cancel" type="button" onclick="window.location.href='">Hủy
									bỏ</button>
							</div>
						</f:form>
						<div class="tile-footer"></div>
					</div>
				</div>
			</div>
		</div>
	</main>

	<!-- Essential javascripts for application to work-->
	<script src="<c:url value='/resources/js/jquery-3.2.1.min.js' />"></script>
	<script src="<c:url value='/resources/js/popper.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<!-- The javascript plugin to display page loading on top-->
	<script src="<c:url value='/resources/js/plugins/pace.min.js' />"></script>

</body>

</html>
