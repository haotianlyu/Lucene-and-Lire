package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.util.DBUtil;

public class UserDao extends DBUtil{
	public boolean testuser(String username){
		boolean exist=false;
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String sql="select * from project.user where username="+"'"+username+"'";
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				exist=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConn(null,conn);
		}
		return exist;
	}
	
	public void insertdata(String username,String password){
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into project.user set username="+"'"+username+"'"+",password="+"'"+password+"'";
	try{
		conn=getConn();
		pstmt=conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		closeConn(null,conn);
	}
	}
	
	public String login(String username){
		String password=null;
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		String sql="select * from project.user where username="+"'"+username+"'";
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				password=rs.getString(2);
				System.out.println(password);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConn(null,conn);
		}
		return password;
	}
}
