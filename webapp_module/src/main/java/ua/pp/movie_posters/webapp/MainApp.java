package ua.pp.movie_posters.webapp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import ua.pp.movie_posters.webapp.configs.SwaggerConfig;
import ua.pp.movie_posters.webapp.messaging.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApp extends SpringBootServletInitializer {
/*
   static public final String topicExchangeName = "spring-boot-exchange";

   static public final String queueName = "spring-boot";

   @Bean
   Queue queue() {
      return new Queue(queueName, false);
   }

   @Bean
   TopicExchange exchange() {
      return new TopicExchange(topicExchangeName);
   }

   @Bean
   Binding binding(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
   }

   @Bean
   SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
      SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
      container.setConnectionFactory(connectionFactory);
      container.setQueueNames(queueName);
      container.setMessageListener(listenerAdapter);
      return container;
   }

   @Bean
   MessageListenerAdapter listenerAdapter(Receiver receiver) {
      return new MessageListenerAdapter(receiver, "receiveMessage");
   }
*/
   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(MainApp.class);
   }


   public static void main(String[] args) {
      SpringApplication.run(MainApp.class, args);
   }
}