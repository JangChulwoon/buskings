<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	
	<!--  Jquery source -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<!-- facebook -->
	<script type="text/javascript" src="resources/js/facebookscript.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>
<a onclick="checkLoginState();"> Test</a>

<P>  The time on the server is ${serverTime}. </P>



	
	
</body>
</html>
