<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>    
<%
	ArrayList<HashMap<String,String>> list = EmpDAO.selectJobCaseList();

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<select>
	<option value="">:::선택:::</option>
	<% for(HashMap<String,String> m : list ){
		%>
		<option value="<%=m.get("ename")%>"><%=m.get("ename")%><%=m.get("job")%><%=m.get("color") %></option>
		
		<%
	}
	 %>
	 
	 </select>
</body>
</html>