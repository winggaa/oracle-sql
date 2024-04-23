<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>

<%
	ArrayList<Emp> list = null;
	String[] ck = request.getParameterValues("ck");
	if(ck == null 	){
		System.out.println(ck + "<-- ck.length");	
	} else{
		System.out.println(ck.length + " <-- ck.length");
		ArrayList<Integer> ckList = new ArrayList<>();
		for(String s : ck){
			ckList.add(Integer.parseInt(s));
		}
		list = EmpDAO.selectEmpListByGrade(ckList);
		System.out.println(list.size() +"<<"); 
	}
	
%>


<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title></title>
</head>
<body>
   <h1>EMP GRADE 검색</h1>
   <form action="./q004WhereIn.jsp" method="post">
      GRADE : 
      <%
         for(int i=1; i<6; i=i+1) {
      %>
            <input name="ck" type="checkbox" value="<%=i%>"><%=i%>
      <%      
         }
      %>
      <br>
      <button type="submit">검색</button>
   </form>
   
   <hr>
   
   <H1>결과 View</H1>
   <%
    if(ck == null){ // list == null
    		return; // ck가 null이면 아무것도 출력없이 끝
    }
   %>
   
   <table>
   	<tr>
   		<th>ename</th>
   		<th>grade</th>
   	</tr>
   </table>
   <%
   	for(Emp e : list){
   %>
   		<tr>
   			<td><%=e.getEname()%></td>
   			<td><%=e.getGrade()%></td>
   		</tr>
   
   <%
   	}
   %>
   
   
   
  
   
</body>
</html>