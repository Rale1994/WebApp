<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.rados.model.Film"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uneti Filmovi</title>


</head>
<body>

	<h1>Uneti filmovi</h1>
	<table style="width: 80%" border="3">
		<tr>
			<th>Naslov</th>
			<th>Trajanje</th>
			<th>Godina izdavanja</th>
			<th>Zanr</th>
			<th>AKA</th>
			<th>Trejleri</th>
			<th>Izmene</th>
			<th>Opis</th>

		</tr>
		<c:forEach var="f" items="${LISTA}">


			<c:url var="deleteLink" value="FilmoviControllerServlet">
				<c:param name="command" value="DELETE"></c:param>
				<c:param name="idFilma" value="${f.idFilma }"></c:param>

			</c:url>
			<c:url var="updateLink" value="FilmoviControllerServlet">
				<c:param name="command" value="LOAD"></c:param>
				<c:param name="idFilma" value="${f.idFilma }"></c:param>

			</c:url>

			<c:url var="trailerLink" value="FilmoviControllerServlet">
				<c:param name="command" value="TRAILER"></c:param>
				<c:param name="idFilma" value="${f.idFilma }"></c:param>

			</c:url>
			  
			<c:url var="opisLink" value="FilmoviControllerServlet">
			<c:param name="command" value="OPIS"></c:param>
			<c:param name="idFilma" value="${f.idFilma}"></c:param>
			</c:url>

			<tr>
				<td width="47%">${f.naslov}</td>
				<td>${f.trajanje}</td>
				<td>${f.godina}</td>
				<td>${f.zanr}</td>
				<td width="30%">${f.aka}</td>
				<td><a href="${trailerLink}">Trejler </a></td>
				<td width="10%"><a href="${deleteLink}"
					onclick="if(!(confirm('Are you sure want to delete this movie'))) return false">Delete</a>
					| <a><a href="${updateLink }">Update</a></td>
				<td><a href="${opisLink}">Opis</a></td>

			</tr>

		</c:forEach>
	</table>
	<br>
	<form action="FilmoviControllerServlet">
		<input type="submit" value="Filter" /> <input type="hidden"
			name="command" value="FILTER">
	</form>


	<br>
	<p>
		<a href="FilmoviControllerServlet"> Nazad na pocetnu</a>
	</p>
</body>
</html>