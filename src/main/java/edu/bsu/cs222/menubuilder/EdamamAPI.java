package edu.bsu.cs222.menubuilder;

import com.jayway.jsonpath.DocumentContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class EdamamAPI {
    private final String userAgent = "aulew728@gmail.com";
    private final String urlInput = "google.com/search?q=lasagna";
    public DocumentContext gSearch(URL val) throws IOException {
        System.out.println("1");
        URLConnection connection = val.openConnection();
        connection.setRequestProperty("User-Agent", userAgent);
        InputStream html = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(html));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            String title = inputLine.substring(inputLine.indexOf("<a>") + 6, inputLine.indexOf("</a"));
            // if has tag, store in array.
            // show array.
            System.out.println(title);
        }
        in.close();
        return null;
    }

}

