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
		
		Elements elements=this.document.getElementsByClass("resource-gallery-detail").get(0)
			.getElementsByClass("media-library-links-list");
		
		for(Element element:elements) {
			System.out.println(element.toString());
		}
		
		return null;
	}
	
	public String getTitle() {
		return this.document.getElementsByClass("section-header__title").get(0).text().strip();

	}
	
	
	public ArrayList<String> getVideoTags() {
		Elements keywordTags=document.getElementsByClass("resource-gallery-detail").get(0)
				.getElementsByClass("keyword-tag");
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
		this.document.getElementsByClass("resource-gallery-detail").get(0)
			.getElementsByClass("col-md-8").get(0)
			.getElementsByClass("keyword-tag").remove();
		
		String info = this.document.getElementsByClass("col-md-8").text();
		info=info.strip();
		
		if(info.startsWith("About This Image")) {
			info=info.substring(16,info.length());
		}
		if(info.endsWith("Keywords:")) {
			info=info.substring(0,info.length()-9);
		}
		return info.strip();
		
	}
}

