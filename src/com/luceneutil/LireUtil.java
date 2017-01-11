package com.luceneutil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

public class LireUtil {
	public void dosearch(){ 
	double xxx=0;
	String namestring=null;
    BufferedImage img = null;  
    boolean passed = false;   
    File f = new File("D://test//errorpage1.jpg");  
        if (f.exists()) {  
            try {  
                img = ImageIO.read(f);  
                passed = true;  
            } catch (IOException e) {  
                e.printStackTrace();    
            }  
        }    
    if (!passed) {  
        System.out.println("No image given as first argument.");  
        System.out.println("Run \"Searcher <query image>\" to search for <query image>.");  
        System.exit(1);  
    }  
 try{
    IndexReader ir = DirectoryReader.open(FSDirectory.open(new File("")));  
    ImageSearcher searcher =new GenericFastImageSearcher(1,CEDD.class);

    ImageSearchHits hits = searcher.search(img, ir);  
    for (int i = 0; i < hits.length(); i++) {  
        String fileName = ir.document(hits.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];  
        if(i==0){
        	xxx=hits.score(0);
        	namestring=fileName;
        }
        else{
        	if(xxx>=hits.score(i)){
        		xxx=hits.score(i);
        		namestring=fileName;
        	}
        }
        System.out.println(hits.score(i) + ": \t" + fileName);  
    }
 }catch(Exception e){
	 e.printStackTrace();
 }
    System.out.println("The most similar picture is "+namestring+",and its difference is"+xxx);
}
	public static void main(String[] args){
		LireUtil aa =new LireUtil();
		aa.dosearch();
	}
}
