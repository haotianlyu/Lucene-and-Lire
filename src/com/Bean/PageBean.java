package com.Bean;
//分页类
public class PageBean {
	private int currentPage;
	private int totalPages;
	int pageRecorder=15;
	private int totalinfo;
	private int pageStartindex;
	private int pageEndindex;
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	public PageBean(){		
	}
	public PageBean(int currentPage,int totalinfo){
		//当前页
		if(currentPage<1){
			this.currentPage=1;
		}
		else{
			this.currentPage=currentPage;
		}
		//总信息数
		if(totalinfo<1){
			System.out.println("error");
		}
		else{
			this.totalinfo=totalinfo;
		}
		//总页数
		if((totalinfo%pageRecorder)==0){
			totalPages=(totalinfo/pageRecorder);
		}
		else{
			totalPages=(totalinfo/pageRecorder)+1;
		}
		//页面开始数据编号
		this.pageStartindex=(this.currentPage-1)*pageRecorder;
		//页面结束数据编号
		if(this.currentPage<this.totalPages){
			this.pageEndindex=this.currentPage*pageRecorder-1;
		}
		else if(this.currentPage>this.totalPages){
			System.out.println("wrong currentPage");
		}
		else{
			this.pageEndindex=totalinfo-1;
		}
		//是否有上一页
		if(this.currentPage>1){
			this.hasPreviousPage=true;	
		}
		else{
			this.hasPreviousPage=false;
		}
		//是否有下一页
		if(this.currentPage<this.totalPages){
			this.hasNextPage=true;
		}
		else{
			this.hasNextPage=false;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageRecorder() {
		return pageRecorder;
	}
	public void setPageRecorder(int pageRecorder) {
		this.pageRecorder = pageRecorder;
	}
	public int getTotalinfo() {
		return totalinfo;
	}
	public void setTotalinfo(int totalinfo) {
		this.totalinfo = totalinfo;
	}
	public int getPageStartindex() {
		return pageStartindex;
	}
	public void setPageStartindex(int pageStartindex) {
		this.pageStartindex = pageStartindex;
	}
	public int getPageEndindex() {
		return pageEndindex;
	}
	public void setPageEndinfo(int pageEndindex) {
		this.pageEndindex = pageEndindex;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
}
