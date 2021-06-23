package com.sajid;

import java.sql.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws SQLException {

        SpringApplication.run(Main.class, args);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Class not found");
            return;
        }
    }

    @Bean
    public Connection createConnection() throws Exception {
        String database = "user_db"; //"user_db";
        String jdbcUrl = String.format("jdbc:mysql://localhost:3306/%s?useSSL=false", database);
        return DriverManager.getConnection(jdbcUrl, "root", "root");
    }

    @Bean(name = "entityManagerFactory")
    public SessionFactory getCurrentSessionFromConfig() {
        // SessionFactory in Hibernate 5 example
        Configuration config = new Configuration();
        config.addAnnotatedClass(User.class);
        config.configure();
        return config.buildSessionFactory();
    }
}
