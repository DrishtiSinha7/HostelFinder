<%@page import="Com.DB.DBConnect"%>
<%@page import="Com.entity.Hostels"%>
<%@page import="Com.dao.hostelDAO"%>
<%@page import="java.util.List" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
     pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Single Hostel</title>
    <%@include file="all_component/all_css.jsp"%>
</head>
<body style="background-color: #B5CB99;">

   <c:if test="${empty userobj }">
      <c:redirect url="login.jsp" />
   </c:if>
   <%@include file ="all_component/navbar.jsp"%>
   <div class="container">
      <div class="row justify-content-center mt-5">
           <div class="col-md-8">
                 <%
                 int id = Integer.parseInt(request.getParameter("id"));
                 hostelDAO dao = new hostelDAO(DBConnect.getConn());
                 Hostels h = dao.getHostelsById(id);
                 %>
                 <div class="card">
                    <div class="card-body">
                       <div class="text-center text-primary">
                          <i class="far fa-clipboard fa-2X"></i>
                       </div>
                       
                       <h6><%=h.getName() %></h6>
                       <p><%=h.getDescription() %>.</p>
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
                       <h6>Publish Date: <%=h.getPdate().toString()%></h6>
                    </div>
                 </div>
           </div>  
      </div> 
   </div>
</body>
</html>
