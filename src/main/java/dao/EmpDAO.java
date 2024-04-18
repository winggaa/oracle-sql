package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import oracle.jdbc.proxy.annotation.Pre;
import vo.Emp;

public class EmpDAO {
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
				e.ename = rs.getString("ename");
				e.sal = rs.getDouble("sal");
				e.empNo = rs.getInt("empNo");
				list.add(e);
			}
			
			return list;
		}

}
