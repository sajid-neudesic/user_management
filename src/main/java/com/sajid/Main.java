package com.sajid;

import java.sql.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws SQLException {

        SpringApplication.run(Main.class, args);
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");


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

    @Bean
    public DatabaseDAO createDatabaseDao() throws Exception {
        return new DatabaseDAO(createConnection());
        //return new DatabaseDAO(DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db?useSSL=false", "root", "root"));
    }

    @Bean
    public UserService createUserService() throws Exception {
        return new UserService(createDatabaseDao());
    }

    @Bean
    public UserServiceHibernate createUserServiceHibernate() throws Exception {
        return new UserServiceHibernate(getCurrentSessionFromConfig());
    }

    private static SessionFactory getCurrentSessionFromConfig() {
        // SessionFactory in Hibernate 5 example
        Configuration config = new Configuration();
        config.addAnnotatedClass(User.class);
        config.configure();
        // local SessionFactory bean created
        SessionFactory sessionFactory = config.buildSessionFactory();
        //Session session = sessionFactory.getCurrentSession();
        //return session;
        return sessionFactory;
    }
}
