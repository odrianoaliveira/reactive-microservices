package tech.adriano.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
@SpringBootApplication
public class ProducerApplication {

	private final MessageChannel output;

	public ProducerApplication(Source source) {
		this.output = source.output();
	}

	@PostMapping("/greet/{name}")
	public void publish(@PathVariable String name) {
		String greeting = "Hello, " + name + "!";
		Message<String> msg = MessageBuilder.withPayload(greeting).build();
		this.output.send(msg);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}

//interface ProducerChannels {
//
//	@Output
//	MessageChannel consumer();
//}