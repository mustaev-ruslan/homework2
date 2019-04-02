package ru.aaxee.homework2.config;

import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;
import ru.aaxee.homework2.event.CascadeSaveMongoEventListener;

@Configuration
public class ApplicationConfig {

    @Bean
    public PromptProvider promptProvider() {
        return () -> new AttributedString("library:>");
    }

    @Bean
    public CascadeSaveMongoEventListener cascadingMongoEventListener() {
        return new CascadeSaveMongoEventListener();
    }
}
