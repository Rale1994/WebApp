<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.rados.model.Film"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filtrirani filmovi</title>

<%
	ArrayList<Film> film = (ArrayList<Film>) request.getAttribute("FILTER");
%>
</head>
<body>
<h1>Filtrirani filmovi</h1>
<form action="FilmoviControllerServlet" method="get">
<input type="hidden" name="command" value="FILTER"/>

	<table style="width: 70%" border="1">
		<tr>
			<th>Naslov</th>
			<th>Trajanje</th>
			<th>Godina izdavanja</th>
			<th>Zanr</th>
			<th>AKA</th>
		</tr>
		<%
			for (Film f : film) {
		%>
		<tr>
			<td width="50%"><%=f.getNaslov()%></td>
			<td><%=f.getTrajanje()%></td>
			<td><%=f.getGodina()%></td>
			<td><%=f.getZanr()%></td>
			<td width="50%"><%=f.getAka()%></td>
		</tr>
 
		<%
			}
		%>
	</table>
	<br>
	</form>
	<br>
	<p>
		<a href="FilmoviControllerServlet"> Nazad na pocetnu</a>
	</p>
</body>
</html>