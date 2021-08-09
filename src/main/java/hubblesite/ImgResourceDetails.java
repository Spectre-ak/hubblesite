package hubblesite;

import java.awt.List;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImgResourceDetails {
	private Document document;
	private Element detailRow;
	public ImgResourceDetails(Document document) {
		this.document=document;
		detailRow=document.getElementsByClass("resource-gallery-detail").get(0);
	}
	
	public String getImageTitle() {
		Element element=this.document.getElementsByClass("section-header__title").get(0);
		return element.text();
	}
	
	public String getImageReleaseDate() {
		return this.detailRow.getElementsByClass("col-md-4").get(0)
				.getElementsByTag("p").get(0).text();
	}
	public ArrayList<String> getImageTags(){
		Elements elements=this.detailRow.getElementsByClass("col-md-8").get(0)
				.getElementsByClass("keyword-tag");
		ArrayList<String> keywordTags=new ArrayList<String>();
		
		for(Element element:elements) {
			keywordTags.add(element.text());
		}
		return keywordTags;
	}
}
