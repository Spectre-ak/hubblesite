package hubblesite;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VideoRescDetails {

	private Document document;
	public VideoRescDetails(Document document) {
		this.document=document;
	}

	public ArrayList<String[]> getDownloadOps(){
		
		
		return null;
	}
	
	public String getTitle() {
		return this.document.getElementsByClass("section-header__title").get(0).text().strip();

	}
	
	
	public ArrayList<String> getVideoTags() {
		Elements keywordTags=document.getElementsByClass("keyword-tag");
		ArrayList<String> tagsArrayList=new ArrayList<String>();
		
		for(Element element:keywordTags) {
			tagsArrayList.add(element.text());
		}
		return tagsArrayList;
	}
	
	public String getVideoReleaseDate() {
		return this.document.getElementsByClass("col-md-4").get(0)
				.getElementsByTag("p").get(0).text().strip();
	}
	
	
	public String getInfo() {
		
		return null;
	}
}

