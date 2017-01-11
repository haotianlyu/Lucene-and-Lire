package com.Bean;
//电影类
public class filmBean {
	private int id;
	private String url;
	private String name; 
	private String director;
	private String writer;
	private String mainactors;
	private String type;
	private String country;
	private String language;
	private String date;
	private String time;
	private String rename;
	private Float score;
	private int commentsnum;
	private String percent;
	private String brief;
	private String award;
	private String website;
	private String photoid;
	
	public filmBean(int id,String name,String photoid){
		this.id=id;
		this.name=name;
		this.photoid=photoid;
	}
	
	public filmBean(int id,String url,String name,String director,String writer,String mainactors,
	                String type,String country,String language,String date,String time,String rename,
	                Float score,int commentsnum,String percent,String brief,String award,String website){
		this.id=id;
		this.url=url;
		this.name=name;
		this.director=director;
		this.writer=writer;
		this.mainactors=mainactors;
		this.type=type;
		this.country=country;
		this.language=language;
		this.date=date;
		this.time=time;
		this.rename=rename;
		this.score=score;
		this.commentsnum=commentsnum;
		this.percent=percent;
		this.brief=brief;
		this.award=award;
		this.website=website;
	}
	
	public filmBean(int id,String url,String name,String director,String writer,String mainactors,
            String type,String country,String language,String date,String time,String rename,
            Float score,int commentsnum,String percent,String brief,String award,String website,String photoid){
this.id=id;
this.url=url;
this.name=name;
this.director=director;
this.writer=writer;
this.mainactors=mainactors;
this.type=type;
this.country=country;
this.language=language;
this.date=date;
this.time=time;
this.rename=rename;
this.score=score;
this.commentsnum=commentsnum;
this.percent=percent;
this.brief=brief;
this.award=award;
this.website=website;
this.photoid=photoid;
}
	
	public filmBean(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMainactors() {
		return mainactors;
	}
	public void setMainactors(String mainactors) {
		this.mainactors = mainactors;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRename() {
		return rename;
	}
	public void setRename(String rename) {
		this.rename = rename;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public int getCommentsnum() {
		return commentsnum;
	}
	public void setCommentsnum(int commentsnum) {
		this.commentsnum = commentsnum;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getAward() {
		return award;
	}
	public void setAward(String award) {
		this.award = award;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void clear(){
		this.id=-1;
		this.url=null;
		this.director=null;
		this.writer=null;
		this.mainactors=null;
		this.type=null;
		this.country=null;
		this.language=null;
		this.date=null;
		this.time=null;
		this.rename=null;
		this.score=-1.0f;
		this.commentsnum=-1;
		this.percent=null;
		this.brief=null;
		this.award=null;
		this.website=null;
	}
	public String getPhotoid() {
		return photoid;
	}
	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}
}
