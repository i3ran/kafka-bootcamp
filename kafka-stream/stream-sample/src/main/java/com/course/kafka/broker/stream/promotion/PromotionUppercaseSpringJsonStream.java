package com.course.kafka.broker.stream.promotion;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.course.kafka.broker.message.PromotionMessage;

//@Configuration
public class PromotionUppercaseSpringJsonStream {

	@Bean
	public KStream<String, PromotionMessage> kstreamPromotionUppercase(StreamsBuilder builder) {
		var stringSerde = Serdes.String();
		var jsonSerde = new JsonSerde<>(PromotionMessage.class);
		var sourceStream = builder.stream("t-commodity-promotion", Consumed.with(stringSerde, jsonSerde));

		var uppercaseStream = sourceStream.mapValues(this::uppercasePromotionCode);

		uppercaseStream.to("t-commodity-promotion-uppercase", Produced.with(stringSerde, jsonSerde));

		var reversedCodeStream = uppercaseStream.mapValues(this::reversePromotionCode);
		reversedCodeStream.to("t-commodity-promotion-uppercase-reversed", Produced.with(stringSerde, jsonSerde));

		sourceStream.print(Printed.<String, PromotionMessage> toSysOut().withLabel("JSON serde original stream"));
		uppercaseStream.print(Printed.<String, PromotionMessage> toSysOut().withLabel("JSON serde uppercase stream"));
		reversedCodeStream.print(Printed.<String, PromotionMessage> toSysOut().withLabel("JSON serde reversed stream"));
		return sourceStream;
	}

	public PromotionMessage uppercasePromotionCode(PromotionMessage message) {
		return new PromotionMessage(message.getPromotionCode().toUpperCase());
	}

	public PromotionMessage reversePromotionCode(PromotionMessage message) {
		return new PromotionMessage(new StringBuilder(message.getPromotionCode()).reverse().toString());
	}

}
