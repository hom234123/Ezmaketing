<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Thêm mới đơn| Sale</title>
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
				<li class="breadcrumb-item"><a href="#">Tạo đơn</a></li>
			</ul>
			<span style="color: red">${error}</span>
		</div>
		<div class="row">
			<div class="col-md-12">

				<div class="tile">

					<h3 class="tile-title">Thêm mới đơn</h3>
					<div class="tile-body">
						<f:form action="saveordersale" method="post" modelAttribute="orders"
							class="row">
							<div class="form-group col-md-3">
								<label class="control-label">Nhân viên</label> <input
									type="text" class="form-control" id="AccountName"
									value="${AccountDetails.username}" readonly />

								<!-- Trường ẩn để giữ ID của tài khoản đăng nhập, sẽ được submit -->
								<input type="hidden" name="Accountid"
									value="${AccountDetails.accountid}" />
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Tên khách hàng</label>
								<f:input class="form-control" type="text" path="FullName" />
							</div>

							<div class="form-group col-md-3">
								<label class="control-label">Địa chỉ</label>
								<f:input class="form-control" type="text" path="Address" />
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Ngày tạo</label>
								<f:input class="form-control" type="date" path="OrderDate" id="callDateField" />
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Số điện thoại</label>
								<f:input class="form-control" type="text" path="Phone" />
								<f:errors cssClass="error" path="Phone"></f:errors>
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Tổng tiền</label>
								<f:input class="form-control" type="text" path="TotalAmount" />
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Ghi chú</label>
								<f:textarea class="form-control" path="Note" rows="4"></f:textarea>
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Trạng thái</label> 
								<select name="Status" class="form-control" id="Status">
									<option value="1">Hoàn thành</option>
									<option value="2">Hoàn hàng</option>
									<option value="0">Hủy</option>
								</select>								
							</div>
							<div class="form-group col-md-3">
								<button class="btn btn-save" type="submit">Lưu lại</button>
								<button class="btn btn-cancel" type="button" onclick="">Hủy
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
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const today = new Date().toISOString().substr(0, 10);
        document.getElementById('callDateField').value = today;
    });
</script>
</body>

</html>
