package com.lanxiang.test.rabbitmq.event;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by lanxiang on 2016/9/26.
 */
@Data
public class RemindEvent {

    private String title;

    private String content;

    private Date createAt;

    private boolean isReapted;

    private List<String> repeatedDate;
}