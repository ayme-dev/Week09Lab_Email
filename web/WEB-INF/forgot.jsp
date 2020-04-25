<%-- 
    Document   : forgot
    Created on : Apr 25, 2020, 3:55:58 PM
    Author     : aymen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <div>
            Please enter your email address to retrieve your password.
        </div>
        <div>
            <form method="POST">
                Email address: <input type="email" name="email"><br>
                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
