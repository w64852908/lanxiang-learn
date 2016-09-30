package com.lanxiang.rabbitmq.async.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.lanxiang.rabbitmq.async.message.AsyncMessage;
import com.lanxiang.rabbitmq.async.register.AsyncRegister;
import com.lanxiang.rabbitmq.async.subscriber.AsyncSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by lanxiang on 2016/9/29.
 */
@Slf4j
@Singleton
public class AsyncExecutorImpl implements AsyncExecutor {

    @Inject
    protected AsyncRegister asyncRegister;

    @Inject
    protected ObjectMapper objectMapper;

    private AsyncMessage receiveMessageBody(byte[] bytes) throws Throwable {
        if (bytes == null) {
            return null;
        }
        Object object;
        String value = new String(bytes, "utf-8");
        JSONObject jsonObject = new JSONObject(value);

        Object objectValue = jsonObject.get("object");
        String className = jsonObject.getString("clazz");
        Class clazz = Class.forName(className);

        if (clazz.equals(String.class)) {
            object = objectValue;
        } else {
            object = objectMapper.readValue(objectValue.toString(), clazz);
        }
        return new AsyncMessage(object);
    }

    @Override
    public int executeWorks(byte[] bytes) throws Throwable {
        AsyncMessage message = null;
        message = receiveMessageBody(bytes);
        if (message == null) {
            return -1;
        }

        Class<?> clazz = message.getClazz();
        Object object = message.getObject();

        List<AsyncSubscriber> subscribers = asyncRegister.getSubscriber(clazz);
        if (subscribers == null || subscribers.size() == 0) {
            log.error("找不到消息对应的执行方法");
            return -1;
        }

        for (AsyncSubscriber subscriber : subscribers) {
            try {
                subscriber.invokeMethod(object);
            } catch (Throwable t) {
                log.error("Message subscribe failed, " + t.getMessage());
            }
        }
        return subscribers.size();
    }
}
