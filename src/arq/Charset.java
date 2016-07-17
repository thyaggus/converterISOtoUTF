package arq;

import java.io.BufferedInputStream;
import java.net.URL;

import org.mozilla.intl.chardet.HtmlCharsetDetector;
import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;
import org.mozilla.intl.chardet.nsPSMDetector;

public class Charset {
	public static boolean found = false;

	public static void getCharset(String texto) {

		nsDetector det = new nsDetector(nsPSMDetector.ALL);

		det.Init(new nsICharsetDetectionObserver() {
			public void Notify(String charset) {
				Charset.found = true;
				System.out.println("CHARSET = " + charset);
				System.out.println("f;dfkjsfkjfklsajfaklfjasl;k");
			}
		});

		boolean isAscii = det.isAscii(texto.getBytes(), texto.getBytes().length);

		if (isAscii) {
			System.out.println("É ascii e isso não me diz nada!");
		} else {
			System.out.println(det.DoIt(texto.getBytes(), texto.getBytes().length, true));
		}
		det.DataEnd();
		
		if (isAscii) {
			found = true;
		}

		String prob[] = det.getProbableCharsets();
		
		for (int i = 0; i < prob.length; i++) {
			System.out.println((i+1) + " - Probable Charset = " + prob[i]);
		}
		System.out.println("Found: " + found);

	}

	public static void main(String argv[]) throws Exception {
		getCharset("Tiago Fraga");
	}

}
