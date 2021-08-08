package hubblesite;

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
		return this.detailRow.getElementsByClass("col-md-4").get(0).text();
	}
}
