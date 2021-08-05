package hubblesite;

import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class ImageFetcher {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		URL url=new URL("https://hubblesite.org/resource-gallery/images");
		HttpsURLConnection httpsURLConnection=(HttpsURLConnection) url.openConnection();
		Scanner scanner=new Scanner(httpsURLConnection.getInputStream());
		while(scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
	}

}
