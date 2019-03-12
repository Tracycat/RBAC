package com.hg.jiagou.util.sys;

public class BaseException extends RuntimeException
{
  protected String key;
  protected Object[] args;

  public BaseException(String key)
  {
    super(key);
    this.key = key;
  }

  public BaseException(String key, Throwable cause)
  {
    super(key, cause);
    this.key = key;
  }

  public BaseException(String key, Object[] args)
  {
    super(key);
    this.key = key;
    this.args = args;
  }

  public BaseException(String key, Throwable cause, Object[] args)
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