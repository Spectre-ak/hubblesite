package hubblesite;

import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageFetcher {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		URL url=new URL("https://hubblesite.org/resource-gallery/images");
//		HttpsURLConnection httpsURLConnection=(HttpsURLConnection) url.openConnection();
//		Scanner scanner=new Scanner(httpsURLConnection.getInputStream());
//		while(scanner.hasNextLine()) {
//			System.out.println(scanner.nextLine());
//		}
//		
		JSONObject jsonObject=new JSONObject();
		
		JSONArray jsonArray=new JSONArray();
		for(int i=1;i<=9;i++) {
			
			Document document=
					Jsoup.connect("https://hubblesite.org/resource-gallery/images?page="+i+"&itemsPerPage=600&").get();
		
			
			Elements elements=document.getElementsByClass("col-sm-4");
			System.out.println(elements.size());
			
			for(Element element:elements) {
				//System.out.println(element.getElementsByClass("text-overlay__center").get(0).text());
				//System.out.println(element.getElementsByTag("a").get(0).attr("href"));
				jsonArray.put("https://hubblesite.org"+element.getElementsByTag("a").get(0).attr("href"));
			}
			
			System.out.println(jsonArray.length());
			
			
			
			//test
			
			break;
		}
		
		jsonObject.put("links",jsonArray);
		
		System.out.println(jsonObject.toString(3));
		
		
		
		
		
	}

}
