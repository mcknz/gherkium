package com.mcknz.gherkium.pages.common;

import com.mcknz.gherkium.web.AbstractSite;
import com.mcknz.gherkium.web.WebPage;

import java.lang.reflect.InvocationTargetException;

public class PageFactory<T extends AbstractLoginPage, U extends WebPage> {

  private final AbstractSite site;
  private final Class<T> loginPageClass;
  private final Class<U> mainPageClass;

  public PageFactory(final AbstractSite site,
                     final Class<T> loginPageClass,
                     final Class<U> mainPageClass) {
    this.site = site;
    this.loginPageClass = loginPageClass;
    this.mainPageClass = mainPageClass;
  }

  public T getLoginPage(String userNameInputId,
                        String passwordInputId,
                        String loginButtonId)
    throws
      InstantiationException,
      IllegalAccessException,
      NoSuchMethodException,
      InvocationTargetException {
    return loginPageClass
      .getDeclaredConstructor(AbstractSite.class, String.class, String.class, String.class)
      .newInstance(site, userNameInputId, passwordInputId, loginButtonId);
  }

  public U getMainPage()
    throws
      InstantiationException,
      IllegalAccessException,
      NoSuchMethodException,
      InvocationTargetException {
    return mainPageClass
      .getDeclaredConstructor(AbstractSite.class)
      .newInstance(site);
  }
}
