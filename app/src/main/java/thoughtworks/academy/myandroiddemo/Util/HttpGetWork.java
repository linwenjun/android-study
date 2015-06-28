package thoughtworks.academy.myandroiddemo.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpGetWork {
    public static String loadBaidu() throws IOException {
        URL url = new URL("http://www.google.com");
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String str;
        StringBuffer sb = new StringBuffer();

        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }
}
