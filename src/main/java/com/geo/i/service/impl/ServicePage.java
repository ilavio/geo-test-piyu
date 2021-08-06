package com.geo.i.service.impl;

import com.geo.i.entity.PointForMap;
import com.geo.i.resource.ConnectionURL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Класс ServicePage обслуживания содержимого запроса странници
 */
@Service
public class ServicePage {

  private String jsonString;

  @Cacheable("page")
  public void save(ConnectionURL connectionURL) {
    jsonString = connectionURL.getPage();
    //System.out.println("Содержимое: " + "\n" + jsonString);
  }

  /**
   * Метод переработки содержимого запроса в список заготовок
   *
   * @return List<String>
   */
  public List<String> parseJSONBlank() {
    var list = new ArrayList<String>();
    var stringBuffer = new StringBuffer();
    Pattern pattern = Pattern.compile("([\\[][\\d]+[\\.][\\d]+[\\,][\\d]+[\\.][\\d]+[\\]])");
    Matcher matcher = pattern.matcher(jsonString);

    if (jsonString != null) {
      while (matcher.find()) {
        list.add(matcher.group());
      }
    } else {
      System.out.println("ServicePage parseJSONBlank(): jsonString равен null");
    }
    //System.out.println("Содержание списка ServicePage parseJSONBlank(): " + list);
    return list;
  }

  /**
   * Метод создания списка точек из заготовки String
   *
   * @param list
   * @return List<PointForMap>
   */
  @Cacheable("list")
  public List<PointForMap> parseBlank(List<String> list) {
    var listPoint = new ArrayList<PointForMap>();
    Pattern pattern = Pattern.compile("([\\d]+[\\.][\\d]+)");

    if (list != null) {
      for (String geopoint : list) {
        Matcher matcher = pattern.matcher(geopoint);
        var pointForMap = new PointForMap();
        int i = 0;
        while (matcher.find()) {
          if (i == 0) {
            double lat = Double.parseDouble(matcher.group());
            pointForMap.setLatitude(lat);
          }
          if (i == 1) {
            double lon = Double.parseDouble(matcher.group());
            pointForMap.setLongitude(lon);
          }
          i += 1;
        }
        listPoint.add(pointForMap);
      }
    } else {
      System.out.println("ServicePage parseBlank(): list равен null");
    }
    System.out.println("Содержание списка ServicePage parseBlank(): " + listPoint);
    return listPoint;
  }
}
