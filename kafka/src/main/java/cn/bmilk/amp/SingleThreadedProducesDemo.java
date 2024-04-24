package cn.bmilk.amp;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class SingleThreadedProducesDemo {

    private static String bootServer = "localhost:9092";
    private static String TOPIC_NAME = "test.topic";
    private static String CONSUMER_GROUP_NAME = "consumer1";


    public static void log(String fmt, Object... objs) {
        if (objs != null && objs.length > 0) {
            String s = String.format(fmt, objs);
            System.out.println(s);
        } else {
            System.out.println(fmt);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
// 设置参数
        Properties props = new Properties();
        // 指定服务器配置【ip：端口】
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootServer);

        // key/value 序列化
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "zstd");
        props.put(ProducerConfig.LINGER_MS_CONFIG, "10000000"); // 例如，设置为1000毫秒
        props.put("max.block.ms", 600000 );
        props.put("max.request.size", 26214400);


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<2000000; i++){
            sb.append("helloKafka");
        }
        String string = sb.toString();


        // 创建生产消息的客户端，传入参数
        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        ProducerRecord<String, String> message = new ProducerRecord<>(
                TOPIC_NAME, 0, UUID.randomUUID().toString(), string);
        ProducerRecord<String, String> message2 = new ProducerRecord<>(
                TOPIC_NAME, 0, UUID.randomUUID().toString(), string);
        ProducerRecord<String, String> message3 = new ProducerRecord<>(
                TOPIC_NAME, 0, UUID.randomUUID().toString(), string);
        // 同步发送消息
        RecordMetadata metadata1 = null;
        RecordMetadata metadata2 = null;
        RecordMetadata metadata3 = null;
        try {
//            metadata1 = producer.send(message).get();
//            metadata2 = producer.send(message2).get();
            long l = System.currentTimeMillis();
            metadata3 = producer.send(message3).get();
            System.out.println(System.currentTimeMillis() - l);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        log("send sync：topic:%s, partition:%d, key:%d, value:%s",
                metadata3.topic(), metadata3.partition(), metadata3.offset(),
                message.key(), message.value());


    }
}
