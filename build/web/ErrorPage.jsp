<%-- 
    Document   : ErrorPage
    Created on : Dec 9, 2023, 9:37:15 AM
    Author     : USER
--%>

<%@page language="java" contentType="text/html; charSet-UTF-8"  pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error: <%=exception.getMessage()%></h1>
        <h1>Hello World!</h1>
    </body>
</html>
