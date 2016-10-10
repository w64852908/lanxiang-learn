package com.lanxiang.protobuf.rabbitmq;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.lanxiang.protobuf.Greeting;
import com.lanxiang.rabbitmq.resource.guice.RabbitmqModule;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by lanxiang on 2016/10/9.
 */
@Slf4j
public class ProtobufProducer {

    private final static String ROUTING_KEY = "async_protobuf";

    @Inject
    private Channel channel;

    private List<Greeting> greetings;

    private void init() throws IOException {
        greetings = new ArrayList<Greeting>();
        for (int i = 0; i < 1000; i++) {
            Greeting.Builder greeting = Greeting.newBuilder();
            greeting.setId(i);
            greeting.setFrom("jojo");
            greeting.setTo("dio");
            greeting.setContent("olaolaolaola,mudamudamudamuda");
            greetings.add(greeting.build());
        }

        channel.queueDeclare(ROUTING_KEY, false, false, false, null);
    }

    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new RabbitmqModule());
        ProtobufProducer producer = injector.getInstance(ProtobufProducer.class);

        producer.init();
        int i = 0;
        for (Greeting greeting : producer.greetings) {
            i++;
            producer.channel.basicPublish("", ROUTING_KEY, null, greeting.toByteArray());
            TimeUnit.SECONDS.sleep(2);
            if (i >= 10) {
                log.info("Sent 10 greetings." + new Date());
                i = 0;
            }
        }
        System.exit(-1);
    }
}
