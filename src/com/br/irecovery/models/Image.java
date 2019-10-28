package com.br.irecovery.models;

/**
 *
 * @author ailson
 */
public class Image {
    private int id;
    private String name;
    private String description;
    private String originalName;
    private String fileName;
    private String fileSize;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", name=" + name + ", description=" + description + ", originalName=" + originalName + ", fileName=" + fileName + ", fileSize=" + fileSize + '}';
    }
     
}
