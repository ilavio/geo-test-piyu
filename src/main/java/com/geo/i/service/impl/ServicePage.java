package com.geo.i.service.impl;

import com.geo.i.resource.ConnectionURL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

public class ServicePage {

  public void save(ConnectionURL connectionURL) {
    System.out.println("Содержимое: " + "\n" + connectionURL.getPage());
  }

  public List<Double[]> parseJSON(String json) {
    List<Double[]> listGeo = new ArrayList<Double[]>();
    JSONObject jsonParse = new JSONObject(new JSONArray(json).getJSONObject(0));
    JSONArray array = new JSONArray(jsonParse.getJSONArray("geojson"));
    Iterator geoItr = array.iterator();
    while (geoItr.hasNext()) {
      JSONArray ar1 = (JSONArray) geoItr.next();
      Iterator geoItr2 = ar1.iterator();
      while(geoItr2.hasNext()){
        JSONArray ar2 = (JSONArray) geoItr.next();
        Iterator geoItr3 = ar2.iterator();
        while(geoItr3.hasNext()){
          JSONArray ar3 = (JSONArray) geoItr.next();
          Iterator geoItr4 = ar3.iterator();
          Double [] doubleMas = new Double[2];
          int x = 0;
          while(geoItr4.hasNext()){
            x++;
            doubleMas[x] = (Double) geoItr4.next();
          }
          System.out.println("Внутри: " + Arrays.toString(doubleMas));
          listGeo.add(doubleMas);
        }
      }
    }
    return listGeo;
  }
}
