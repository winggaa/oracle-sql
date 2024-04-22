package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import oracle.jdbc.proxy.annotation.Pre;
import vo.Emp;

public class EmpDAO {
		// q003Case.jsp
		public static ArrayList<HashMap<String,String>> selectJobCaseList() throws Exception{
			
			ArrayList<HashMap<String,String>> list = new ArrayList<>(); 
			Connection conn = DBhelper.getConnection();
			String sql = "select ename, job, case"
					 + " when job = 'PRESIDENT' then '빨강'"
					 + " when job = 'MANAGER' then '주황' "
					 + " when job = 'ANALYST' then '노랑'"
				     + " when job = 'CLERK' then '초록' else '파랑' end color from emp"
				     + " order by(case "
				     + " when color ='빨강' then 1 "
				     + " when color ='주황' then 2 "
				     + " when color ='노랑' then 3 "
				     + " when color ='초록' then 4 "
				     + " else 5 END) ASC";
			
			
				     
			PreparedStatement stmt = conn.prepareStatement(sql);	     
			ResultSet rs = stmt.executeQuery();	
			
			while(rs.next()) {
				HashMap<String,String> m = new HashMap<>();
				m.put("ename", rs.getString("ename"));
				m.put("job", rs.getString("job"));
				m.put("color", rs.getString("color"));
				
				list.add(m);
			}
			
			conn.close();
			return list;
		}
	
	
		//deptno 뒤에 부서별 인원 같이 조회하는 메서드
		public static ArrayList<HashMap<String, Integer>> selectDeptNoCnList() throws Exception{
			ArrayList<HashMap<String,Integer>> list = new ArrayList<>();
			
			Connection conn = DBhelper.getConnection();
			String sql = "select deptno deptNo, count(*) cnt from emp where deptno is not null group by deptno order by deptno asc";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HashMap<String,Integer> m = new HashMap<>();
				m.put("cnt", rs.getInt("cnt"));
				m.put("deptno", rs.getInt("deptNo"));
				list.add(m);
			}
			
			conn.close();
			return list;
		}
	
	
	
	
		// 중복을 제외한 deptno 목록을 출력하는 메서드
		public static ArrayList<Integer> selectDeptnoList() throws Exception{
			ArrayList<Integer> list = new ArrayList<>();
			
			Connection conn = DBhelper.getConnection();
			String sql = "select distinct deptno deptNo from emp where deptno is not null";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Integer i = rs.getInt("deptno");
				list.add(i);
			}
			
			conn.close();
			return list;
		}
	
	
		// 조인으로 Map을 사용하는 경우
		public static ArrayList<HashMap<String,Object>> selectEmpAndDept() throws Exception{
			
			ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			
			Connection conn = DBhelper.getConnection();
			String sql = "select emp.empno empNo , emp.ename ename, emp.deptno deptNo, dept.dname dname"
						+" from emp inner join dept ON emp.deptno = dept.deptno";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("empNo", rs.getInt("empNo"));
				m.put("ename", rs.getString("ename"));
				m.put("deptNo", rs.getInt("deptNo"));
				m.put("dname", rs.getString("dname"));
				list.add(m);
			}
			
			return list;
		}
	
	
		//vo 사용
		public static ArrayList<Emp> selectEmptList() throws Exception{
			ArrayList<Emp> list = new ArrayList<>();
			
			Connection conn = DBhelper.getConnection();
			String sql="select empno empNo, ename , sal from emp";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next()) {
				Emp e = new Emp();
				e.setEname(rs.getString("ename"));
				e.setSal(rs.getDouble("sal"));
				e.setEmpNo(rs.getInt("empNo"));
				
				list.add(e);
			}
			
			return list;
		}

}
