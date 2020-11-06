package dev.example.configuration;

import dev.example.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

import java.text.ParseException;

@Configuration
@Profile("dev")
//@Sql({"/schema-postgressql.sql"})
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean startDB() throws ParseException
    {
        if (!"create".equals(strategy))
        {
            return false;
        }

        dbService.iniciarConstrucaoDB();
        return true;
    }
}
