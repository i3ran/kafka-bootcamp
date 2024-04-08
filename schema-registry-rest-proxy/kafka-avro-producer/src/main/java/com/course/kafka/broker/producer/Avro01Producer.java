package com.course.kafka.broker.producer;


import com.course.avro.data.Avro01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Avro01Producer {
    @Autowired
    private KafkaTemplate<String, Avro01> kafkaTemplate;

    public void sendMessage(Avro01 avro01){
        System.out.println(">>>>>>>>>>>>>> avro01: " + avro01.toString());
        kafkaTemplate.send("sc-avro01",avro01);
    }


}
