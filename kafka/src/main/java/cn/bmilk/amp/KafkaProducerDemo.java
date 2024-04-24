package cn.bmilk.amp;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class KafkaProducerDemo implements Runnable{

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
        for (int i =0 ;i<3;i++){
            Thread thread = new Thread(new KafkaProducerDemo());
            thread.start();
        }
        Thread.sleep(100000);
        System.out.println("main exit");


//        KafkaProducerDemo kafkaProducerDemo = new KafkaProducerDemo();
//        kafkaProducerDemo.run();



//        Thread thread = new Thread(new KafkaProducerDemo());
//        thread.start();
//        KafkaProducerDemo kafkaProducerExample = new KafkaProducerDemo();
//        kafkaProducerExample.run();;
//        kafkaProducerExample.run();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() + "线程启动=========================");
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

        for (int i = 0; i < 10; i++) {
            // 创建消息；key：作用是决定了往哪个分区上发，value：具体要发送的消息内容
            String uuid = UUID.randomUUID().toString();
            ProducerRecord<String, String> message = new ProducerRecord<>(
                    TOPIC_NAME, 0, uuid, string);

            // 同步发送消息
            RecordMetadata metadata = null;
            try {
                long start = System.currentTimeMillis();
                producer.send(message);
                long end = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getId() + "======================"+ (end-start));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
//            log("send sync：topic:%s, partition:%d, key:%d, value:%s",
//                    metadata.topic(), metadata.partition(), metadata.offset(),
//                    message.key(), message.value());
        }
    }
}

