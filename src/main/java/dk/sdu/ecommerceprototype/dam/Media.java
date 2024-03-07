package dk.sdu.ecommerceprototype.dam;

import java.net.URL;

public class Media {

    private String name;

    private String fileType;

    private URL path;

    private Long size;

    public String getName() {
        return name;
    }

    public String getFileType() {
        return fileType;
    }

    public URL getPath() {
        return path;
    }

    public Long getSize() { return size; }




    public Media(String name, String fileType, URL path, Long size) {
        if (name == null || name.isEmpty() || fileType == null || fileType.isEmpty()) {
            throw new IllegalArgumentException("Navn og filtype kan ikke være tomme");
        }
        this.name = name;
        this.fileType = fileType;
        this.path = path;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Media{" +
                "name='" + name + '\'' +
                ", fileType='" + fileType + '\'' +
                ", size=" + size +
                ", path=" + path +
                '}';
    }
}


