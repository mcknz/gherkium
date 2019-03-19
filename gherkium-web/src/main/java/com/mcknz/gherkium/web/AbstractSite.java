package com.mcknz.gherkium.web;

abstract public class AbstractSite {

  private final String url;

  public AbstractSite() {
    this.url = getUrlPrefix();
  }

  String getUrl() {
    return url;
  }

  protected abstract String getUrlPrefix();

}