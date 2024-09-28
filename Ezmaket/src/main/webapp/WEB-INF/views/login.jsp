<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<title>Ezmaketing | V2.0</title>
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
	href="<c:url value ="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />">
<link
	href="<c:url value="/resources/vendor/css-hamburgers/hamburgers.min.css" />"
	rel="stylesheet" type="text/css">

<!--link -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

<link href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="<c:url value="/resources/images/team.jpg" />" alt="">
				</div>
				<!--=====TIÊU ĐỀ======-->
				<div class="login100-form validate-form">
					<span class="login100-form-title"> <b>ĐĂNG NHẬP HỆ THỐNG
							EZ</b>

					</span> <span style="color: red">${msg}</span>

					<!--=====FORM INPUT TÀI KHOẢN VÀ PASSWORD======-->

					<form name='loginForm' action="<c:url value='loginProcess'/>"
						method="POST">
						<div class="wrap-input100 validate-input">
							<input class="input100" autocomplete="off" type="text"
								placeholder="Nhập tên tài khoản" name="username" id="username">
							<span class="focus-input100"></span> <span
								class="symbol-input100"> <i class='bx bx-user'></i>
							</span>
						</div>
						<div class="wrap-input100 validate-input">
							<input autocomplete="off" id="password" class="input100"
								type="password" placeholder="Mật khẩu" name="password">
							<span class="symbol-input100"> <i class='bx bx-key'></i>
							</span>
						</div>
						<div class="container-login100-form-btn">
							<input type="submit" value="Đăng nhập" id="submit"
								onclick="submit" />
						</div>
						<div class="text-right p-t-12">
							<a class="txt2" href='<c:url value="/forgot"/>'>Bạn quên mật
								khẩu?</a>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
				<!--=====FOOTER======-->
				<div class="text-center p-t-70 txt2">
					Ezmaketing <i class="far fa-copyright" aria-hidden="true"></i>
				</div>
			</div>
		</div>
	</div>
	<!--Javascript-->
	<script
		src="<c:url value="/resources/vendor/jquery/jquery-3.2.1.min.js" />"></script>
	<script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
	<script
		src="<c:url value="/resources/vendor/bootstrap/js/popper.js" />"></script>
	<script
		src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js" />"></script>
	<script
		src="<c:url value="/resources/vendor/select2/select2.min.js" />"></script>

</body>
</html>

