<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Quản lý chung | Admin</title>
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
	<!-- Navbar-->
	<header class="app-header">		
		<ul class="app-nav">
			<li><a class="app-nav__item" href="${pageContext.request.contextPath}/admin/">Trang chủ</a></li>
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
			<ul class="app-breadcrumb breadcrumb side">
				<li class="breadcrumb-item active"><a href="#"><b>Quản
							lý chung</b></a></li>
			</ul>
			<div id="clock"></div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="tile">
					<div class="tile-body">
						<div class="row element-button">
							<div class="col-sm-2">

								<a class="btn btn-add btn-sm" href="addproduct"
									title="Thêm"><i ></i> Thêm mới sản phẩm</a>
							</div>
							<div class="col-sm-2">
							<a class="btn btn-excel btn-sm" href="addcategory" title="In"><i
									></i>Thêm mới thể loại</a>
								
							</div>
							<div class="col-sm-2">
								<a class="btn btn-excel btn-sm" href="addrole" title="In"><i
									></i> Thêm mới vai trò</a>
							</div>
							<div class="col-sm-2">
								<a class="btn btn-excel btn-sm" href="addaccountrole" title="In"><i
									></i> Phân quyền</a>
							</div>
						</div>
						<table class="table table-hover table-bordered" id="sampleTable">
							<thead>
								<tr>									
									<th>Mã</th>
									<th>Tên sản phẩm</th>
									<th>Thể loại</th>
									<th>Đơn vị</th>
									<th>Số lượng</th>
									<th>Giá</th>
									<th>Giá giảm</th>
									<th>Tổng tiền</th>
									<th>Ghi chú</th>
									<th>Trạng thái</th>
									<th>Chức năng</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="P" items="${Product}">
									<tr>
										<td>${P.getProductID() }</td>
										<td>${P.getProductName() }</td>
										<td><c:forEach var="C" items="${Category}">
												<c:if test="${C.categoryID eq P.categoryID}">
            										${C.categoryName}
       											</c:if>
											</c:forEach></td>
										<td>${P.getUnit() }</td>
										<td>${P.getQuantity() }</td>
										<td>${P.getPrice() }</td>
										<td>${P.getSalePrice() }</td>
										<td>${P.getTotalPrice() }</td>
										<td>${P.getNote() }</td>
										<td><span class="badge bg-success">${P.getStatus() }</span></td>
										<td><a onclick="return confirm('Bạn có muốn xóa không?')"
											class="btn btn-danger" href="deleteproduct?ProductID=${P.getProductID()}">Xóa</a>
											<a class="btn btn-success" href="editproduct?ProductID=${P.getProductID()}">Sửa</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<!--Left-->
			<div class="col-md-12 col-lg-3">
				<div class="row">					
					<div class="col-md-12">
						<div class="tile">
							<h3 class="tile-title">Thể loại</h3>
							<div>
								<table class="table table-bordered" id="orderTable">
									<thead>
										<tr>
											<th>Mã</th>
											<th>Tên</th>											
											<th>Chức năng</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="C" items="${Category}">
											<tr>
												<td>${C.getCategoryID()}</td>
												<td>${C.getCategoryName()}</td>												
												<td><a onclick="return confirm('Bạn có muốn xóa không?')"
											class="btn btn-danger" href="deletecategory?CategoryID=${C.getCategoryID()}">Xóa</a>
											<a class="btn btn-success" href="editcategory?CategoryID=${C.getCategoryID()}">Sửa</a></td>
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
			<!--END left-->
			<div class="col-md-12 col-lg-6">
				<div class="row">					
					<div class="col-md-12">
						<div class="tile">
							<h3 class="tile-title">Phân quyền</h3>
							<div>
								<table class="table table-bordered" id="accountTable">
									<thead>
										<tr>
											<th>Mã</th>
											<th>Vai Trò</th>
											<th>Nhân viên</th>
											<th>Chức năng</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="Ar" items="${AccountRole}">
											<tr><td>${Ar.getId()}</td>												
												<td>${Ar.getRole()}</td>
												<td>${Ar.getAccount()}</td>
												<td>
											<a class="btn btn-success" href="editaccountrole?Id=${Ar.getId()}">Sửa</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div id="accountPagination"></div>
							</div>							
						</div>
					</div>				
				</div>
			</div>
			<!--Right-->
			<div class="col-md-12 col-lg-3">
				<div class="row">
					<!-- col-12 -->
					<div class="col-md-12">
						<div class="tile">
							<h3 class="tile-title">Vai trò</h3>
							<div>
								<table class="table table-bordered" id="customerTable">
									<thead>
										<tr>
											<th>Mã</th>
											<th>Tên</th>											
											<th>Chức năng</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="R" items="${Role}">
											<tr>
												<td>${R.getRoleid()}</td>
												<td>${R.getRolename()}</td>												
												<td><a onclick="return confirm('Bạn có muốn xóa không?')"
											class="btn btn-danger" href="deleterole?Roleid=${R.getRoleid()}">Xóa</a>
											<a class="btn btn-success" href="editrole?Roleid=${R.getRoleid()}">Sửa</a></td>
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
	</main>
	<!-- Essential javascripts for application to work-->
	<script src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/DeleteProduct.js" />"></script>
	<script src="<c:url value="/resources/js/DeleteCategory.js" />"></script>
	<script src="<c:url value="/resources/js/DeleteRole.js" />"></script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
	<script src="<c:url value="/resources/js/admin.js" />"></script>
	
	<!-- The javascript plugin to display page loading on top-->

	<script src="<c:url value="/resources/js/plugins/pace.min.js" />"></script>
	<!-- Page specific javascripts-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	<!-- Data table plugin-->
	<script type="text/javascript"
		src="<c:url value="/resources/js/plugins/jquery.dataTables.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/plugins/dataTables.bootstrap.min.js" />"></script>
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
		//In dữ liệu
		var myApp = new function() {
			this.printTable = function() {
				var tab = document.getElementById('sampleTable');
				var win = window.open('', '', 'height=700,width=700');
				win.document.write(tab.outerHTML);
				win.document.close();
				win.print();
			}
		}		
		//Modal
		$("#show-emp").on("click", function() {
			$("#ModalUP").modal({
				backdrop : false,
				keyboard : false
			})
		});
	</script>
</body>

</html>
