package com.course.kafka;

import com.course.avro.data.Avro01;
import com.course.kafka.broker.producer.Avro01Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class KafkaAvroProducerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaAvroProducerApplication.class, args);
    }

    @Autowired
    private Avro01Producer avro01Producer;

    @Override
    public void run(String... args) throws Exception {
        sendViaAvro01Producer(avro01Producer);
    }

    private static void sendViaAvro01Producer(Avro01Producer avro01Producer){
        var data = Avro01.newBuilder()
                .setActive(false)
                .setFullName("Full name" + ThreadLocalRandom.current().nextInt())
                .setMaritalStatus("SINGLE")
                .build();
        avro01Producer.sendMessage(data);
    }
}
