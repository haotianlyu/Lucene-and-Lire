package dao;
//图片搜索的主要实现类，通过lire的操作，找到最相似的图片，并将相似度和图片地址返回
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.global.CEDD;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

import com.Bean.SimilarityBean;

public class LireDao {
	public List<SimilarityBean> LireSearch(String directory) throws IOException{
		List<SimilarityBean> list=new ArrayList<SimilarityBean>();
		double xxx=0;
		String namestring=null;
        BufferedImage img = null;  
        boolean passed = false;   
            File f = new File(directory);  
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

		IndexReader ir = DirectoryReader.open(FSDirectory.open(new File("D://websearch//lucene//Project//WebContent//index1//Lireindex")));  
        ImageSearcher searcher =new GenericFastImageSearcher(15,CEDD.class);
   
        ImageSearchHits hits = searcher.search(img, ir);  
        for (int i = 0; i < hits.length(); i++) {  
            String fileName = ir.document(hits.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];  
            String[] xx=fileName.split("\\\\");
            String finalpart=xx[xx.length-1];
    		SimilarityBean sim=new SimilarityBean(hits.score(i), finalpart);
            list.add(sim);
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
        System.out.println("The most similar picture is "+namestring+",and its difference is"+xxx);

		return list;
		
	}
	
}
