package ru.aaxee.homework2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.SubscribableChannel;

@Configuration
@IntegrationComponentScan
public class ChannelsConfig {

    @Bean
    public SubscribableChannel newBookChannel() {
        return new PublishSubscribeChannel();
    }
}
