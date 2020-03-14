package com.jiuqi.cosmos.pojo;

import java.util.Date;

/**
 * Created by ldd on 2019/12/30.
 */
public class OutMessage {

   private String fromName;

   private String content;

   private Date time = new Date(  );


   public OutMessage(){}

   public OutMessage(String content){
     this.content = content;
   }


    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
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
