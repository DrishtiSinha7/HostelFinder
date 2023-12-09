<%@page import="Com.DB.DBConnect"%>
<%@page import="java.util.List"%>
<%@page import="Com.entity.Hostels"%>
<%@page import="Com.dao.hostelDAO"%>
<%@page import="java.util.ArrayList"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User: View Jobs</title>
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
				<h5 class="text-center text-primary">All Hostels</h5>
				<%
				String loc = request.getParameter("loc");
				String cat = request.getParameter("cat");
				String msg = "";
				
				hostelDAO dao = new hostelDAO (DBConnect.getConn());
				
				List<Hostels> list = null;
				
				if ("lo".equals(loc) && "ca".equals(cat)){
				  list = new ArrayList<Hostels>();
				  msg = "Not Available";  //has not chosen anything
				} else if ("lo".equals(loc) || "ca".equals(cat)) {
					
				  list = dao.getJobsORLocationAndCate(cat, loc);
				} else {
					
				list = dao.getJobsAndLocationAndCate(cat, loc);
				
				}if(list.isEmpty())
					{
				%>
				<h4 class="text-center text-danger">Hostel Not Available</h4>
				<%
				}
				if(list != null){
					
				for (Hostels h: list) {
				%>
				<div class="card mt-2">
					<div class="card-body">
						<div class="text-center text-primary">
							<i class="far fa-clipboard fa-2x"></i>
						</div>
						<h6><%=h.getName()%></h6>
						<p><%=h.getDescription()%></p>
						<br>
						<div class="form-row">
							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Location: <%=h.getLocation()%>" readonly>
							</div>

							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Category: <%=h.getCategory()%>" readonly>
							</div>
						</div>
						<h6>
							Publish Date:
							<%=h.getPdate().toString()%></h6>

						<div class="text-center">
							<a href="one_view.jsp?id=<%=h.getId()%>"
								class="btn btn-sm bg-success text-white">View Details</a>
						</div>
					</div>
				</div>
				<%
               }
 }else {
	 %>
				<h4 class="text-center text-danger"><%=msg%></h4>
				<%
 }
%> 
			</div>
		</div>
	</div>

</body>
</html>