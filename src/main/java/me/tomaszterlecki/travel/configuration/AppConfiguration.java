package me.tomaszterlecki.travel.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("me.tomaszterlecki.travel")
public class AppConfiguration {
    @Bean
    public SessionFactory sessionFactory () {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }

    @EnableWebMvc
    public class MvcConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("/files/**")
                    .addResourceLocations("file:/Users/tomaszterlecki/Desktop/travel_img/");
        }
    }
}
