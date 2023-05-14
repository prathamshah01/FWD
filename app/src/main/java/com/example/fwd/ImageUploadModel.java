package com.example.fwd;

public class ImageUploadModel {
    private String imager;
    private String description;

    public ImageUploadModel(String imager, String description) {
        this.imager = imager;
        this.description = description;
    }

    // Default constructor (no-argument constructor)
    public ImageUploadModel() {
        // Initialize default values or perform necessary setup
    }

//    public ImageUploadModel(String imager, String description) {
//
//
//    }

    // Other constructors and methods

    // Getter and setter methods for the instance variables
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