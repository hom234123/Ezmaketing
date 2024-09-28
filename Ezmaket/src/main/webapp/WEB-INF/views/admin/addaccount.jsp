<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Thêm mới nhân viên | Admin</title>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

</head>

<body onload="time()" class="app sidebar-mini rtl">
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
				<li class="breadcrumb-item">Nhân viên</li>
				<li class="breadcrumb-item"><a href="#">Tạo mới</a></li>
			</ul>
			<span style="color: red">${error}</span>
			<div id="clock"></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="tile">
					<h3 class="tile-title">Thêm mới nhân viên</h3>
					<div class="tile-body">
						<f:form class="row" action="saveaccount" modelAttribute="account" method="post">
							<div class="form-group col-md-2">
								<label class="control-label">Mã nhân viên</label> 
								<f:input class="form-control" type="text" path="accountid"/>
							</div>							
							<div class="form-group col-md-2">
								<label class="control-label">Họ tên nhân viên</label> 
								<f:input class="form-control" type="text" path="fullname"/>
							</div>
							<div class="form-group col-md-1">
								<label class="control-label">Giới tính</label>
								<select name="gender" class="form-control" id="gender">
									<option value="1">Nam</option>
									<option value="0">Nữ</option>
								</select> 
							</div>
							<div class="form-group col-md-2">
								<label class="control-label">Ngày sinh</label> 
								<f:input class="form-control" type="date"  path="bidthay"/>
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Địa chỉ</label> 
								<f:input class="form-control" type="text"  path="address"/>
							</div>
							<div class="form-group col-md-2">
								<label class="control-label">Email</label> 
								<f:input class="form-control" type="text"  path="email"/>
							</div>
							<div class="form-group col-md-2">
								<label class="control-label">Số điện thoại</label> 
								<f:input
									class="form-control" type="text"  path="phone"/>
									<f:errors cssClass="error" path="Phone"></f:errors>
							</div>
							<div class="form-group col-md-2">
								<label class="control-label">Tên đăng nhập</label> 
								<f:input class="form-control" type="text" path="username"/>
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Mật khẩu</label> <f:input
									class="form-control" type="text" path="passWord"/>
							</div>
							<div class="form-group col-md-3">
								<label class="control-label">Ghi chú</label>
								<f:textarea class="form-control"  path="Note"></f:textarea>
							</div>
							<div class="form-group col-md-2">
								<label class="control-label">Trạng thái</label>
								<select name="Status" class="form-control" id="status">
									<option value="1">Hoạt động</option>
									<option value="0">Khóa</option>
								</select> 
							</div>
							
							<div class="form-group col-md-4">

								<button class="btn btn-save" type="submit">Lưu lại</button>
								<button class="btn btn-cancel" type="button"
									onclick="">Hủy bỏ</button>
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
	<script type="text/javascript">
		function time() {
			var today = new Date();
			var weekday = new Array(7);
			weekday[0] = "Chủ Nhật";
			weekday[1] = "Thứ Hai";
			weekday[2] = "Thứ Ba";
			weekday[3] = "Thứ Tư";
			weekday[4] = "Thứ Năm";
			weekday[5] = "Thứ Sáu";
			weekday[6] = "Thứ Bảy";
			var day = weekday[today.getDay()];
			var dd = today.getDate();
			var mm = today.getMonth() + 1;
			var yyyy = today.getFullYear();
			var h = today.getHours();
			var m = today.getMinutes();
			var s = today.getSeconds();
			m = checkTime(m);
			s = checkTime(s);
			nowTime = h + " giờ " + m + " phút " + s + " giây";
			if (dd < 10) {
				dd = '0' + dd
			}
			if (mm < 10) {
				mm = '0' + mm
			}
			today = day + ', ' + dd + '/' + mm + '/' + yyyy;
			tmp = '<span class="date"> ' + today + ' - ' + nowTime + '</span>';
			document.getElementById("clock").innerHTML = tmp;
			clocktime = setTimeout("time()", "1000", "Javascript");

			function checkTime(i) {
				if (i < 10) {
					i = "0" + i;
				}
				return i;
			}
		}
	</script>
</body>
</html>