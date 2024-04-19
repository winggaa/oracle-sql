package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Dept;

public class DeptDAO {
	//vo 사용
	public static ArrayList<Dept> selectDeptList() throws Exception{
		
		ArrayList<Dept> list = new ArrayList<>();
		Connection conn = DBhelper.getConnection();
		String sql="SELECT deptno deptNo, dname, loc FROM dept";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(); 
		while(rs.next()) {
			Dept d = new Dept();
			d.setDeptNo(rs.getInt("deptNo"));
			d.setDname(rs.getString("dname"));
			d.setLoc(rs.getString("loc"));
			
			list.add(d);
		}
		
		return list;
	}
	
	public static ArrayList<HashMap<String,Object>> selectDeptOnOffList() throws Exception{
		
		ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		Connection conn = DBhelper.getConnection();
		String sql ="SELECT deptno deptNo , dname , loc , 'ON' onOff FROM dept";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(); 
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("dname", rs.getString("dname"));
			m.put("loc", rs.getString("loc"));
			m.put("ON", rs.getString("onOff"));
			
			list.add(m);
		}
		
		return list;
	}
}
