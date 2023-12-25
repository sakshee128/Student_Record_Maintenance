<!DOCTYPE html>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<html>
<head>
<title>Students Information</title>
</head>
<body>
<h1>Students Information</h1>
<%
try
{
	Connection con=null;
Class.forName("com.mysql.jdbc.Driver");
con=(Connection)DriverManager.getConnection(
"jdbc:mysql://localhost/internship","root","2002");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select * from student;");

%>
<table border=1 style="text-align:center">
<thead>
<tr>
<th>Id</th>
<th>Name</th>
<th>Gender</th>
<th>CGPA</th>
</tr>
</thead>
<tbody>
<%while(rs.next())
{
%>
<tr>
<td><%=rs.getString(1) %></td>
<td><%=rs.getString(2) %></td>
<td><%=rs.getString(3) %></td>
<td><%=rs.getString(4) %></td>
</tr>
<%}%>
</tbody>
</table><br>
<%}
catch(Exception e){
out.print(e.getMessage());%><br><%
}
%>
</body>
</html>