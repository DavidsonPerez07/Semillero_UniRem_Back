package com.unirem.member_service.config;

import com.unirem.member_service.utils.FileSaver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public FileSaver fileSaver() {
        return new FileSaver();
    }
}
