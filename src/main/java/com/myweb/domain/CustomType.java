package com.myweb.domain;

import java.io.Serializable;

public class CustomType implements Serializable {
  private static final long serialVersionUID = -8830428430110582225L;
  private Integer code;
  private Object result;

  public CustomType() {
  }

  public CustomType(Integer code, Object result) {
    this.code = code;
    this.result = result;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }
}
