<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Thêm cuộc gọi | Admin</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Main CSS-->
<link href="<c:url value="/resources/admin/main.css" />"
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
				href="${pageContext.request.contextPath}/admin/">Trang chủ</a></li>
			<li><a class="app-nav__item" href="lead">Số điện thoại</a></li>
			<li><a class="app-nav__item" href="phonecall">Cuộc gọi</a></li>
			<li><a class="app-nav__item" href="order">Đơn hàng</a></li>
			<li><a class="app-nav__item" href="warehouse">Quản lí</a></li>
			<li><a class="app-nav__item " href="account">Tài khoản</a></li>
			
			<!-- User Menu-->
			<c:url value='/login' var="loginUrl" />
			<li><a class="app-nav__item" href="${loginUrl}">Đăng xuất </a></li>
		</ul>
	</header>
	<main class="app-content">
		<div class="app-title">
			<ul class="app-breadcrumb breadcrumb">
				<li class="breadcrumb-item">Danh sách cuộc gọi</li>
				<li class="breadcrumb-item"><a href="#">Thêm cuộc gọi</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-md-12">

				<div class="tile">

					<h3 class="tile-title">Thêm cuộc gọi</h3>
					<div class="tile-body">
						<f:form class="row" action="savephonecall" method="post" modelAttribute="phonecall">
							<div class="form-group col-md-2">
								<label for="exampleSelect1" class="control-label">Mã cuộc gọi</label> 
									<select name="LeadID" class="form-control" id="LeadID">
									<option>-- Chọn Mã --</option>
										<c:forEach var="lead" items="${LeadList}">
										<option value="${lead.leadID}">${lead.leadID}</option>
									</c:forEach>

								</select>
							</div>
							<div class="form-group  col-md-2">
								<label class="control-label">Nhân viên</label> 
								<select	name="Accountid" class="form-control" id="Accountid">
									<option>-- Chọn Mã --</option>
									<c:forEach var="Account" items="${AccountList}">
										<option value="${Account.accountid}">${Account.fullname}</option>
									</c:forEach>

								</select>
							</div>
							<div class="form-group col-md-2">
								<label class="control-label">Ngày gọi</label> 
								<f:input class="form-control" type="date" path="CallDate" id="calldayup"/>
							</div>
							<div class="form-group col-md-2">
								<label class="control-label">Phút gọi</label>
								<f:input class="form-control" type="text" path="Duration" />
							</div>
							<div class="form-group  col-md-4">
								<label class="control-label">Ghi chú</label>
								<f:textarea class="form-control" path="Notes" rows="4"></f:textarea>
							</div>
							<div class="form-group col-md-4">

								<button class="btn btn-save" type="submit">Lưu lại</button>
								<button class="btn btn-cancel" type="button" onclick="">Hủy bỏ</button>
							</div>
						</f:form>
						<div class="tile-footer"></div>
					</div>
				</div>
			</div>
		</div>
	</main>

	<!-- Essential javascripts for application to work-->
	<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<!-- The javascript plugin to display page loading on top-->
	<script src="<c:url value="/resources/js/plugins/pace.min.js" />"></script>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			const today = new Date().toISOString().substr(0, 10);
			document.getElementById('calldayup').value = today;
		});
	</script>
</body>

</html>