package cn.bmilk.amp;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class KafkaTopicDemo {
    public static void main(String[] args) {
        // Kafka配置
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Kafka集群的地址
        props.put("client.id", "CreateKafkaTopic");

        // 创建Kafka AdminClient
        AdminClient adminClient = AdminClient.create(props);

        // 指定要创建的Topic的名称、分区和副本数
        String topicName = "TEST.TOPIC-6";
        int numPartitions = 3;
        short replicationFactor = 1;

        // 创建新Topic
        NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);

        // 使用AdminClient创建Topic
        adminClient.createTopics(Collections.singletonList(newTopic));
//        List<String> topicList = new ArrayList<>();
        //topicList.add("test.topic");
        //topicList.add("test.topic-1");
//        topicList.add("test.topic-2");
//        topicList.add("test.topic-3");
//        adminClient.deleteTopics(topicList);

        // 关闭AdminClient
        adminClient.close();
    }
}
