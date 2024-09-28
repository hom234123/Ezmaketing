<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Trang chủ | Maketing</title>
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
		<!-- Sidebar toggle button <a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
      aria-label="Hide Sidebar"></a>-->
		<!-- Navbar Right Menu-->
		<ul class="app-nav">
			<li><a class="app-nav__item"
				href="${pageContext.request.contextPath}/maketing/">Trang chủ</a></li>
			<li><a class="app-nav__item" href="leadmkt">Số điện thoại</a></li>
			<li><a class="app-nav__item" href="ordermkt">Đơn hàng</a></li>
			<!-- User Menu-->
			<c:url value='/login' var="loginUrl" />
			<li><a class="app-nav__item" href="${loginUrl}">Đăng xuất </a></li>
		</ul>
	</header>
	<main class="app-content">
		<div class="row">
			<div class="col-md-12">
				<div class="app-title">
					<ul class="app-breadcrumb breadcrumb">
						<li class="breadcrumb-item"><a href="#"><b>Bảng điều
									khiển</b></a></li>
					</ul>
					<div id="clock"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<!--Left-->
			<div class="col-md-12 col-lg-6">
				<div class="row">
					<!-- col-6 -->
					<div class="col-md-6">
						<div class="widget-small primary coloured-icon">
							<i class='icon bx bxs-user-account fa-3x'></i>
							<div class="info">
								<h4>Tổng số điện thoại</h4>
								<p>
									<b>${totalLeadAc } Điện thoại</b>
								</p>
								<p class="info-tong">Tổng số điện thoại được quản lý.</p>
							</div>
						</div>
					</div>					
					<div class="col-md-6">
						<div class="widget-small danger coloured-icon">
							<i class='icon bx bxs-data fa-3x'></i>
							<div class="info">
								<h4>Doanh Thu</h4>
								<p>
									<b>${totalOrders1Amount } vnd</b>
								</p>
								<p class="info-tong">doanh thu theo số điện thoại.</p>
							</div>
						</div>
					</div>
					<!-- col-12 -->
					<div class="col-md-12">
						<div class="tile">
							<h3 class="tile-title">Tình trạng đơn hàng</h3>
							<div>
								<table class="table table-bordered" id="orderTable">
									<thead>
										<tr>
											<th>Mã</th>
											<th>Nhân viên</th>
											<th>Tổng tiền</th>
											<th>Trạng thái</th>
											
										</tr>
									</thead>
									<tbody>
										<c:forEach var="O" items="${ordersList}">
											<tr>
												<td>${O.getOrderID()}</td>
												<td><c:forEach var="Ac" items="${Account}">
														<c:if test="${Ac.accountid eq O.accountid}">
            										${Ac.fullname}
       											</c:if>
													</c:forEach></td>
												<td>${O.getTotalAmount()}</td>
												<td><c:choose>
												<c:when test="${O.getStatus() == 1}">
													<span class="badge bg-success">Hoàn thành</span>
												</c:when>
												<c:otherwise>
													<!-- Hiển thị nếu giá trị không phải 1 (chưa hoàn thành) -->
													<span class="badge bg-warning">Hủy</span>
												</c:otherwise>
											</c:choose></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div id="orderPagination"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-lg-6">
				<div class="row">
					<!-- col-6 -->
					<div class="col-md-6">
						<div class="widget-small primary coloured-icon">
							<i class='icon bx bxs-user-account fa-3x'></i>
							<div class="info">
								<h4>Tổng đơn hàng</h4>
								<p>
									<b>${totalOrders1 } đơn hàng</b>
								</p>
								<p class="info-tong">Tổng số đơn hàng thành công.</p>
							</div>
						</div>
					</div>					
					<div class="col-md-6">
						<div class="widget-small danger coloured-icon">
							<i class='icon bx bxs-phone-outgoing fa-3x'></i>
							<div class="info">
								<h4>Tổng số điện thoại đã lên</h4>
								<p>
									<b>${totalleadsale } Số điện thoại</b>
								</p>
								<p class="info-tong">Tổng số điện thoại sale đã nhận</p>
							</div>
						</div>
					</div>
					<!-- col-12 -->
					<div class="col-md-12">
						<div class="tile">
							<h3 class="tile-title">Tình trạng số</h3>
							<div>
								<table class="table table-hover" id="customerTable">
									<thead>
										<tr>
											<th>Mã</th>
											<th>Họ và tên</th>
											<th>Số điện thoại</th>
											<th>Tình trạng</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="L" items="${Lead}">
											<tr>
												<td>${L.getLeadID()}</td>
												<td>${L.getFullName()}</td>
												<td>${L.getPhone()}</td>
												<td><c:choose>
												<c:when test="${L.getStatus()== 1}">
													<span class="badge bg-success">Đã nhận</span>
												</c:when>
												<c:otherwise>
													<!-- Hiển thị nếu giá trị không phải 1 (chưa hoàn thành) -->
													<span class="badge bg-warning">Chưa nhận</span>
												</c:otherwise>
											</c:choose></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div id="customerPagination"></div>
							</div>						
						</div>
					</div>					
				</div>
			</div>
		</div>		
		<div class="text-center" style="font-size: 13px">
			<p>
				<b>Copyright <script type="text/javascript">
					document.write(new Date().getFullYear());
				</script> Phần mềm quản lý số
				</b>
			</p>
		</div>
	</main>
	<script
		src="<c:url value="/resources/vendor/jquery/jquery-3.2.1.min.js" />"></script>
	<!--===============================================================================================-->
	<script src="<c:url value= "/resources/js/popper.min.js" /> "></script>
	<script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
	<!--========================================================================-->
	<script
		src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js" />"></script>
	<!--===============================================================================================-->
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<script src="<c:url value="/resources/js/admin.js" />"></script>
	<!--===============================================================================================-->
	<script src="<c:url value="/resources/js/plugins/pace.min.js" />"></script>
	<!--===============================================================================================-->

	<!--===============================================================================================-->

	<script type="text/javascript">
		//Thời Gian
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