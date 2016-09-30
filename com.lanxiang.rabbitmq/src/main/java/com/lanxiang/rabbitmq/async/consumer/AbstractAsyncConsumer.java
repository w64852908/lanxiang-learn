package com.lanxiang.rabbitmq.async.consumer;

import com.google.inject.Inject;
import com.lanxiang.rabbitmq.async.executor.AsyncExecutor;
import com.lanxiang.rabbitmq.async.message.AsyncMessage;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lanxiang on 2016/9/29.
 */
@Slf4j
@Singleton
public abstract class AbstractAsyncConsumer {

    @Inject
    private Connection connection;

    private Channel channel;

    @Inject
    private AsyncExecutor asyncExecutor;

    private Consumer consumer;

    private ExecutorService executorService;

    protected abstract String getExchangeName();

    protected abstract String getType();

    protected abstract String getQueueName() throws IOException;

    protected abstract List<String> getBindingKeys();

    public void initConsumer() {
        log.info("Initialize rabbitmq consumer.");
        try {
            channel = connection.createChannel();
            declareExchange();
            declareQueue();
            bindQueue();
        } catch (IOException e) {
            log.error("Create channel failed, " + e.getCause().getMessage());
        }
        consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                int result = 0;
                try {
                    result = asyncExecutor.executeWorks(body);
//                    log.info("Execute message " + asyncExecutor.receiveMessageBody(body).toString());
                    if (result == -1) {
                        channel.basicReject(envelope.getDeliveryTag(), false);
                    }
                } catch (Throwable t) {
                    log.error("Execute message failed, " + t);
                }
                if (result >= 0) {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        executorService = Executors.newSingleThreadExecutor();
        //按照消息顺序进行消费
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //为啥要休眠一分钟
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }

                try {
                    channel.basicConsume(getQueueName(), false, consumer);
                } catch (IOException e) {
                    log.error("Consumer message error, " + e.getMessage());
                }
            }
        });
    }

    private void declareExchange() throws IOException {
        channel.exchangeDeclare(getExchangeName(), getType(), true, false, null);
    }

    private void declareQueue() throws IOException {
        channel.queueDeclare(getQueueName(), true, false, false, null).getQueue();
    }

    private void bindQueue() throws IOException {
        for (String bindingKey : getBindingKeys()) {
            channel.queueBind(getQueueName(), getExchangeName(), bindingKey);
        }
    }
}
