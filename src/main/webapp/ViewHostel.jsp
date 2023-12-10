<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@page import="java.util.List"%>
<%@page import="Com.entity.Hostels"%>
<%@page import="Com.DB.DBConnect"%>
<%@page import="Com.dao.hostelDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin View Hostel</title>
<%@include file="all_component/all_css.jsp"%>
</head>
<body style="background-color: #B5CB99">
	<c:if test="${userobj.role ne 'admin'}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>
	<%@include file="all_component/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<h5>All Hostels</h5>

				<c:if test="${not empty succMsg }">
					<div class="alert alert-success" role="alert">${ succMsg}</div>
					<c:remove var="succMsg" />
				</c:if>



				<%
				hostelDAO dao = new hostelDAO(DBConnect.getConn());
				List<Hostels> list = dao.getAllHostels();

				for (Hostels h : list) {
				%>
				<div class="card mt-2">
					<div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<div class="text-center text-primary">
									<i class="far fa-clipboard fa-2x"></i>
								</div>

								<h6><%=h.getName()%></h6>
								<p><%=h.getDescription()%></p>
								<p>
									Location:
									<%=h.getLocation()%></p>
								<p>
									Category:
									<%=h.getCategory()%></p>
								<p>
									Rooms Available:
									<%=h.getStatus()%></p>
								<h6>
									Publish Date:
									<%=h.getPdate()%></h6>
							</div>

							<div class="col-md-6">
								<div>
									<img src="images/<%=h.getImageurl()%>" alt="Hostel Image"
										style="max-width: 100%;">
								</div>
							</div>
						</div>

						<div class="text-center mt-3">
							<a href="edit_hostel.jsp?id=<%=h.getId()%>"
								class="btn btn-sm bg-success text-white">Edit</a> <a
								href="delete?id=<%=h.getId()%>"
								class="btn btn-sm bg-danger text-white">Delete</a>
						</div>
					</div>
				</div>
				<%
  }
%>



			</div>
		</div>
	</div>

</body>
</html>