package com.santander.rest.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/kafka")
    public String send(@RequestBody String message) {
        sendMessage(message);
        return "Send Message Kafka Queue:"+message.toString();
    }
    public void sendMessage(String msg) {
        kafkaTemplate.send("my-topic", msg);
    }


    @KafkaListener(topics = "my-topic",groupId = "1")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

}
/*
ESto para hacer send de mensajes, create un controlle para lanzar
@Autowired
private KafkaTemplate<String, String> kafkaTemplate;

public void sendMessage(String msg) {
    kafkaTemplate.send(topicName, msg);
}

 */