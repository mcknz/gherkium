package com.mcknz.gherkium.pages.samples.google.common;

import com.mcknz.gherkium.web.AbstractSite;

public class GoogleSite extends AbstractSite {

  public GoogleSite() {
    super();
  }

  @Override
  protected String getUrlPrefix() {
    return "https://www.google.com/";
  }
}
