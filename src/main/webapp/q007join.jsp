<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>

<%
	ArrayList<HashMap<String,Object>> list = EmpDAO.joinEmp();


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table border=1>
	
		<tr>
			<td>ename</td>
			<td>empno</td>
			<td>grade</td>
			<td>mgrName</td>
			<td>mgrGrade</td>
		</tr>
		
		<% for(HashMap<String,Object> m : list ) {%>
		<!-- object to String to int  -->
						<!-- (int)  -->
		<% int grade = Integer.parseInt((String.valueOf(m.get("grade"))));%>
		<% int mgrGrade = Integer.parseInt((String.valueOf(m.get("mgrGrade"))));%> 
			<tr>
			<td><%=m.get("ename")%></td>
			<td><%=m.get("empno") %></td>
			<td>
			<% for(int i = 0 ; i < grade ; i++){
			
			%>
			&#127803;
			<%} %>
			</td>
			<td><%=m.get("mgrName") %></td>
			<td>
			<% for(int i = 0 ; i < mgrGrade ; i++){
			
			%>
			&#127804;
			<%} %>
			</td>
			
		</tr>
		
		<%} %>
	</table>
</body>
</html>