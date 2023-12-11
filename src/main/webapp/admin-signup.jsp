<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sign Up</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- MATERIAL DESIGN ICONIC FONT -->
		<link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

		<!-- STYLE CSS -->
		<link rel="stylesheet" href="css/style.css">
	</head>

	<body>

		<div class="wrapper" style="background-image: url('images/bg-registration-form-2.jpg');">
			<div class="inner">
				<form action="admin" method="post">
					<h3>SIGNUP FORM</h3>
					<div class="form-wrapper">
						<label for="firstname">First Name</label>
						<input type="text" id="firstname" name="firstname" class="form-control">
					</div>
					<div class="form-wrapper">
						<label for="lastname">Last Name</label>
						<input type="text" id="lastname" name="lastname" class="form-control">
					</div>
						<div class="form-wrapper">
							<label for="username">User Name</label>
							<input type="text" id="username" name="username" class="form-control">
						</div>
					<div class="form-wrapper">
						<label for="email">Email</label>
						<input type="text" id="email" name="email" class="form-control">
					</div>
					<div class="form-wrapper">
						<label for="password">Password</label>
						<input type="password" id="password" name="password" class="form-control">
					</div>
					<div class="checkbox">
						<label>
							<input name="checkbox" type="checkbox"> I accept the Terms of Use & Privacy Policy.
							<span class="checkmark"></span>
						</label>
					</div>
					<input hidden="hidden" name="adminlogin" value="adminlogin">
					<button type="submit">Sign Up</button>
					<div style="margin-top: 4rem; text-align: center;" class="flex-col-c p-t-155">
    						<span class="txt1 p-b-17">
      							 Already a member?
   								 </span>
						<a href="login.jsp" class="txt2">
							Login Here</a>
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