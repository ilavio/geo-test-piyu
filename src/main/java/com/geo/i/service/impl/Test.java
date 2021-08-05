package com.geo.i.service.impl;

import com.geo.i.resource.ConnectionURL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Test {

  public static void main(String[] args) throws Exception {
    String str = "https://nominatim.openstreetmap.org/search?state=Самарская область&country=russia&format=json&polygon_geojson=1";
    String str2 = "http://www.oracle.com/";
    var urlCon = new ConnectionURL(str);
    var service = new ServicePage();
    service.save(urlCon);
    service.parseJSON(urlCon.getPage());

   /**
    URL oracle = new URL("https://nominatim.openstreetmap.org/search?state=Самарская область&country=russia&format=json&polygon_geojson=1");
    URLConnection yc = oracle.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
    String inputLine;
    while ((inputLine = in.readLine()) != null)
      System.out.println(inputLine);
    in.close();*/

  }

}
