package com.hfad.vacationdestination;

public class VacationDestination {

    private String placeName;
    private int imageId;
    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public VacationDestination(String n, int id, boolean f)
    {
        placeName = n;
        imageId =  id;
        isFavorite = f;

    }

    public String getName()
    {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
