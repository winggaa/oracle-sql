package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import oracle.jdbc.proxy.annotation.Pre;
import vo.Emp;

public class EmpDAO {
	
		// q007join.jsp
		public static ArrayList<HashMap<String, Object>> joinEmp() throws Exception{
			ArrayList<HashMap<String,Object>> list = new ArrayList<>();
			Connection conn = DBhelper.getConnection();
			String sql = "select e1.empno , e1.ename , e1.grade , nvl(e2.ename, '관리자없음') \"mgrName\" , nvl(e2.grade , 0 ) \"mgrGrade\""
					+ " from emp e1 left outer join emp e2 on e1.mgr = e2.empno order by e1.empno asc";			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(); 
			
			while(rs.next()) {
				HashMap<String,Object> m = new HashMap<>();
				m.put("empno", rs.getInt("empno"));
				m.put("ename", rs.getString("ename"));
				m.put("grade", rs.getInt("grade"));
				m.put("mgrName", rs.getString("mgrName"));
				m.put("mgrGrade", rs.getInt("mgrGrade"));
				list.add(m);
			}
			conn.close();
			return list;
		}
	
	
	
	
	
	
		// q006GroupBy.jsp
		public static ArrayList<HashMap<String,Integer>> selectEmpSalStats() throws Exception{
			ArrayList<HashMap<String,Integer>> list = new ArrayList<>();
			Connection conn = DBhelper.getConnection();
			String sql = "select grade , count(*) count , sum(sal) sum , avg(sal) avg , max(sal) max , min(sal) min"
					+ " from emp group by grade order by grade asc";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Integer> m = new HashMap<>();
				m.put("grade", rs.getInt("grade"));
				m.put("count", rs.getInt("count"));
				m.put("sum", rs.getInt("sum"));
				m.put("avg", rs.getInt("avg"));
				m.put("max", rs.getInt("max"));
				m.put("min", rs.getInt("min"));
				list.add(m);
			}
					
			conn.close();
			return list;
		}
	
	
	
	
	
		// q005 OrderBy.jsp
		public static ArrayList<Emp> selectEmpListSort(String col, String sort) throws Exception{
				
				
				// 매개값 디버깅
				System.out.println(col + "<-- EmpDAO.selectEmpListSort param col");
				System.out.println(sort + "<-- ");
				
				ArrayList<Emp> list = new ArrayList<>();
				Connection conn = DBhelper.getConnection();
				
				String sql = "select empno, ename from emp"; 
				if(col != null && sort != null) {
					sql = sql + "order by" + col + " " + sort;
				}
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Emp e = new Emp();
					e.setEmpNo(rs.getInt("empno"));
					e.setEname(rs.getString("ename"));
					list.add(e);
				}
				/*
				동적쿼리(쿼리문자열이 매개값에 분기되어 차이가 나는 경우)
				ename ASC
				ename DESC
				*/
			conn.close();
			return list;
		}
		
	
		public static ArrayList<Emp> selectEmpListByGrade (ArrayList<Integer> ckList) throws Exception {
			Connection conn = DBhelper.getConnection();
			ArrayList<Emp> list = new ArrayList<>();
			String sql = "select ename ,grade from emp where grade IN ";
			PreparedStatement stmt = null;
			
			if(ckList.size() == 1) {
				sql = sql + "(?)";
				stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1, ckList.get(0));
			} else if(ckList.size() == 2) {
				sql = sql + "(?,?)";
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ckList.get(0));
				stmt.setInt(2, ckList.get(1));
			} else if(ckList.size() == 3) {
				sql = sql + "(?,?,?)";
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ckList.get(0));
				stmt.setInt(2, ckList.get(1));
				stmt.setInt(3, ckList.get(2));
			} else if(ckList.size() == 4) {
				sql = sql + "(?,?,?,?)";
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ckList.get(0));
				stmt.setInt(2, ckList.get(1));
				stmt.setInt(3, ckList.get(2));
				stmt.setInt(4, ckList.get(3));
				
			} else if(ckList.size() == 5) {
				sql = sql + "(?,?,?,?,?)";
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, ckList.get(0));
				stmt.setInt(2, ckList.get(1));
				stmt.setInt(3, ckList.get(2));
				stmt.setInt(4, ckList.get(3));
				stmt.setInt(5, ckList.get(4));
			}
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Emp e = new Emp();
				e.setEname(rs.getString("ename"));
				e.setGrade(rs.getInt("grade"));
				list.add(e);
			}
			conn.close();
			return list;
		}
					
	
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
			conn.close();
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
			conn.close();
			return list;
		}

}
