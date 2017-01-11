package com.Bean;

public class CommentBean {
	private int commentid;
	private String username;
	private String comments;
	private String Date;
	private int filmid;
	
	public CommentBean(){		
	}
	public CommentBean(int commentid,String username,String comments,String Date,int filmid){
		this.commentid=commentid;
		this.username=username;
		this.comments=comments;
		this.Date=Date;
		this.filmid=filmid;
	}
	
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getFilmid() {
		return filmid;
	}
	public void setFilmid(int filmid) {
		this.filmid = filmid;
	}
}
