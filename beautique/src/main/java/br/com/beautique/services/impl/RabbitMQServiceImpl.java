package br.com.beautique.services.impl;

import br.com.beautique.configurations.RabbitMQTopicConfig;
import br.com.beautique.services.BrokerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMQServiceImpl implements BrokerService {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQTopicConfig rabbitMQTopicConfig;

    @Override
    public void send(String type, Object data) {
        String routingKey = type + ".#";
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend(rabbitMQTopicConfig.exchangeName , routingKey, jsonData, message ->
                    { message.getMessageProperties()
                        .setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing message" + e.getMessage());
        }
    }
}
