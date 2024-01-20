<%--<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>--%>
<%--<%@page import="net.java.usermanagement.model.SignUp"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>User Management Application</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <script src="//cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <style>
            .activeuser{
                font-size: 20px;
                background-color:#08C25E;

                border: 1px solid #ccc;
                width:120px;
                height: 80px;
                border-radius: 10px;
                margin-left: 30px;
                display: block;
                align-items: center;
                justify-content: center;
                justify-self: center;




            }
            .items{
                text-align: center;
                color:#fff;
                padding: 1px




            }
        </style>
    </head>

    <body>

        <%@include file="navBar.jsp" %>
        <br>
        <div class="" style="margin-left:20px;display:flex; text-align: center">
            <div style="">
                <i class="fa-solid fa-user" style="width:80px;height:80px;"></i>
               
            </div>
            <div class="" style="display: block;font-size: 12px; margin-left: 20px ">
                <%
                    net.java.usermanagement.model.SignUp credentialUserr = (net.java.usermanagement.model.SignUp) session.getAttribute("loggedInUser");
                    if (credentialUserr != null) {
                %>
                <p style="margin-top: -10px"><%=credentialUserr.getName()%></p>
<!--                <p style="margin-top: -10px"><%=credentialUserr.getEmail()%></p>-->
                <p style="margin-top: -10px"><%=credentialUserr.getPhone()%></p>


                <%
                } else {
                %>
                <p>No user logged in</p>
                <%
                    }
                %>
            </div>
        </div>
<!--        <div>
            <div class="activeuser" >
                <p class='items'>Active user</p>
                <p class='items'> <c:out value="${totalUser}" /></p>
            </div>
        </div>-->

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Users</h3>
                <hr>
                <div style="flex-direction:row; display: flex; justify-content:space-between">
                <form action="<%=request.getContextPath()%>/search" method="GET">
                    <input type="text" name="name" placeholder="Search by Name">
                    <button type="submit">Search</button>
                </form>
                    <div style=""> <p style="padding-top: 2px;padding-bottom: 2px;text-align: center; padding-left: 10px; background-color: black; border-radius:5px; padding-right: 10px; color: white"><a href="<%=request.getContextPath()%>/addNew">Add New</a></p></div>
                    </div>
                <div class="container text-left">

                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Number</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Country</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <c:set var="startIndex" value="${(currentPage - 1) * recordsPerPage + 1}" />

                    <tbody>

                        <c:forEach var="user" items="${listUser}" varStatus="loop">

                            <tr>
                                <td><c:out value="${startingNumber + loop.index}" /></td> 
                                <td><c:out value="${user.id}" /></td>
                                <td><c:out value="${user.name}" /></td>
                                <td><c:out value="${user.email}" /></td>
                                <td><c:out value="${user.country}" /></td>
                                <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a
                                        href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                            </tr>
                        </c:forEach>


                    </tbody>

                </table>
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="<%=request.getContextPath()%>/list?page=${currentPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${totalPages}" varStatus="page">
                            <li class="page-item <c:if test='${page.index == currentPage}'>active</c:if>'">
                                <a class="page-link" href="<%=request.getContextPath()%>/list?page=${page.index}">
                                    ${page.index}
                                </a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="<%=request.getContextPath()%>/list?page=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </body>
</html>
