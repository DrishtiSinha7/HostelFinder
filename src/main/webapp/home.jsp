<%@page import="Com.entity.Hostels"%>
<%@page import="java.util.List"%>
<%@page import="Com.dao.hostelDAO"%>
<%@page import="Com.DB.DBConnect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User : View Hostel</title>
<%@include file="all_component/all_css.jsp"%>
</head>
<body style="background-color: #B5CB99;">
 
 <c:if test="${empty userobj }">
		<c:redirect url="login.jsp" />
	</c:if>

	<%@include file="all_component/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h5 class="text-center text-primary" style="color: black;">All Active Hostels</h5>

				<div class="card">
					<div class="card-body">
						<form class="form-inline" action="more_view.jsp" method="get">
							<div class="form-group col-md-5 mt-1">
								<h5>Location</h5>
							</div>

							<div class="form-group col-md-4 mt-1">
								<h5>Category</h5>
							</div>

							<div class="form-group col-md-5">
								<select name="loc" class="custom-select "
									id="inlineFormCustomSelectPref">
									<option selected value="lo">Choose...</option>
									<option value="BIDHOLI">BIDHOLI</option>
									<option value="KANDOLI">KANDOLI</option>
									<option value="NANDA KI CHOWKI">NANDA KI CHOWKI</option>
									<option value="DONGA">DONGA</option>
									<option value="PONDA">PONDA</option>
								</select>
							</div>

							<div class="form-group col-md-5">
								<select class="custom-select " id="inlineFormCustomSelectPref"
									name="cat">
									<option value="ca" selected>Choose...</option>
									<option value="Girls Hostel">Girls Hostel</option>
									<option value="Boys Hostel">Boys Hostel</option>
									<option value="Flates">Flates</option>
									<option value="PG">PG</option>
								</select>
							</div>
							<button class="btn btn-success">Submit</button>
						</form>
					</div>
				</div>

				<div class="row mt-3">
					<% hostelDAO dao = new hostelDAO(DBConnect.getConn()); List<Hostels> list = dao.getAllHostelsForUser(); for (Hostels h : list) { %>
					<div class="col-md-4 mb-3">
						<div class="card">
							<div class="card-body">
								<div class="text-center text-primary">
									<i class="far fa-clipboard fa-2x"></i>
								</div>
								<h6><%=h.getName()%></h6>
								<% if (h.getDescription().length() > 0 && h.getDescription().length() < 50) { %>
								<p><%=h.getDescription()%>.
								</p>
								<% } else { %>
								<p><%=h.getDescription().substring(0, 50)%>...
								</p>
								<% } %>

								<div class="form-row">
									<div class="form-group col-md-12">
										<input type="text" class="form-control form-control-sm"
											value="Location: <%=h.getLocation()%>" readonly>
									</div>

									<div class="form-group col-md-12">
										<input type="text" class="form-control form-control-sm"
											value="Category: <%=h.getCategory()%>" readonly>
									</div>
								</div>
								<h6>
									Publish Date:
									<%=h.getPdate().toString()%></h6>
								<div class="text-center">
									<a href="one_view.jsp?id=<%=h.getId()%>"
										class="btn btn-sm bg-success text-white">View More</a>
								</div>
							</div>
						</div>
					</div>
					<% } %>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
