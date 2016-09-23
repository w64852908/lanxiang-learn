package com.lanxiang.rabbitmq.review.topic.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanxiang.rabbitmq.review.topic.body.MessageBody;
import com.lanxiang.rabbitmq.review.topic.handler.MessageHandler;
import com.lanxiang.rabbitmq.review.topic.subscriber.TopicSubscriber;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lanxiang on 16/9/20.
 */
public class TopicExecutor {

    private final static Logger log = LoggerFactory.getLogger(TopicExecutor.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private MessageBody receiveMessageBody(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        Object object = null;
        try {
            String value = new String(bytes, "utf-8");
            JSONObject jsonObject = new JSONObject(value);

            Object objectValue = jsonObject.get("object");
            String className = jsonObject.getString("event");
            Class event = Class.forName(className);

            if (event.equals(String.class)) {
                object = objectValue;
            } else {
                object = objectMapper.readValue(objectValue.toString(), event);
            }
        } catch (Exception e) {
            log.error("Receive message error : " + e.getMessage());
        }
        return new MessageBody(object);
    }


    public void executeWork(byte[] bytes) {
        MessageBody messageBody = receiveMessageBody(bytes);

        if (messageBody == null) {
            return;
        }

        Object object = messageBody.getObject();
        Class<?> event = messageBody.getEvent();
        try {
            //消费消息的类的method需要有consumer()方法
            MessageHandler messageHandler = new MessageHandler(new TopicSubscriber(), TopicSubscriber.class.getMethod("consume", Object.class));
            messageHandler.invokeMethod(object);
        } catch (Throwable t) {
            log.error(t.getMessage());
        }
    }
}
