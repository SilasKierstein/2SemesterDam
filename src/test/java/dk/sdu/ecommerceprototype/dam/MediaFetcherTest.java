package dk.sdu.ecommerceprototype.dam;

import dk.sdu.ecommerceprototype.dam.MediaFetcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MediaFetcherTest {
    @Test
    public void returnsImageTest() {
        MediaFetcher fetcher = new MediaFetcher();
        Media testimage = fetcher.getMedia(1234L);
        assertEquals("testimage", testimage.getName());
        assertNotEquals("jpg", testimage.getFileType());
        assertEquals("png", testimage.getFileType());
        assertNotNull(testimage.getPath());
    }
}
