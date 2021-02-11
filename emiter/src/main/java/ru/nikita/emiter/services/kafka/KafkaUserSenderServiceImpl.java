package ru.nikita.emiter.services.kafka;

import dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import topics.UserTopics;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUserSenderServiceImpl {

	private final KafkaTemplate<String, UserDto> kafkaTemplate;

	public ListenableFuture<SendResult<String, UserDto>> sendMessage(UserDto userDto) {
		return kafkaTemplate.send(UserTopics.USER_TOPIC, userDto);
	}
}
