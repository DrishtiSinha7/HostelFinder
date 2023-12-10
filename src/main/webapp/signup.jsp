<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignUp Page</title>
<%@include file="all_component/all_css.jsp"%>
<script>
    function updateQualificationVisibility() {
        var role = document.getElementById("exampleInputRole").value;
        var qualificationField = document.getElementById("qualificationField");

        if (role === "HostelAdministration") {
            qualificationField.style.display = "none";
        } else {
            qualificationField.style.display = "block";
        }
    }
    document.addEventListener("DOMContentLoaded", function() {
        updateQualificationVisibility();
    });
</script>
</head>
<style>
.btn.btn-primary {
	background-color: #186F65;
	margin-top: 20px;
}
</style>
<body style="background-color: #B5CB99">
	<%@include file="all_component/navbar.jsp"%>

	<div class="container-fluid">
		<div class="row p-5">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<div class="text-center">
							<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
							<h5>Registration</h5>
						</div>
						
						<c:if test="${not empty succMsg }"> 
						<h4 class="text-center text-success">${succMsg}</h4>
						</c:if>

						<form action="add_user" method="post">
							<div class="from-group">
								<label>Enter Full Name</label>
								<input type="text" required="required" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="name">
							</div>

							<div class="from-group">
								<label>Enter Qualification</label>
								<input type="text" required="required" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="qua" id="qualificationField">
							</div>

							<div class="from-group">
								<label>Enter Email</label>
								<input type="email" required="required" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
							</div>

							<div class="from-group">
								<label for="exampleInputPassword1">Enter Password</label>
								<input required="required" type="password" class="form-control" id="exampleInputPassword1" name="ps">
							</div>

							<div class="from-group">
								<label for="exampleInputRole">Select Role</label>
								<select class="form-control" id="exampleInputRole" name="role" onchange="updateQualificationVisibility()">
									<option value="user">User</option>
									<option value="HostelAdministration">Hostel Administration</option>
									<!-- Add more roles if needed -->
								</select>
							</div>

							<button type="submit" class="btn btn-primary badge-pill btn-block">Register</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
