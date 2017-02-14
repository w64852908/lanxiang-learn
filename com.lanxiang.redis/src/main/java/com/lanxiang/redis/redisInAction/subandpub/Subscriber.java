package com.lanxiang.redis.redisInAction.subandpub;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by lanxiang on 2017/2/14.
 */
public class Subscriber extends JedisPubSub {

    private int count = 0;

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("Received redis published message, channel %s, message %s", channel, message));
        count++;
        //接收到4条消息后,取消订阅
        if (count >= 4) {
            this.unsubscribe();
        }
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                channel, subscribedChannels));

    }
}
