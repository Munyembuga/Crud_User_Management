<%--<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>User Management Application</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
        <%@include file="navBar.jsp" %>

  
        <br>

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Users</h3>
                <hr>
                <form action="<%=request.getContextPath()%>/search" method="GET">
    <input type="text" name="name" placeholder="Search by Name">
    <button type="submit">Search</button>
</form>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/addNew" class="btn btn-success">Add
                        New User</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Country</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                     
                        <c:forEach var="user" items="${searchResults}">

                            <tr>
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
            </div>
        </div>
    </body>
</html>
