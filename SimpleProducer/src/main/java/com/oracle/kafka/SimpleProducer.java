package com.oracle.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: bgo
 * Date: 11/4/18
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();

        // bootstrap servers
        properties.setProperty("bootstrap.servers","localhost:9092");
        // key and value is strings
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        //producer acks  (0=not wait for ack; 1=wait for leader ack; all=wait for full cluster ack)
        properties.setProperty("acks","1");
        properties.setProperty("retries","3");
        properties.setProperty("linger.ms", "1"); // force messages to be send every 1ms ; remove this line if producer.flush() is explicitly used.

        Producer<String,String> producer = new KafkaProducer<String, String>(properties);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("first_topic", "6", "test record - linger.ms=1");
        producer.send(producerRecord);
        //producer.flush();  //This can be used to force a send - alternatively use property linger.ms
        producer.close();
    }
}
