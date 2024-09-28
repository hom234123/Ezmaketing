<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Thông báo | Ezmarketing V2.0</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--css-->
<link
	href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/vendor/animate/animate.css" />"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/vendor/select2/select2.min.css" />"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/css/util.css" />" rel="stylesheet"
	type="text/css">
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="<c:url value ="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css" /> ">
<link
	href="<c:url value="/resources/vendor/css-hamburgers/hamburgers.min.css" />"
	rel="stylesheet" type="text/css">

<!--link -->
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>


<link href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
<link rel="stylesheet"
	href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

</head>

<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="text-center p-t-70 txt2">
				<h1>${msg}!</h1>
					<h2>Xin lỗi vì sự bất tiện mời bạn trở về</h2>
					<c:url value='/login' var="loginUrl" />
					<h2 class="text-center" ><a href="${loginUrl}">Đăng nhập</a></h2>
				</div>				
				<div class="text-center p-t-70 txt2">
					Ezmaketing <i class="far fa-copyright" aria-hidden="true"></i>
				</div>
			</div>
		</div>
	</div>
	<!--===============================================================================================-->
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/resources/vendor/jquery/jquery-3.2.1.min.js" />"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/resources/vendor/bootstrap/js/popper.js" />"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js" />"></script>
	<!--===============================================================================================-->
	<script
		src="<c:url value="/resources/vendor/select2/select2.min.js" />"></script>
	<!--===============================================================================================-->

</body>
</html>