package com.course.kafka;

import com.course.kafka.entity.Employee;
import com.course.kafka.entity.PurchaseRequest;
import com.course.kafka.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {



    @Autowired
    private PurchaseRequestProducer producer;
//    private Employee2JsonProducer producer;
//    private KafkaKeyProducer producer;
//    private FixedRateProducer producer;
//    private HelloKafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaCoreProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        String s = "Hi " + ThreadLocalRandom.current().nextInt();
//        System.out.println("----------Publishing message: " + s);
//        helloKafkaProducer.sendHello(s);

        /*for (int i = 0; i < 30; i++) {
            var key = "key-" + (i % 4);
            var value = "value " + i + " with key " + key;
            producer.send(key, value);
        }*/
        /*for (int i = 0; i < 5; i++) {
            var emp = new Employee("emp-" + i, "Employee "+ i, LocalDate.now());
            producer.sendMessage(emp);
        }*/

        var pr1 = new PurchaseRequest(5551, "PR-1", 100, "USD");
        var pr2 = new PurchaseRequest(5552, "PR-2", 101, "USD");
        var pr3 = new PurchaseRequest(5553, "PR-3", 103, "USD");

        producer.send(pr1);
        producer.send(pr2);
        producer.send(pr3);
        producer.send(pr1); //duplicate message
    }
}
