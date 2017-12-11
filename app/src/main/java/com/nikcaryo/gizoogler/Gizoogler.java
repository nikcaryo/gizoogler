package com.nikcaryo.gizoogler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by nik on 12/10/17.
 */

public class Gizoogler {
    public static String gizoogle(String text){
        String URL = "http://gizoogle.net/textilizer.php";

        Document doc = new Document("");
        try{
            doc = Jsoup.connect(URL).data("translatetext", text).post();
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }

        System.out.print(doc.toString());

        String full = doc.toString();
        String text_gizoogled = full.substring(full.indexOf("250px") + 19, full.indexOf("div id=\"button\"")-7);


        System.out.println(text_gizoogled);
        return text_gizoogled;

    }


}
