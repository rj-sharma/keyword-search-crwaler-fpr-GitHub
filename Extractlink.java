import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Extractlink {

	public static void main(String[] args) throws SQLException, IOException {
		
		
		
		//******Enter your Keyword*********//
		 String Keyword = "temperature";
		 ///////////////////////////////
		
		 //*******Variables**********//
		String sql=null;
		DB db = new DB();
		int MaxLimit = 20;
		int count = 1;
		
		//********Repositry URL***********//
		String URL = "https://github.com/search?utf8=%E2%9C%93&q="+Keyword+"&type=";
		
		//deleting previous records
		db.runSql2("TRUNCATE Record;");
		
		Document doc = Jsoup.connect(URL).timeout(1000).get();
	
		
		Elements rows = doc.getElementsByClass("v-align-middle");
		for (Element row : rows){
		    System.out.println(count+" "+row.text());
		    count++;
		    
		    sql = "INSERT INTO  `Crawler`.`projectRecords` " + "(`ProjectName`) VALUES " + "(?);";
			PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, row.text());
			stmt.execute();
		    
		 // Exit program when system reaches max limit	   
	          if(count>MaxLimit)
	          {
	        	  System.out.println("Exiting");
	        	  System.exit(0) ;
	          }
		    
		}
		
		   
	
		//get all links and recursively call the processPage method
	         
		
		
	}
 
}
