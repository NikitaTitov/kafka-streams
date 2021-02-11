package ru.nikita.consumer.listeners;

import dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import topics.UserTopics;

@Slf4j
@Service
public class UserListener {

	@KafkaListener(topics = UserTopics.USER_TOPIC)
	public void onUserPublished(@Payload UserDto userDto,
	                            @Header(KafkaHeaders.OFFSET) Integer offset,
	                            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
	                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
	                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
	                            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
	                            Acknowledgment acknowledgment
	) {
		log.info(String.format("New event with user: %s, has topic: %s, key: %s", userDto, topic, key));
		acknowledgment.acknowledge();
	}
}
