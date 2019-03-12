package com.hg.jiagou.util.sys;

public class BaseError extends Error
{
  protected String key;
  protected Object[] args;

  public BaseError(String key)
  {
    super(key);
    this.key = key;
  }

  public BaseError(String key, Throwable cause)
  {
    super(key, cause);
    this.key = key;
  }

  public BaseError(String key, Object[] args)
  {
    super(key);
    this.key = key;
    this.args = args;
  }

  public BaseError(String key, Throwable cause, Object[] args)
  {
    super(key, cause);
    this.key = key;
    this.args = args;
  }

  public String getKey()
  {
    return this.key;
  }

  public Object[] getArgs()
  {
    return this.args;
  }
}