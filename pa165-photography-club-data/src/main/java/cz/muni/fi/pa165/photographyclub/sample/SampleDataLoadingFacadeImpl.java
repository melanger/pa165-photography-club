package cz.muni.fi.pa165.photographyclub.sample;

import cz.muni.fi.pa165.photographyclub.service.EquipmentService;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import cz.muni.fi.pa165.photographyclub.service.ReviewService;
import cz.muni.fi.pa165.photographyclub.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Pavel Brousek
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    public static final String JPEG = "image/jpeg";

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private TourService tourService;

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        // TODO: create sample data
    }


}

