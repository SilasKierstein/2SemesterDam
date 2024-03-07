package dk.sdu.ecommerceprototype.dam;

import dk.sdu.ecommerceprototype.Root;

public class MediaFetcher implements MediaFetcherInterface {

    @Override
    public Media getMedia(Long UUID) {
        Media test = new Media("testimage", "png", Root.getFxmlFile("dam/images/testimage.png"));
        return test;
    }

    @Override
    public boolean saveMedia(Long UUID) {
        return false;
    }

    @Override
    public boolean updateMedia(Long UUID) {
        return false;
    }
    @Override
    public boolean deleteMedia(Long UUID) {
        return false;
    }

}



