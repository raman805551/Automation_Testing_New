package com.account.my.config;


import com.account.my.common.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.account.my"})
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class AppConfig {

    @Autowired
    public DataSource dataSource;

    @Autowired
    public  Environment environment;

    @Bean
    @Qualifier
    public JdbcTemplate jdbcTemplate(){return new JdbcTemplate(dataSource);}

    @Bean
    public ApiClient newServiceApi(RestTemplate restTemplate, Environment environment){
        return new ApiClient(restTemplate, environment);
    }


}
