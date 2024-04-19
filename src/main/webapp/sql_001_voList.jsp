<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>

<!-- Controller(컨트롤러) -->    
<%
	// DAO호출로 모델 반환
	ArrayList<Dept> deptList = DeptDAO.selectDeptList();
	ArrayList<Emp> empList = EmpDAO.selectEmptList();
	ArrayList<HashMap<String,Object>> deptOnOffList = DeptDAO.selectDeptOnOffList();
	ArrayList<HashMap<String,Object>> empAndDeptList = EmpDAO.selectEmpAndDept();
%>
    
    
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h1>Emp INNER JOIN Dept List</h1>
	<table>
		<tr>
		<td>empNo</td>
		<td>ename</td>
		<td>deptNo</td>
		<td>dname</td>
		</tr>
		
		
		<%
		for(HashMap<String, Object> ed: empAndDeptList){
		%>
		
		<tr>
			<td><%=(Integer) (ed.get("empNo"))%></td>
			<td><%=(String) (ed.get("ename"))%></td>
			<td><%=(Integer) (ed.get("deptNo"))%></td>
			<td><%=(String) (ed.get("dname"))%></td>
			
			
		</tr>
		<%
		}
		%>
		
		
	</table>




	<h1>Dept List</h1>
	<table border="1"></table>
	<tr>
		<td>depNo</td>
		<td>dname</td>
		<td>loc</td>
	</tr>
	<%
		for(Dept d : deptList) {
	%>
			<tr>
				<td><%=d.getDeptNo()%></td>
				<td><%=d.getDname()%></td>
				<td><%=d.getLoc()%></td>
			</tr>
<%
		}
%>
	<tr> 
		<td>ename</td>
		<td>sal</td>
		<td>empNo</td>
	</tr>
	<%
		for(Emp e : empList){
			
	%>

	<tr>
		<td><%=e.getEname() %></td>
		<td><%=e.getEmpNo() %></td>
		<td><%=e.getSal() %></td>
		
	</tr>
	<%
		}
	%>


</body>
</html>