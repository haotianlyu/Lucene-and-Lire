package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Bean.filmBean;
import com.database.util.DBUtil;

public class RecommendDaoWithInterface extends DBUtil implements DetailFactoryDao {
	public filmBean findOne(int id){
		Connection conn=null;
		filmBean film=null;
		ResultSet rs1,rs2 = null;
        PreparedStatement pstmt1,pstmt2 = null;
        try{
        	int idnum=0;
        	String name="default";
        	String photoid="default.jpg";
        	System.out.println("connection ready to start");
			conn=getConn();
			String sql1 = "select * from project.film_info where id="+id;
	        String sql2 = "select * from project.photo where film_id="+id;
	        System.out.println(sql1);
	        System.out.println(sql2);
			pstmt1 = conn.prepareStatement(sql1);
            rs1 = pstmt1.executeQuery();
            if(rs1.next()){
            idnum=rs1.getInt(1);
            name=rs1.getString(3);
            }
            else{
            	idnum=0;
            	name="default";
            }
            pstmt2 = conn.prepareStatement(sql2);
            rs2 = pstmt2.executeQuery();
            if(rs2.next()){
            photoid=rs2.getString(1);
            }
            else{
            	photoid="default.jpg";
            }
            film=new filmBean(idnum,name,photoid);
            rs1=null;
            rs2=null;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConn(null,conn);
		}
		
		return film;	
	}
}
