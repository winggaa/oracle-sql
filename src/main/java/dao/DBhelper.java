package dao;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class DBhelper {
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		System.out.println("드라이브 로딩 성공");
		Connection conn = null;
		String dburl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbuser = "";
		// 보안이슈로 로컬에서 설정파일 불러오기
		FileReader fr = new FileReader("D:\\dev\\auth\\oracle.Properties");
		Properties prop = new Properties();
		prop.load(fr);
		dbuser = prop.getProperty("dbuser");
		String dbPw = prop.getProperty("dbPw");		
		System.out.println(dbuser+dbPw);
		conn = DriverManager.getConnection(dburl, dbuser, dbPw);
		return conn;
	}
	
	public static void main(String[] args) throws Exception{
		Connection conn = new DBhelper().getConnection();
		System.out.println(conn);
	}	
	
}
