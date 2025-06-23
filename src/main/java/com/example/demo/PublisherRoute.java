package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PublisherRoute extends RouteBuilder {
  @Override
  public void configure() {
    from("timer:alerta?period=5000")
        .setBody().simple("Alerta generada: ${date:now:yyyy-MM-dd HH:mm:ss}")
        .log("Publicando mensaje: ${body}")
        .to("rabbitmq://localhost/alert-exchange"
            + "?exchangeType=fanout"
            + "&autoDelete=false"
            + "&declare=true" // Same here
            + "&username=guest"
            + "&password=guest");
  }
}
