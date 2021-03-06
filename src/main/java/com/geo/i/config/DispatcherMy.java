package com.geo.i.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Инициализация DispatcherServlet с конфигурацией
 */
public class DispatcherMy extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[0];
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{ApplicationContextData.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  public void onStartup(ServletContext aServletContext) throws ServletException {
    aServletContext.setRequestCharacterEncoding("UTF-8");
    aServletContext.setResponseCharacterEncoding("UTF-8");
    super.onStartup(aServletContext);
    registerHiddenFieldFilter(aServletContext);
  }

  private void registerHiddenFieldFilter(ServletContext aContext) {
    aContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter())
        .addMappingForUrlPatterns(null, true,
            "/*");
    aContext.setRequestCharacterEncoding("UTF-8");
    aContext.setResponseCharacterEncoding("UTF-8");
  }

}
