import com.google.api.GoogleAPI;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class Main {
	public static void main(String[]args) throws Exception{
		// Set the HTTP referrer to your website address.
	    GoogleAPI.setHttpReferrer("google.ca");

	    // Set the Google Translate API key
	    // See: http://code.google.com/apis/language/translate/v2/getting_started.html
	    GoogleAPI.setKey("");

	    String translatedText = Translate.DEFAULT.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);

	    System.out.println(translatedText);

	}
}
