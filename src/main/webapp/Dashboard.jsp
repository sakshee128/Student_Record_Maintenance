<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<style>


</style>
</head>
<body style="align-items:center;display:flex; justify-content:center;flex-direction:column;color:#31356e">
<h1 >
<%
	String name=(String)request.getAttribute("uname");
	String id=(String)request.getAttribute("id");
	String gender=(String)request.getAttribute("gender");
	String cgpa=(String)request.getAttribute("cgpa");
	String password=(String)request.getAttribute("pass");
	out.print("Welcome \n"+name);
 %></h1>
 <br>
 <table class=" rounded table table-primary"  style="text-align:center ; width:400px !important; border:1px solid white; border-radius:12px ">
 <tr class="col">  
 <td> Id</td>
 <td> <%out.println(id); %></td>
 </tr  >
 <tr  class="col">
 <td> Gender</td>
 <td> <%out.println(gender); %></td>
 </tr >
 <tr  class="col">
 <td> CGPA</td>
 <td> <%out.println(cgpa); %></td>
 </tr>
 </table>
 
 <br>
 <br>
 <a class="button btn btn-primary" href=Update_stud.jsp>Click here to update</a>
 
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</html>