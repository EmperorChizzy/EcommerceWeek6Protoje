<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>LogInForm_Emperor_Chizzy</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- MATERIAL DESIGN ICONIC FONT -->
		<link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">
		
		<!-- STYLE CSS -->
		<link rel="stylesheet" href="css/style.css">
	</head>

	<body>

		<div class="wrapper" style="background-image: url('images/bg-registration-form-2.jpg');">
			<div class="inner">
				<form action="user" method="post">
					<label>
						<input hidden="hidden" name="login" value="login">
					</label>
					<h3>LOGIN FORM</h3>
					<div class="form-wrapper">
						<label for="email">Email</label>
						<input type="text" id="email" name="email" class="form-control">
					</div>
					<div class="form-wrapper">
						<label for="password">Password</label>
						<input type="password" id="password" name="password" class="form-control">
					</div>
					<button>Login</button>
					<div style="margin-top: 4rem; text-align: center;" class="flex-col-c p-t-155">
    						<span class="txt1 p-b-17">
      							 Or Sign Up
   								 </span>
						<a href="sign-up.jsp" class="txt2">
							Here</a>
					</div>
					<div style="margin-top: 4rem; text-align: center;" class="flex-col-c p-t-155">
						<a href="index.jsp" class="txt2">
							Back to Home</a>
					</div>
				</form>
			</div>
		</div>
		
	</body>
</html>