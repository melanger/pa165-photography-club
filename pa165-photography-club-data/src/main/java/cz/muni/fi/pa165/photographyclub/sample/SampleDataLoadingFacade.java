package cz.muni.fi.pa165.photographyclub.sample;

import java.io.IOException;

/**
 * Populates database with sample data.
 *
 * @author Pavel Brousek
 */
public interface SampleDataLoadingFacade {

    void loadData() throws IOException;
}