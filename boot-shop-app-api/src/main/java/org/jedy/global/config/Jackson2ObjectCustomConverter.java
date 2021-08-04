package org.jedy.global.config;

import com.fasterxml.jackson.databind.MapperFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


@Configuration
public class Jackson2ObjectCustomConverter {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer initJackson() {
        return (Jackson2ObjectMapperBuilder builder) -> builder.featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
    }
}
