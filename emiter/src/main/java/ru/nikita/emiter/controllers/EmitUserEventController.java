package ru.nikita.emiter.controllers;

import dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikita.emiter.services.kafka.KafkaUserSenderServiceImpl;

@RestController
@RequestMapping("/app/v1/user")
@RequiredArgsConstructor
public class EmitUserEventController {

	private final KafkaUserSenderServiceImpl kafkaUserSenderService;

	@PostMapping
	public String emitWithNewUser(@RequestBody UserDto userDto) {
		kafkaUserSenderService.sendMessage(userDto);
		return "Success";
	}
}
