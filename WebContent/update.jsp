<%@page import="com.rados.model.Film"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>Podaci o filmu</h2>
		</div>
	</div>

	<div id="container">
		<h3>Podaci o filmu</h3>
		<form action="FilmoviControllerServlet" method="get">
			<input type="hidden" name="command" value="UPDATE"/>
			 <input	type="hidden" name="idFilma" value="${F.idFilma}" />
			 
			<table>
			
				<tbody>
					<tr>
						<td><label>Naslov: </label></td>
						<td><input type="text" name="naslov" value="${F.naslov}"
							/></td>
					</tr>

					<tr>
						<td><label>Trajanje: </label></td>
						<td><input type="text" name="trajanje"
							value="${F.trajanje}" /></td>
					</tr>
					<tr>
						<td><label>Godina izdavanja: </label></td>
						<td><input type="text" name="godina" value="${F.godina}"
							/></td>
					</tr>
					<tr>
						<td><label>Zanr: </label></td>
						<td><input type="text" name="zanr" value="${F.zanr}"
							/></td>
					</tr>
					<tr>
						<td><label>AKA: </label></td>
						<td><input type="text" name="aka" value="${F.aka}"
							/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Update" class="save"></td>
					</tr>
				</tbody>
			</table>
		</form>
		