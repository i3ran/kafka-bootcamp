package com.course.kafka;

import com.course.kafka.entity.Employee;
import com.course.kafka.entity.FoodOrder;
import com.course.kafka.entity.PurchaseRequest;
import com.course.kafka.entity.SimpleNumber;
import com.course.kafka.producer.*;
import com.course.kafka.service.ImageService;
import com.course.kafka.service.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceProducer invoiceProducer;
   /* @Autowired
    private ImageService imageService;

    @Autowired
    private ImageProducer imageProducer;*/

//    @Autowired
//    private FoodOrderProducer producer;
//
    @Autowired
    private SimpleNumberProducer simpleNumberProducer;
//    private PurchaseRequestProducer producer;
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

//        purchaseRequestProducerForDuplicateMessageTest(producer);
//        foodOrderProducerTest(producer);
        simpleNumberProducerTest(simpleNumberProducer);
//            imageProducer_RetryingKafkaTest();
//        invoiceProducer_DLTTest();

    }

    private void invoiceProducer_DLTTest() throws JsonProcessingException {
        for (int i = 0; i < 10; i++) {
            var invoice = invoiceService.generateInvoice();
            if (i > 5) invoice.setAmount(0);
            invoiceProducer.send(invoice);
        }
    }

    /*private void imageProducer_RetryingKafkaTest() throws JsonProcessingException {
        var image1 = imageService.generateImage("jpg");
        var image2 = imageService.generateImage("svg");
        var image3 = imageService.generateImage("png");
        var image4 = imageService.generateImage("bmp");
        var image5 = imageService.generateImage("tiff");
        var image6 = imageService.generateImage("gif");

        imageProducer.send(image1,0);
        imageProducer.send(image2,0);
        imageProducer.send(image3,0);
        imageProducer.send(image4,1);
        imageProducer.send(image5,1);
        imageProducer.send(image6,1);
    }*/

    private void simpleNumberProducerTest(SimpleNumberProducer simpleNumberProducer) throws JsonProcessingException {
        for (int i = 0; i < 3; i++) {
            SimpleNumber simpleNumber = new SimpleNumber(i);
            simpleNumberProducer.send(simpleNumber);
        }
    }

    private void foodOrderProducerTest(FoodOrderProducer producer) throws JsonProcessingException {
        var order1 = new FoodOrder(5, "chicken");
        var order2 = new FoodOrder(10, "fish");
        var order3 = new FoodOrder(3, "pizza");
        producer.send(order1);
        producer.send(order2);
        producer.send(order3);
    }

    /*private void purchaseRequestProducerForDuplicateMessageTest(PurchaseRequestProducer producer){
        var pr1 = new PurchaseRequest(5551, "PR-1", 100, "USD");
        var pr2 = new PurchaseRequest(5552, "PR-2", 101, "USD");
        var pr3 = new PurchaseRequest(5553, "PR-3", 103, "USD");

        producer.send(pr1);
        producer.send(pr2);
        producer.send(pr3);
        producer.send(pr1); //duplicate message
    }*/
}
