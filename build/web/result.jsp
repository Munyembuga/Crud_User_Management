<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <a href="https://www.javaguides.net" class="navbar-brand"> User
                        Management App </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list"
                           class="nav-link">Users</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Users</h3>
                <hr>
                <form action="<%=request.getContextPath()%>/search" method="GET">
    <input type="text" name="id" placeholder="Search by Name">
    <button type="submit">Search</button>
</form>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/addNew" class="btn btn-success">Add
                        New User</a>
                </div>
                <br>
                <c:if test="${not empty searchResults}">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Namee</th>
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
                    </c:if>
            </div>
        </div>
    </body>
</html>
