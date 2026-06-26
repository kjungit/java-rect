package org.example.springtheory.ch03.ex_3_3.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public SimpleConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
