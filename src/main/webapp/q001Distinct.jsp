<%@page import="vo.Emp"%>
<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>

<%
	ArrayList<Integer> list = EmpDAO.selectDeptnoList();
	
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<select name="deptNo">
		<option value="">:::선택:::</option>
		<%
		
			for(Integer e: list){	
		%>
			<option value="<%=e%>"><%=e%></option>
		<%
			}
		%>
	</select>
	
	
</body>
</html>