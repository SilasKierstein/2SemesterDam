package dk.sdu.ecommerceprototype.dam;

public interface MediaFetcherInterface {

    public Media getMedia(String UUID);

    public Media getMedia(String attributeKey, String attributeValue);

    boolean deleteMedia(String UUID);

}

