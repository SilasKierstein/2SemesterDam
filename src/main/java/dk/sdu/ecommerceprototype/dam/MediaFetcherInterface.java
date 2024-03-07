package dk.sdu.ecommerceprototype.dam;

public interface MediaFetcherInterface {

    Media getMedia(Long UUID);
    boolean deleteMedia(Long UUID);
    boolean saveMedia(Long UUID);
    boolean updateMedia(Long UUID);
}

