package Admin.Gallery;

import java.lang.String;

import java.util.ArrayList;

public class GalleryModel {
    private String captions;
    private ArrayList<String> ImagesUrl;

    public GalleryModel(String captions, ArrayList<String> imagesUrl) {
        this.captions = captions;
        ImagesUrl = imagesUrl;
    }

    public GalleryModel() {
    }


    public String getCaptions() {
        return captions;
    }

    public void setCaptions(String captions) {
        this.captions = captions;
    }

    public ArrayList<String> getImagesUrl() {
        return ImagesUrl;
    }

    public void setImagesUrl(ArrayList<String> imagesUrl) {
        ImagesUrl = imagesUrl;
    }
}
