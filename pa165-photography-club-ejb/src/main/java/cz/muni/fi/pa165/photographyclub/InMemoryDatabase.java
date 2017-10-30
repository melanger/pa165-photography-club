package cz.muni.fi.pa165.photographyclub;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import javax.sql.DataSource;

/**
 * @author Matus Kravec
 */
@Configuration
 public class InMemoryDatabase {
 
     @Bean
     public DataSource db(){
         EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
         EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.DERBY).build();
         return db;
     }
 }
