package cz.muni.fi.pa165.photographyclub;

import cz.muni.fi.pa165.photographyclub.service.GenericService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author Pavel Brousek
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackageClasses={GenericService.class}, basePackages = "cz.muni.fi.pa165.photographyclub")
public class ServiceTestApplicationContext {

}
