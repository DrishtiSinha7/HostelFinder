<%@page import="Com.entity.Hostels"%>
<%@page import="Com.DB.DBConnect"%>
<%@page import="Com.dao.hostelDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Hostels</title>
<%@include file="all_component/all_css.jsp"%>
</head>
<body style="background-color: #B5CB99">
	<c:if test="${userobj.role ne 'admin'}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>
	<%@include file="all_component/navbar.jsp"%>

	<div class="container p-2">
		<div class="col-md-10 offset-md-1">
			<div class="card">
				<div class="card-body">
					<div class="text-center text-success">
						<i class="fas fa-user-friends fa-3x"></i>


						<%
						int id = Integer.parseInt(request.getParameter("id"));
						hostelDAO dao = new hostelDAO(DBConnect.getConn());
						Hostels h = dao.getHostelsById(id);
						%>


						<h5>Edit Hostels</h5>
					</div>
					<form action="update" method="post">

						<input type="hidden" value="<%=h.getId()%>" name="id">
						<div class="form-group">
							<label>Hostel Name</label> <input type="text" name="title"
								required class="form-control" value="<%=h.getName()%>">
						</div>
						<div class="form-row">
							<div class="form-group col-md-4">
								<label>Location</label> <select name="location"
									class="custom-select" id="inlineFormCustomSelectPref">
									<option value="<%=h.getLocation()%>"><%=h.getLocation()%></option>
									<option value="BIDHOLI">BIDHOLI</option>
									<option value="KANDOLI">KANDOLI</option>
									<option value="NANDA KI CHOWKI">NANDA KI CHOWKI</option>
									<option value="DONGA">DONGA</option>
									<option value="PONDA">PONDA</option>
								</select>

							</div>
							<div class="form-group col-md-4">
								<label>Category</label> <select class="custom-select"
									id="inlineFromCustomerSelectPref" name="category">
									<option value="<%=h.getCategory()%>"><%=h.getCategory()%></option>
									<option value="Girls Hostel">Girls Hostel</option>
									<option value="Boys Hostel">Boys Hostel</option>
									<option value="Flates">Flates</option>
									<option value="PG">PG</option>
								</select>
							</div>
							<div class="form-group col-md-4">
								<label>Rooms avaliable</label> <select class="form-control"
									name="Rooms avaliable">
									<option class="active" value="<%=h.getStatus()%>"><%=h.getStatus()%></option>
									<option class="active" value="avaliable">Active</option>
									<option class="Inactive" value="Inavaliable">Inactive</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label>Enter Description</label>
							<textarea required rows="6" cols="" name="desc"
								class="form-control"><%=h.getDescription()%></textarea>
						</div>
						<button class="btn btn-success">Update Hostel</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>