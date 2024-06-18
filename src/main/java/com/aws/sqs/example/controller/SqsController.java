package com.aws.sqs.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SqsController {


    private final SqsClient sqsClient;
    @Autowired
    public SqsController(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }


    @GetMapping("/create-queue")
    public String createQueue()
    {
        CreateQueueRequest request=CreateQueueRequest.builder()
                .queueName("my-queue")
                .build();
        CreateQueueResponse response=sqsClient.createQueue(request);
        return "Queue cteated with URL:- "+response.queueUrl();
    }
    @GetMapping("/send-message")
    public String sendMessge(@RequestParam String msg)
    {
        SendMessageRequest request=SendMessageRequest.builder()
                .queueUrl("http://sqs.sa-east-1.localhost.localstack.cloud:4566/000000000000/my-queue")
                .messageBody(msg)
                .build();
        sqsClient.sendMessage(request);
        return "Message send to Queue.";
    }
    @GetMapping("/recevie-message")
    public List<String> recevieMessagesList()
    {
        ReceiveMessageRequest request=ReceiveMessageRequest.builder()
                .queueUrl("http://sqs.sa-east-1.localhost.localstack.cloud:4566/000000000000/my-queue")
                .build();
        ReceiveMessageResponse response =sqsClient.receiveMessage(request);
        List<Message>messages=response.messages();
        List<String>messageBoides=new ArrayList<>();
        for(Message message:messages)
        {
            messageBoides.add(message.body());
        }
        return messageBoides;
    }
    @GetMapping("/send-bulk-message")
    public String sendBulkMessage(@RequestParam String msg,@RequestParam("count") int count)
    {
        for(int i=0;i<count;i++)
        {
            SendMessageRequest request=SendMessageRequest.builder()
                    .queueUrl("http://sqs.sa-east-1.localhost.localstack.cloud:4566/000000000000/my-queue")
                    .messageBody("Bulk Message : "+msg+" - "+(i+1))
                    .build();
            sqsClient.sendMessage(request);
        }
        return "Bulk message sent to the Queue.";
    }
    @GetMapping("/receive-bulk-message")
    public String receiveBulkMessage(@RequestParam("count")int count)
    {
        ReceiveMessageRequest request=ReceiveMessageRequest.builder()
                .queueUrl("http://sqs.sa-east-1.localhost.localstack.cloud:4566/000000000000/my-queue")
                .maxNumberOfMessages(count)
                .build();
        List<Message> messageList=sqsClient.receiveMessage(request).messages();
        StringBuilder response = new StringBuilder("Received messages:\n");
        for (Message message : messageList) {
            response.append(message.body()).append("\n");
           }

        return response.toString();
    }
    @DeleteMapping("/delete-message")
    public String deleteMessage(@RequestParam("count")int count)
    {
        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl("http://sqs.sa-east-1.localhost.localstack.cloud:4566/000000000000/my-queue")
                .maxNumberOfMessages(count)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();

        StringBuilder response = new StringBuilder("Received messages:\n");
        for (Message message : messages) {
            response.append(message.body()).append("\n");
            sqsClient.deleteMessage(DeleteMessageRequest.builder()
                    .queueUrl(receiveRequest.queueUrl())
                    .receiptHandle(message.receiptHandle())
                    .build());
        }

        return response.toString();
    }
}
