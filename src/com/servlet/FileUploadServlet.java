package com.servlet;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.Bean.SimilarityBean;

import dao.LireDao;
//文件的上传类，通过接受图片来进行判断

public class FileUploadServlet extends HttpServlet {

 private static final long serialVersionUID = -7744625344830285257L;
 private ServletContext sc;
 private String savePath;

 public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
  doPost(request, response);
 }
 

 public void init(ServletConfig config) {
  // 在web.xml中设置的一个初始化参数
  savePath = config.getInitParameter("savePath");
  sc = config.getServletContext();
 }
 
 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  request.setCharacterEncoding("UTF-8");
  DiskFileItemFactory factory = new DiskFileItemFactory();
  ServletFileUpload upload = new ServletFileUpload(factory);
  try {
   List items = upload.parseRequest(request);
   Iterator itr = items.iterator();
   while (itr.hasNext()) {
    FileItem item = (FileItem) itr.next();
    if (item.isFormField()) {
     System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
    } else {
     if (item.getName() != null && !item.getName().equals("")) {
      System.out.println("上传文件的大小:" + item.getSize());
      System.out.println("上传文件的类型:" + item.getContentType());
      // item.getName()返回上传文件在客户端的完整路径名称
      System.out.println("上传文件的名称:" + item.getName());

      File tempFile = new File(item.getName());
      String filepath=request.getSession().getServletContext().getRealPath("/") + savePath+"\\"+item.getName();
      System.out.println(filepath);
      File file = new File(request.getSession().getServletContext().getRealPath("/") + savePath, tempFile.getName());
      item.write(file);
      LireDao lireuse=new LireDao();
      List<SimilarityBean> sim=new ArrayList<SimilarityBean>();
      System.out.println(sim.size());
      sim=lireuse.LireSearch(filepath); 
      request.setAttribute("simlist", sim);
      request.setAttribute("upload.message", "Upload successfully！");
     }else{
      request.setAttribute("upload.message", "Don't choose a file！");
     }
    }
   }
  }catch(FileUploadException e){
   e.printStackTrace();
  } catch (Exception e) {
   e.printStackTrace();
   request.setAttribute("upload.message", "上传文件失败！");
  }
  request.getRequestDispatcher("uploadResult.jsp").forward(request, response);
 }
}