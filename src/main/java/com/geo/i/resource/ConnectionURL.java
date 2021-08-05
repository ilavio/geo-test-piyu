package com.geo.i.resource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
  private URL connectionURL(String urlAdress) {
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
      HttpURLConnection urlcon = (HttpURLConnection) connectionURL(urlAdress).openConnection();
      //URLConnection urlcon = this.connectionURL(urlAdress).openConnection();
      BufferedInputStream bufinstream = new BufferedInputStream(urlcon.getInputStream(), 64);
      //BufferedInputStream bufinstream = new BufferedInputStream(connectionURL(urlAdress).openStream(), 64);
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