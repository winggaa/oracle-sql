<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*"%>
<%@ page import = "java.util.*" %>

<% ArrayList<HashMap<String,Integer>> list = EmpDAO.selectDeptNoCnList();



%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<select>
<%
	for(HashMap<String,Integer> m : list){
		 
		 %>
		 
		 <option value="<%=m.get("deptno")%>"><%=m.get("deptno")%>(<%=m.get("cnt")%>)명</option>
		 
	 <%
		}
	 %>
	 
	 </select>
</body>
</html>