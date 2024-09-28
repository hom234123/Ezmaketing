<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Đơn hàng | Maketing</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Main CSS-->
<link href="<c:url value="/resources/admin/main.css" />"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/util.css" />" rel="stylesheet"
	type="text/css">
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
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css"
	rel="stylesheet">
</head>

<body onload="time()" class="app sidebar-mini rtl">
	<!-- Navbar-->
	<header class="app-header">
		
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
		<div class="app-title">
			<ul class="app-breadcrumb breadcrumb side">
				<li class="breadcrumb-item active"><a href="#"><b>Danh
							sách đơn hàng</b></a></li>
			</ul>
			<div id="clock"></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="tile">
					<div class="tile-body">
						
						<table class="table table-hover table-bordered" id="sampleTable">
							<thead>
								<tr>
									
									<th>Mã</th>
									<th>Nhân viên</th>
									<th>Họ và Tên khách</th>
									<th>Địa chỉ</th>
									<th>Số ĐT</th>
									<th>Ngày tạo</th>
									<th>Tổng tiền</th>
									<th>Ghi chú</th>
									<th>Tình trạng</th>
									
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
										<td>${O.getFullName()}</td>
										<td>${O.getAddress()}</td>
										<td>${O.getPhone()}</td>
										<td>${O.getOrderDate()}</td>
										<td>${O.getTotalAmount()}</td>
										<td>${O.getNote()}</td>
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
					</div>
				</div>
			</div>
		</div>
		
	</main>
	<!-- Essential javascripts for application to work-->
	<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<script src="<c:url value="/resources/js/DeleteDetail.js" />"></script>
	<script src="<c:url value="/resources/js/DeleteBill.js" />"></script>
	<script src="<c:url value="/resources/js/admin.js" />"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

	<!-- The javascript plugin to display page loading on top-->
	<script src="<c:url value="/resources/js/plugins/pace.min.js" />"></script>
	<!-- Page specific javascripts-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	<!-- Data table plugin-->
	<script
		src="<c:url value="/resources/js/plugins/jquery.dataTables.min.js"/> "
		type="text/javascript"></script>
	<script
		src="<c:url value="/resources/js/plugins/dataTables.bootstrap.min.js"/> "
		type="text/javascript"></script>

	<script type="text/javascript">
		$('#sampleTable').DataTable();
	</script>
	<script>
		oTable = $('#sampleTable').dataTable();
		$('#all').click(
				function(e) {
					$('#sampleTable tbody :checkbox').prop('checked',
							$(this).is(':checked'));
					e.stopImmediatePropagation();
				});

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