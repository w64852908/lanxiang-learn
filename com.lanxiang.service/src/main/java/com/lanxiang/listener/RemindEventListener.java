package com.lanxiang.listener;

import com.lanxiang.event.RemindEvent;
import com.lanxiang.rabbitmq.async.annotation.AsyncMark;
import com.lanxiang.rabbitmq.async.listener.AsyncAbstractListener;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

/**
 * Created by lanxiang on 2016/9/26.
 */
@Slf4j
@Singleton
public class RemindEventListener extends AsyncAbstractListener {

    @AsyncMark
    public void remindEventListener(RemindEvent event) {
        if(event == null){
            return;
        }
        log.info(event.toString());
    }
}
