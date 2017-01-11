package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Bean.filmBean;
import com.database.util.DBUtil;

public class LireinfoDao extends DBUtil{
	public filmBean findfilm(String photoid){
		Connection conn=null;
		filmBean film=null;
		ResultSet rs1,rs2 = null;
        PreparedStatement pstmt1,pstmt2 = null;
        String sql2 = "SELECT * FROM project.photo where photo_id="+"'"+photoid+"'";
		try{
			conn=getConn();
            pstmt2 = conn.prepareStatement(sql2);
            rs2 = pstmt2.executeQuery();
            if(rs2.next()){
            	int id=rs2.getInt(2);
            	String sql1="select * from project.film_info where id="+id;
            	pstmt1=conn.prepareStatement(sql1);
            	rs1=pstmt1.executeQuery();
            	if(rs1.next()){
            	film=new filmBean(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6)
                		,rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs1.getString(11),rs1.getString(12),
                		rs1.getFloat(13),rs1.getInt(14),rs1.getString(15),rs1.getString(16),rs1.getString(17)
                		,rs1.getString(18),rs2.getString(1));
            	}
            }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConn(null,conn);
		}
		
		return film;	
	}
}
