package dk.sdu.ecommerceprototype.dam;

import dk.sdu.ecommerceprototype.Root;

public class MediaFetcher implements MediaFetcherInterface{

    @Override
    public Media getMedia(String UUID) {
        Media test = new Media("testimage", "png", Root.getFxmlFile("dam/images/testimage.png"));
        return test;
    }

    @Override
    public Media getMedia(String attributeKey, String attributeValue) {
        Media test = new Media("testimage", "png", Root.getFxmlFile("dam/images/testimage.png"));
        return test;
    }

    @Override
    public boolean deleteMedia(String UUID) {
        return true;
    }
}
