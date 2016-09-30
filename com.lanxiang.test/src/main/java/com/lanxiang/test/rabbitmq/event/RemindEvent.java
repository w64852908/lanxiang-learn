package com.lanxiang.test.rabbitmq.event;


import java.util.Date;
import java.util.List;

/**
 * Created by lanxiang on 2016/9/26.
 */
public class RemindEvent extends Event {

    private String title;

    private String content;

    private Date createAt;

    private boolean isReapted;

    private List<String> repeatedDate;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public boolean isReapted() {
        return isReapted;
    }

    public List<String> getRepeatedDate() {
        return repeatedDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setReapted(boolean reapted) {
        isReapted = reapted;
    }

    public void setRepeatedDate(List<String> repeatedDate) {
        this.repeatedDate = repeatedDate;
    }

    @Override
    public String toString() {
        return "RemindEvent{" +
                "num='" + num + '\'' +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", isReapted=" + isReapted +
                ", repeatedDate=" + repeatedDate +
                '}';
    }
}