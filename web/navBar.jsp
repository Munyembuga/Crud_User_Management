<%-- 
    Document   : navBar
    Created on : Dec 10, 2023, 11:55:30 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: #08C25E; align-items: center; display: flex; justify-content: center">
                <div>
                    <a href="" class="navbar-brand"> User
                        Management App </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list"
                           class="nav-link">Users</a></li>
                </ul>
                           <div style="">
            <a style="text-align: end; align-self: end; color:white " href="<%=request.getContextPath()%>/logout"> Logout</a>
        </div>

            </nav>

        </header>
        <br>
       
    </body>
</html>
