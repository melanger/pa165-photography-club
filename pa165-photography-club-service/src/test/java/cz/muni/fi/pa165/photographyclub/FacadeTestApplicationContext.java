package cz.muni.fi.pa165.photographyclub;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author Pavel Brousek
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "cz.muni.fi.pa165.photographyclub.facade.dummy", lazyInit = true)
public class FacadeTestApplicationContext {

}
