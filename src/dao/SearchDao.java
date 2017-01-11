package dao;
//基本搜索类，拥有三个方法，第一个是返回总的信息数
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.util.Version;

import com.Bean.filmBean;
import com.luceneutil.luceneUtil;

public class SearchDao extends luceneUtil{
	
	public int Totalnum(String field,String keyword){
		IndexSearcher indexsearcher=null;
		Query query=null;
		List<String> idset=new ArrayList<String>();
	    boolean exist=false;
		try{
			indexsearcher=getSearch();
			Analyzer analyzer = getAnalyzer();
			QueryParser qp = new QueryParser(Version.LUCENE_4_9,
		               field,analyzer);
		       qp.setDefaultOperator(QueryParser.OR_OPERATOR);
		       try {
		    	   System.out.println("try come in");
		    	   query=qp.parse(keyword);
		           ScoreDoc[] hits;
		           System.out.println("get something");
		           hits = indexsearcher.search(query, null, 100).scoreDocs;
		           System.out.println("hits get value");
		           System.out.println(hits.length);
		           for (int i = 0; i < hits.length; i++) {
		        	   Document doc=indexsearcher.doc(hits[i].doc);
		        	   exist=false;
			        if(idset.contains(doc.get("id"))){
			        			exist=true;
			        }
			        if(!exist){
			            idset.add(doc.get("id"));
			        } 
		           }       
		           System.out.println("try finished");
		       } catch (IOException e) {
		    	   System.out.println("failed1");
		           e.printStackTrace();
		       } catch (ParseException e) {
		    	   System.out.println("failed2");
		           e.printStackTrace();
		       }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return idset.size();
	}
	
	public List<String> SearchByTerm(String field,String keyword){
		IndexSearcher indexsearcher=null;
		Query query=null;
		List<String> idset=new ArrayList<String>();
	    boolean exist=false;
		try{
			indexsearcher=getSearch();
			Analyzer analyzer = getAnalyzer();
			QueryParser qp = new QueryParser(Version.LUCENE_4_9,
		               field,analyzer);
		       qp.setDefaultOperator(QueryParser.OR_OPERATOR);
		       try {
		    	   System.out.println("try come in");
		    	   query=qp.parse(keyword);
		           ScoreDoc[] hits;
		           System.out.println("get something");
		           hits = indexsearcher.search(query, null, 100).scoreDocs;
		           System.out.println("hits get value");
		           System.out.println(hits.length);
		           for (int i = 0; i < hits.length; i++) {
		        	   Document doc=indexsearcher.doc(hits[i].doc);
		        	   exist=false;
			           if(idset.contains(doc.get("id"))){
			        	 exist=true;
			           }
			           if(!exist){
			             idset.add(doc.get("id"));
			           } 
		           }       
		           System.out.println("try finished");
		       } catch (IOException e) {
		    	   System.out.println("failed1");
		           e.printStackTrace();
		       } catch (ParseException e) {
		    	   System.out.println("failed2");
		           e.printStackTrace();
		       }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
 		return idset;		
	}
	
	public List<String> SearchByPage(String field,String keyword,int firstindex,int endindex){
		IndexSearcher indexsearcher=null;
		Query query=null;
		List<String> idset=new ArrayList<String>();
	    boolean exist=false;
		try{
			indexsearcher=getSearch();
			Analyzer analyzer = getAnalyzer();
			QueryParser qp = new QueryParser(Version.LUCENE_4_9,
		               field,analyzer);
		       qp.setDefaultOperator(QueryParser.OR_OPERATOR);
		       try {
		    	   System.out.println("try come in");
		    	   query=qp.parse(keyword);
		           ScoreDoc[] hits;
		           System.out.println("get something");
		           hits = indexsearcher.search(query, null, 100).scoreDocs;
		           System.out.println("hits get value");
		           System.out.println(hits.length);
		           for (int i = firstindex; i <=endindex; i++) {
		        	   Document doc=indexsearcher.doc(hits[i].doc);
		        	   exist=false;
			           if(idset.contains(doc.get("id"))){
			        	 exist=true;
			           }
			           if(!exist){
			             idset.add(doc.get("id"));
			           } 
		           }       
		           System.out.println("try finished");
		       } catch (IOException e) {
		    	   System.out.println("failed1");
		           e.printStackTrace();
		       } catch (ParseException e) {
		    	   System.out.println("failed2");
		           e.printStackTrace();
		       }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
 		return idset;		
	}
	
	public List<String> HotSearchByPage(String field,String keyword,int firstindex,int endindex){
		IndexSearcher indexsearcher=null;
		Query query=null;
		List<String> idset=new ArrayList<String>();
	    boolean exist=false;
		try{
			indexsearcher=getSearch();
			Analyzer analyzer = getAnalyzer();
			QueryParser qp = new QueryParser(Version.LUCENE_4_9,
		               field,analyzer);
		       qp.setDefaultOperator(QueryParser.OR_OPERATOR);
		       try {
		    	   System.out.println("try come in");
		    	   query=qp.parse(keyword);
		           ScoreDoc[] hits;
		           System.out.println("get something");
		           Sort sorts=Sort.RELEVANCE;
		           SortField[] sortfield=sorts.getSort();
		           SortField relevantsort=sortfield[0];
		           Sort sort=new Sort(new SortField[] {relevantsort, new SortField("commentsnum", Type.INT,true)});
		           hits = indexsearcher.search(query, null, 100,sort).scoreDocs;
		           System.out.println("hits get value");
		           System.out.println(hits.length);
		           for (int i = firstindex; i <=endindex; i++) {
		        	   Document doc=indexsearcher.doc(hits[i].doc);
		        	   exist=false;
			           if(idset.contains(doc.get("id"))){
			        	 exist=true;
			           }
			           if(!exist){
			             idset.add(doc.get("id"));
			           } 
		           }       
		           System.out.println("try finished");
		       } catch (IOException e) {
		    	   System.out.println("failed1");
		           e.printStackTrace();
		       } catch (ParseException e) {
		    	   System.out.println("failed2");
		           e.printStackTrace();
		       }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
 		return idset;		
	}
	
	public List<String> HighSearchByPage(String field,String keyword,int firstindex,int endindex){
		IndexSearcher indexsearcher=null;
		Query query=null;
		List<String> idset=new ArrayList<String>();
	    boolean exist=false;
		try{
			indexsearcher=getSearch();
			Analyzer analyzer = getAnalyzer();
			QueryParser qp = new QueryParser(Version.LUCENE_4_9,
		               field,analyzer);
		       qp.setDefaultOperator(QueryParser.OR_OPERATOR);
		       try {
		    	   System.out.println("try come in");
		    	   query=qp.parse(keyword);
		           ScoreDoc[] hits;
		           System.out.println("get something");
		           Sort sorts=Sort.RELEVANCE;
		           SortField[] sortfield=sorts.getSort();
		           SortField relevantsort=sortfield[0];
		           Sort sort=new Sort(new SortField[] {relevantsort, new SortField("score", Type.FLOAT,true)});
		           hits = indexsearcher.search(query, null, 100,sort).scoreDocs;
		           System.out.println("hits get value");
		           System.out.println(hits.length);
		           for (int i = firstindex; i <=endindex; i++) {
		        	   Document doc=indexsearcher.doc(hits[i].doc);
		        	   exist=false;
			           if(idset.contains(doc.get("id"))){
			        	 exist=true;
			           }
			           if(!exist){
			             idset.add(doc.get("id"));
			           } 
		           }       
		           System.out.println("try finished");
		       } catch (IOException e) {
		    	   System.out.println("failed1");
		           e.printStackTrace();
		       } catch (ParseException e) {
		    	   System.out.println("failed2");
		           e.printStackTrace();
		       }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
 		return idset;		
	}
	
	public List<String> MultisearchByPage(String[] fields,String[] keywords,int firstindex,int endindex){
		    IndexSearcher indexsearcher=null;
		    List<String> idset=new ArrayList<String>();
		    boolean exist=false;
	try{
		    System.out.println("start");
		    Query query=null;  	
		    indexsearcher = getSearch();
	        Analyzer analyzer =  getAnalyzer();
	        BooleanClause.Occur[] clauses={BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD
	        		,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
	        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
	        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
	        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
		try {
			System.out.println("query");
	        query=MultiFieldQueryParser.parse(Version.LUCENE_4_9, keywords, fields, clauses, analyzer);
	        ScoreDoc[] hits=indexsearcher.search(query,null,100).scoreDocs;	        
	        for(int i=firstindex;i<=endindex;i++){
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

	public int MultisearchTotal(String[] fields,String[] keywords){
	    IndexSearcher indexsearcher=null;
	    List<String> idset=new ArrayList<String>();
	    boolean exist=false;
try{
	    System.out.println("start");
	    Query query=null;  	
	    indexsearcher = getSearch();
        Analyzer analyzer =  getAnalyzer();
        BooleanClause.Occur[] clauses={BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD
        		,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
	try {
		System.out.println("query");
        query=MultiFieldQueryParser.parse(Version.LUCENE_4_9, keywords, fields, clauses, analyzer);
        ScoreDoc[] hits=indexsearcher.search(query, 100).scoreDocs;
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
		return idset.size();
}
	
	public List<String> HotMultisearchByPage(String[] fields,String[] keywords,int firstindex,int endindex){
	    IndexSearcher indexsearcher=null;
	    List<String> idset=new ArrayList<String>();
	    boolean exist=false;
try{
	    System.out.println("start");
	    Query query=null;  	
	    indexsearcher = getSearch();
        Analyzer analyzer =  getAnalyzer();
        BooleanClause.Occur[] clauses={BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD
        		,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
	try {
		System.out.println("query");
        query=MultiFieldQueryParser.parse(Version.LUCENE_4_9, keywords, fields, clauses, analyzer);
        Sort sorts=Sort.RELEVANCE;
        SortField[] sortfield=sorts.getSort();
        SortField relevantsort=sortfield[0];
        Sort sort=new Sort(new SortField[] {relevantsort, new SortField("commentsnum", Type.INT,true)});
        ScoreDoc[] hits=indexsearcher.search(query,null,100,sort).scoreDocs;	        
        for(int i=firstindex;i<=endindex;i++){
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
	
	
	public List<String> HighMultisearchByPage(String[] fields,String[] keywords,int firstindex,int endindex){
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
        	clauses[i]=BooleanClause.Occur.SHOULD;
        };
	try {
		System.out.println("query");
        query=MultiFieldQueryParser.parse(Version.LUCENE_4_9, keywords, fields, clauses, analyzer);
        Sort sorts=Sort.RELEVANCE;
        SortField[] sortfield=sorts.getSort();
        SortField relevantsort=sortfield[0];
        Sort sort=new Sort(new SortField[] {relevantsort, new SortField("score", Type.FLOAT,true)});
        ScoreDoc[] hits=indexsearcher.search(query,null,100,sort).scoreDocs;	        
        for(int i=firstindex;i<=endindex;i++){
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
	
	
	public List<String> MultisearchByterm(String[] fields,String[] keywords){
	    IndexSearcher indexsearcher=null;
	    List<String> idset=new ArrayList<String>();
	    boolean exist=false;
try{
	    System.out.println("start");
	    Query query=null;  	
	    indexsearcher = getSearch();
        Analyzer analyzer =  getAnalyzer();
        BooleanClause.Occur[] clauses={BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD
        		,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
	try {
		System.out.println("query");
        query=MultiFieldQueryParser.parse(Version.LUCENE_4_9, keywords, fields, clauses, analyzer);
        ScoreDoc[] hits=indexsearcher.search(query, 100).scoreDocs;
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
