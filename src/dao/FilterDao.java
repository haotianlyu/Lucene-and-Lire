package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.util.Version;

import com.luceneutil.luceneUtil;

public class FilterDao extends luceneUtil{
	public List<String> MultisearchByterm(String[] fields,String[] keywords){
	    IndexSearcher indexsearcher=null;
	    List<String> idset=new ArrayList<String>();
	    boolean exist=false;
try{
	    System.out.println("start");
	    Query query=null;  	
	    indexsearcher = getSearch();
        Analyzer analyzer =  getAnalyzer();
        int length=fields.length;
        BooleanClause.Occur[] clauses=new BooleanClause.Occur[length];
        for(int i=0;i<length;i++){
        	clauses[i]=BooleanClause.Occur.MUST;
        }
	try {
		System.out.println("query");
        query=MultiFieldQueryParser.parse(Version.LUCENE_4_9, keywords, fields, clauses, analyzer);
        ScoreDoc[] hits=indexsearcher.search(query, 15).scoreDocs;
        for(int i=0;i<hits.length;i++){
        	Document doc = indexsearcher.doc(hits[i].doc);
        	exist=false;
        	if(idset.contains(doc.get("id"))){
        			exist=true;
        	}
        	if(!exist){
            idset.add(doc.get("id"));
        	}
        
        }
	
	} catch (IOException e) {
    	   System.out.println("failed1");
           e.printStackTrace();
    }catch (ParseException e) {
    	   System.out.println("failed2");
           e.printStackTrace();
       }
	
}catch(Exception e){
	e.printStackTrace();
}
        System.out.println(idset.size()); 
		return idset;
}

}
