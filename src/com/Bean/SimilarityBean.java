package com.Bean;
//Lire调用的相似度类
public class SimilarityBean {
	private double similarity;
	private String picturepath;
	
	public SimilarityBean(double similarity,String picturepath){
		this.similarity=similarity;
		this.picturepath=picturepath;
	}
	
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	public String getPicturepath() {
		return picturepath;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}
}
