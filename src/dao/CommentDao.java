package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Bean.CommentBean;
import com.Bean.filmBean;
import com.database.util.DBUtil;

public class CommentDao extends DBUtil{
	public List<CommentBean> searchComment(int id){
		List<CommentBean> commentlist=new ArrayList<CommentBean>();
		Connection conn=null;
	    CommentBean comment=null;
		ResultSet rs = null;
        PreparedStatement pstmt= null;
        String sql = "select * from project.comments where filmid="+id;
		try{
			conn=getConn();
			pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
            	comment=new CommentBean(rs.getInt(1),rs.getString(2),rs.getString(3),
            			rs.getString(4),rs.getInt(5));
            	commentlist.add(comment);
            }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConn(null,conn);
		}
		return commentlist;
		
	}
	
	public List<CommentBean> searchAllComment(String username){
		List<CommentBean> commentlist=new ArrayList<CommentBean>();
		Connection conn=null;
	    CommentBean comment=null;
		ResultSet rs = null;
        PreparedStatement pstmt= null;
        String sql = "select * from project.comments where username='"+username+"'";
		try{
			conn=getConn();
			pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
            	comment=new CommentBean(rs.getInt(1),rs.getString(2),rs.getString(3),
            			rs.getString(4),rs.getInt(5));
            	commentlist.add(comment);
            }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConn(null,conn);
		}
		return commentlist;
		
	}
	
	public void addComment(int filmid,String username,String comments,String date){
		Connection conn=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmts=null;
		String sql="select max(commentid) from project.comments;";
		ResultSet rs = null;
		int max=0;
		try{
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				max=rs.getInt(1);
			}
			max=max+1;
			String sqls="insert into project.comments set commentid="+max+",username='"+username+"',comments='"+comments+"',Date='"+date+"',filmid="+filmid;
			pstmts=conn.prepareStatement(sqls);
			pstmts.executeUpdate();
			System.out.println("insert success");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConn(null,conn);
		}
	}
}
