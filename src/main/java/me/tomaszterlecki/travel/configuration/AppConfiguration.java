package me.tomaszterlecki.travel.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("me.tomaszterlecki.travel")
public class AppConfiguration {

    @Bean
    public SessionFactory sessionFactory () {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }
}
