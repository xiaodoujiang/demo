package cn.bmilk.amp;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
public class KafkaProducerExample{

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


        // 创建生产消息的客户端，传入参数
        Producer<String, String> producer = new KafkaProducer<String, String>(props);


        String uuid = UUID.randomUUID().toString();
        ProducerRecord<String, String> message1 = new ProducerRecord<>(
                TOPIC_NAME, 0, uuid, uuid+"helloKafka");

        RecordMetadata metadata = producer.send(message1).get();


        producer.close();

        log("send sync：topic:%s, partition:%d, key:%d, value:%s",
                metadata.topic(), metadata.partition(), metadata.offset(),
                message1.key(), message1.value());

    }
}

