package com.hg.jiagou.util.sys;

import com.hg.jiagou.vo.sys.Vo4Upload;
import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class Upload
{
  private static Logger logger = Logger.getLogger(Upload.class.getName());
  private int MAX_SIZE = 10485760;
  private String[] allowedExt = { ".png", ".jpg", ".jpeg", ".bmp" };
  private HttpServletRequest request = null;

  private String state = "";

  private long size = 0L;
  private String savePath = "upload";

  public List<Vo4Upload> upload(HttpServletRequest request) throws UnsupportedEncodingException
  {
    this.request = request;

    DiskFileItemFactory dfif = new DiskFileItemFactory();
    dfif.setSizeThreshold(4096);

    String realwebbase = request.getSession().getServletContext().getRealPath("/");
    File temp_file = new File(realwebbase + this.savePath + "/UploadTemp");
    if (!temp_file.exists()) {
      temp_file.mkdirs();
    } else {
      temp_file.delete();
      temp_file.mkdirs();
    }
    dfif.setRepository(temp_file);

    ServletFileUpload sfu = new ServletFileUpload(dfif);
    sfu.setHeaderEncoding("UTF-8");

    sfu.setSizeMax(this.MAX_SIZE);

    List fileList = null;
    try {
      fileList = sfu.parseRequest(request);
    } catch (FileUploadException e) {
      if ((e instanceof SizeLimitExceededException)) {
        this.state = "文件尺寸超过规定大小!";
        logger.error(this.state, e);

        throw new BaseException(this.state, e);
      }

      if ((fileList == null) || (fileList.size() == 0)) {
        this.state = "请选择上传文件!";
        logger.error(this.state, e);

        throw new BaseException(this.state, e);
      }
    }

    List<Vo4Upload> list = new ArrayList<Vo4Upload>();

    Iterator fileItr = fileList.iterator();

    while (fileItr.hasNext())
    {
      FileItem fileItem = (FileItem)fileItr.next();

      if (fileItem.isFormField()) {
        Vo4Upload vo = new Vo4Upload();
        String name = fileItem.getFieldName();
        String value = fileItem.getString("UTF-8");
        vo.setName(name);
        vo.setValue(value);
        list.add(vo);
      }
      else {
        String path = fileItem.getName();

        this.size = fileItem.getSize();

        if (("".equals(path)) || (this.size == 0L)) {
          this.state = "上传的文件无效！";
        }

        String t_name = path.substring(path.lastIndexOf("/") + 1);
        String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1).toLowerCase();

        int i = 0;
        while (i < this.allowedExt.length) {
          if (this.allowedExt[i].equals(t_ext)) {
            break;
          }
          i++;
        }
        if (i == this.allowedExt.length) {
          String o = "请重新操作，上传以下类型的文件:";
          int i2 = 0;
          while (i2 < this.allowedExt.length) {
            o = o + "*." + this.allowedExt[i2] + "&nbsp;&nbsp;&nbsp;";
            i2++;
          }
          this.state = o;
        }

        String uuid = UUID.randomUUID().toString();

        String _today = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String u_name = realwebbase + this.savePath + "/Uploaded/" + _today + "/" + uuid + "." + t_ext;
        System.out.println("保存的最终文件完整路径（服务器端）:" + u_name);
        Vo4Upload vo = new Vo4Upload();
        vo.setName("logo_url");
        vo.setValue(u_name);
        list.add(vo);
        try
        {
          File _today_file = new File(realwebbase + this.savePath + "/Uploaded/" + _today);
          if (!_today_file.exists()) {
            _today_file.mkdirs();
          }
          fileItem.write(new File(u_name));

          String save_path = this.savePath + "/Uploaded/" + _today + "/" + uuid + "." + t_ext;

          this.state = ("文件上传成功. 已保存为: " + uuid + "." + t_ext + "     文件大小: " + this.size + " mB ");
        }
        catch (Exception e) {
          e.printStackTrace();
          logger.error("上传失败", e);

          throw new BaseException("上传失败", e);
        }
      }
    }
    return list;
  }

  public long getSize() {
    return this.size;
  }
  public void setMaxSize(int size) {
    this.MAX_SIZE = size;
  }
  public String getState() {
    return this.state;
  }
  public void setAllowFiles(String[] allowedExt) {
    this.allowedExt = allowedExt;
  }
  public void setSavePath(String savePath) {
    this.savePath = savePath;
  }
}