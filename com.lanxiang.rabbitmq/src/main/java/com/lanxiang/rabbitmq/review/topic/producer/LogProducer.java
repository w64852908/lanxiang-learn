package com.lanxiang.rabbitmq.review.topic.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanxiang.rabbitmq.review.topic.body.MessageBody;
import com.lanxiang.rabbitmq.review.topic.channel.ChannelGenerator;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lanxiang on 16/9/19.
 */
public class LogProducer {

    private final static Logger log = LoggerFactory.getLogger(LogProducer.class);

    private final static String EXCHANGE_NAME = "topic_logs";

//    private final static String QUENE_NAME = "async.log";

    private final static String info = "wbg.info.log";

    private final static String debug = "wbg.debug.log";

    private final static String warn = "wbg.warn.log";

    private final static String error = "wbg.error.log";

    private ChannelGenerator channelGenerator;

    private ObjectMapper objectMapper;

    private void sendLog(Channel channel, String routingKey, byte[] bytes) throws Exception {
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, bytes);
    }

    private byte[] buildMsg(String msg) throws Exception {
        log.info(" [x] Sent routingKey = " + ", msg = " + msg + ".");
        byte[] bytes = objectMapper.writeValueAsBytes(new MessageBody(msg));
        return bytes;
    }

    public static void main(String[] args) {

        LogProducer lp = new LogProducer();
        lp.channelGenerator = new ChannelGenerator();
        lp.objectMapper = new ObjectMapper();

        log.info("Initialize log message producer.");
        Channel channel = lp.channelGenerator.getChannel();
        try {
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true, false, null);
//            channel.queueDeclare(QUENE_NAME, true, false, false, null);

            lp.sendLog(channel, info, lp.buildMsg("今天是星期一呢"));
            lp.sendLog(channel, debug, lp.buildMsg("暂时没有bug需要修复哦"));
            lp.sendLog(channel, warn, lp.buildMsg("马上就要灰度啦"));
            lp.sendLog(channel, error, lp.buildMsg("还没有发现错误级别的bug"));
            lp.sendLog(channel, info, lp.buildMsg("最后一条消息发送完毕"));
            lp.sendLog(channel, debug, lp.buildMsg("每天6点发布bug榜单"));
            lp.sendLog(channel, warn, lp.buildMsg("周天才放假"));
            lp.sendLog(channel, error, lp.buildMsg("错误错误错误错误"));

            System.exit(1);
        } catch (Exception e) {
            log.error("Failed to declare exchange, " + e.getMessage());
        }
    }
}
