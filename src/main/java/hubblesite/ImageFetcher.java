package hubblesite;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
	
		
		ProcessImgs();
		
	}
	
	static void SaveAllImgs() throws Exception{
		
		JSONObject jsonObject=new JSONObject();
		
		JSONArray jsonArray=new JSONArray();
		
		int totalSize=0;
		
		for(int i=1;i<=9;i++) {
			
			Document document=
					Jsoup.connect("https://hubblesite.org/resource-gallery/images?page="+i+"&itemsPerPage=600&").get();
		
			
			Elements elements=document.getElementsByClass("col-sm-4");
			for(Element element:elements) {
				jsonArray.put("https://hubblesite.org"+element.getElementsByTag("a").get(0).attr("href"));
			}
			
			totalSize+=jsonArray.length();
			
			System.out.println(totalSize);
			
			//test
			
			
		}
		
		jsonObject.put("links",jsonArray);
		
		System.out.println(jsonArray.length());
		
		FileWriter fwFileWriter=new FileWriter(new File("hubbleSiteImgs.json"));
		fwFileWriter.write(jsonObject.toString(3));
		fwFileWriter.close();
		
		
	}
	static void ProcessImgs() throws Exception {
		Scanner scanner=new Scanner(new File("hubbleSiteImgs.json"));
		String string="";
		while(scanner.hasNextLine()) {
			string+=scanner.nextLine();
		}
		JSONObject jsonObject=new JSONObject(string);
		
		JSONArray jsonArray=jsonObject.getJSONArray("links");
		
		
		
		for(Object obj:jsonArray) {
			
			String url=obj.toString();
			
			Document document=Jsoup.connect(url).get();
			
			ArrayList<String[]> imgsWithRes=getImgsLink(document);
			
			for(String ar[]:imgsWithRes) {
				System.out.println(Arrays.toString(ar));
			}
			
			//terminator-->test
			break;
		}
	}
	
	
	static ArrayList<String[]> getImgsLink(Document document) {
		
		ArrayList<String[]> list=new ArrayList<String[]>();
		
		
		Element imgOptionsList=document.getElementsByClass("media-library-links-list").get(0);
		
		Elements ImgLinks=imgOptionsList.getElementsByTag("a");
		
		
		for(Element element:ImgLinks) {
			String imgMetaData=(element.text());
			String imgInfos[]=imgMetaData.split(",");
			
			if(imgInfos[2].toLowerCase().contains("jpg")
					|| imgInfos[2].toLowerCase().contains("png")) {
				String linkAndRes[]=new String[2];
				
				linkAndRes[0]="https:"+element.attr("href");
				linkAndRes[1]=imgInfos[1].strip();
				
				list.add(linkAndRes);
			}
			
			System.out.println(imgMetaData);
		}
		
		return list;
	}

}
