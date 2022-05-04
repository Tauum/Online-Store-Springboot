<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Store</title>
</head>
<body>

<h1>Online Store</h1>

<a href ="/display">Click to Display</a></br>

${message }

</br>

<form action="/addUser">

	Your user name: <input type ="text" name ="usrname">
	Your Password: <input type ="text" name ="pass">
	<input type = "submit" value = "Click Me!">
</form>

${myuser }
</body>
</html>