package com.example.fwd;

public class ImageUploadModel {
    String imager, description;

    public ImageUploadModel(String imager, String description) {
        this.imager = imager;
        this.description = description;
    }

    public String getImager() {
        return imager;
    }

    public void setImager(String imager) {
        this.imager = imager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ImageUploadModel{" +
                "imager='" + imager + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}