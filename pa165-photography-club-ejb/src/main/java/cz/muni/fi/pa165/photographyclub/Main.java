package cz.muni.fi.pa165.photographyclub;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    
    private static EntityManagerFactory emf;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // The following line is here just to start up a in-memory database
        new AnnotationConfigApplicationContext(InMemoryDatabase.class);
        
        emf = Persistence.createEntityManagerFactory("default");
        
        emf.close();
    }
    
}
