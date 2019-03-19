package com.mcknz.gherkium.pages.samples.google.common;

import com.mcknz.gherkium.web.AbstractSite;

public class GoogleAccountSite extends AbstractSite {

  @SuppressWarnings("unused")
  @Override
  protected String getUrlPrefix() {
    return "https://accounts.google.com/";
  }
}
