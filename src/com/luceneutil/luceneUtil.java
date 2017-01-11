package com.luceneutil;
import static com.luceneutil.Loadluceneconfig.*;
import java.io.File;
import java.io.IOException;


import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
//lucene接口类，用于连接lucene的index
public class luceneUtil {
	@SuppressWarnings("unused")
	public static IndexSearcher getSearch(){
		IndexSearcher search=null;
		Version version = Version.LUCENE_4_9;
		Directory directory=null;
		DirectoryReader ireader = null;
		 try {
			 String dirposition=(getluceneValue("dirposition"));
			 System.out.println(dirposition);
			 directory = FSDirectory.open(new File(dirposition));
	            if(ireader==null) {
	                ireader = DirectoryReader.open(directory);
	            } else {
	                DirectoryReader tr = DirectoryReader.openIfChanged(ireader) ;
	                if(tr!=null) {
	                    ireader.close();
	                    ireader = tr;
	                }
	            }
	            return new IndexSearcher(ireader);
	        } catch (CorruptIndexException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return search;
	}
	public static IKAnalyzer getAnalyzer(){
            return new IKAnalyzer();
	}

}
