package com.jiuqi.cosmos.pojo;

import java.util.Date;

/**
 * Created by ldd on 2019/12/30.
 */
public class InMessage {

   private String fromName;


   private String toName;

   private String content;

   private Date time;




    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
