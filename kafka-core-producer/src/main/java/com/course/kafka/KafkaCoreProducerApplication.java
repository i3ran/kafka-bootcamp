package com.course.kafka;

import com.course.kafka.producer.HelloKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class KafkaCoreProducerApplication implements CommandLineRunner {

    @Autowired
    private HelloKafkaProducer helloKafkaProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaCoreProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String s = "Hi " + ThreadLocalRandom.current().nextInt();
        System.out.println("----------Publishing message: " + s);
        helloKafkaProducer.sendHello(s);
    }
}
