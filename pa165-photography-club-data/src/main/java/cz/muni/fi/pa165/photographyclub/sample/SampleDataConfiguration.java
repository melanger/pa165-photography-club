package cz.muni.fi.pa165.photographyclub.sample;

import cz.muni.fi.pa165.photographyclub.service.config.ServiceApplicationContext;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Takes ServiceApplicationContext and adds the SampleDataLoadingFacade bean.
 * @author Pavel Brousek
 */
@Configuration
@Import(ServiceApplicationContext.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class SampleDataConfiguration {
    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;
    
    @PostConstruct
    public void dataLoading() throws IOException {
        sampleDataLoadingFacade.loadData();
    }
}
