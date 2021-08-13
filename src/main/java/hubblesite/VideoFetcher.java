package hubblesite;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VideoFetcher {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//base url 
		//https://hubblesite.org/resource-gallery/videos?itemsPerPage=1000&page=1
		processLinks();
		
	}
	static void SaveVideoLinks() throws Exception{
		Document document=Jsoup.connect("https://hubblesite.org/resource-gallery/videos?itemsPerPage=1000&page=1").get();
		Elements elements=document.getElementsByClass("col-sm-4");
		
		JSONArray jsonArray=new JSONArray();
		for(Element element:elements) {
			String link=element.getElementsByTag("a").get(0).attr("href");
			System.out.println(link);
			jsonArray.put("https://hubblesite.org"+link);
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("links",jsonArray);
		
		FileWriter fileWriter=new FileWriter(new File("VideoLinksHubble.json"));
		fileWriter.write(jsonObject.toString(3));
		fileWriter.close();
	}
	
	static void processLinks() throws Exception{
		Scanner scanner=new Scanner(new File("VideoLinksHubble.json"));
		String videoLinksJSONstr="";
		while(scanner.hasNextLine()) {
			videoLinksJSONstr+=scanner.nextLine();
		}
		JSONObject jsonObject=new JSONObject(videoLinksJSONstr);
		JSONArray jsonArray=jsonObject.getJSONArray("links");
		
		int dataCount=0;
		
		JSONArray jsonArray2=new JSONArray();
		
		for(Object object:jsonArray) {
			try {
				dataCount++;
				
				String link=object.toString();
				System.out.println(dataCount);
		
			
				Document document=Jsoup.connect(link).get();
				
				VideoRescDetails videoRescDetails=new VideoRescDetails(document);
				
				JSONObject obj=new JSONObject();
				obj.put("title",videoRescDetails.getTitle());
				
				
				obj.put("title",videoRescDetails.getTitle());
				obj.put("date",videoRescDetails.getVideoReleaseDate());
				obj.put("tags",videoRescDetails.getVideoTags());
				obj.put("info",videoRescDetails.getInfo());
				obj.put("videoLinks",videoRescDetails.getDownloadOps());
				
				jsonArray2.put(obj);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		}
		
		FileWriter fwFileWriter=new FileWriter(new File("vidArray.json"));
		fwFileWriter.write(jsonArray2.toString(3));
		fwFileWriter.close();
		
		
		System.out.println("xxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Video Scrapping done");
		System.out.println("xxxxxxxxxxxxxxxxxxxxx");
	}
}
