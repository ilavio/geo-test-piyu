package com.geo.i.resource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;

/**
 * Класс ConnectionURL соединения по URL и изъятию содержимого
 */
@RequiredArgsConstructor
public class ConnectionURL {

  private final String urlAdress;
  private String page;
  private URL url;

  /**
   * Метод фабрика connectionURL создания URL соединения
   *
   * @param urlAdress String
   * @return URL
   */
  private URL createConnectionURL(String urlAdress) {
    try {
      url = new URL(urlAdress);
      System.out.println(url.toString());
    } catch (MalformedURLException e) {
      System.out.println("Ошибка ConnectionURL в connectionURL(): " + e);
    }
    return url;
  }

  /**
   * Метод получения строки содержания запроса по url
   *
   * @return String
   */
  public String getPage() {
    StringBuffer strB1 = new StringBuffer();
    try {
      HttpURLConnection urlcon = (HttpURLConnection) createConnectionURL(urlAdress).openConnection();
      BufferedInputStream bufinstream = new BufferedInputStream(urlcon.getInputStream(), 64);
      byte[] bytemas = new byte[64];
      while (bufinstream.available() != 0) {
        bytemas = bufinstream.readAllBytes();
        strB1.append(new String(bytemas, StandardCharsets.UTF_8));
      }
      bufinstream.close();
    } catch (IOException e) {
      System.out.println("Ошибка ConnectionURL в getPage(): ");
      e.printStackTrace();
    }
    return strB1.toString();
  }
}