package cz.muni.fi.pa165.photographyclub.beanmapping.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author Pavel Brousek
 */
@Configuration
@ComponentScan(basePackages = {"cz.muni.fi.pa165.photographyclub.beanmapping"})
public class BeanMappingApplicationContext {
    @Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();
		return dozer;
    }
}
