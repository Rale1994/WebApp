<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Movie trailer </title>
</head>
<body>
Ovaj film trenutno nema dosptupan trailer<br>
Mozete ubaciti trailer unosenjem linka video u polje ispod<br>
<br>
<br>
<form action="FilmoviControllerServlet" method="get">
<input type="hidden" name="command" value="ADDTRAILER"/>
	 <input	type="hidden" name="idFilma" value="${fil.idFilma}" />		
<input type="text" name="linkT">
<input type="submit" value="Insert">
</form>
</body>
</html>