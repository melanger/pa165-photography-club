package cz.muni.fi.pa165.photographyclub.service.config;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.beanmapping.config.BeanMappingApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @author Pavel Brousek
 */
@Configuration
@Import({PersistenceSampleApplicationContext.class, BeanMappingApplicationContext.class})
@ComponentScan(basePackages = {"cz.muni.fi.pa165.photographyclub.service", "cz.muni.fi.pa165.photographyclub.facade"})
public class ServiceApplicationContext {
    
}
