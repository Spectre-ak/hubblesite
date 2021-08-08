package hubblesite;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImgResourceDetails {
	Document document;
	public ImgResourceDetails(Document document) {
		this.document=document;
	}
	
	public String getImageTitle() {
		Element element=document.getElementsByClass("section-header__title").get(0);
		return element.text();
	}
}
