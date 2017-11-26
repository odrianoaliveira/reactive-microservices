package tech.adriano.consumer;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.messaging.SubscribableChannel;

import java.util.logging.Logger;

@SpringBootApplication
@EnableBinding(Processor.class)
public class ConsumerApplication {

	@Bean
	@Scope("prototype")
	Logger logger(InjectionPoint ip) {
		return Logger.getLogger(ip.getDeclaredType().getName());
	}

	@Autowired
	private Logger logger;

	@StreamListener(Processor.INPUT)
	public void handleMessage(final String msg) {
		logger.info(msg);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}

//interface ConsumerChannels {
//
//	String INPUT = "producer";
//
//	@Input(ConsumerChannels.INPUT)
//	SubscribableChannel producer();
//}