package luceneuse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.Bean.filmBean;

public class keywordsearch {
	private static final Version version = Version.LUCENE_4_9;
    private Directory directory = null;
    private DirectoryReader ireader = null;
    private IndexWriter iwriter = null;
    private IKAnalyzer analyzer;
    
    
    public keywordsearch() {
        try {
			directory = FSDirectory.open(new File("D://luceneindex"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public IndexSearcher getSearcher(){
        try {
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
        return null;
    }
    
    private IKAnalyzer getAnalyzer(){
        if(analyzer == null){
            return new IKAnalyzer();
        }else{
            return analyzer;
        }
    }
    
    public List searchByTerm(String field,String keyword,int num) throws InvalidTokenOffsetsException{
    	List<filmBean> list=new ArrayList<filmBean>();
    	filmBean newfilm=new filmBean();
        IndexSearcher isearcher = getSearcher();
        Analyzer analyzer =  getAnalyzer();
       //ʹ��QueryParser��ѯ����������Query����
       QueryParser qp = new QueryParser(version,
               field,analyzer);
       //Ч����
       qp.setDefaultOperator(QueryParser.OR_OPERATOR);
       try {
           Query query = qp.parse(keyword);
           ScoreDoc[] hits;

           hits = isearcher.search(query, null, num).scoreDocs;
           for (int i = 0; i < hits.length; i++) {
        	   newfilm.clear();
               Document doc = isearcher.doc(hits[i].doc);
               newfilm.setId(Integer.parseInt(doc.get("id")));
               newfilm.setUrl(doc.get("url"));
               newfilm.setName(doc.get("name"));
               newfilm.setDirector(doc.get("director"));
               newfilm.setWriter(doc.get("writer"));
               newfilm.setMainactors(doc.get("mainactors"));
               newfilm.setType(doc.get("type"));
               newfilm.setCountry(doc.get("country"));
               newfilm.setLanguage(doc.get("language"));
               newfilm.setDate(doc.get("date"));
               newfilm.setTime(doc.get("time"));
               newfilm.setRename(doc.get("rename"));
               newfilm.setScore(Float.parseFloat(doc.get("score")));
               newfilm.setCommentsnum(Integer.parseInt(doc.get("commentsnum")));
               newfilm.setPercent(doc.get("percent"));
               newfilm.setBrief(doc.get("brief"));
               newfilm.setAward(doc.get("award"));
               newfilm.setWebsite(doc.get("website"));
               list.add(newfilm);
           }         
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ParseException e) {
           e.printStackTrace();
       }
       return list;
   }
}
