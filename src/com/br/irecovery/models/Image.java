package com.br.irecovery.models;

/**
 *
 * @author ailson
 */
public class Image {
    private int id;
    private String name;
    private String description;
    private String fileName;
    private String filePath;
    private String fileHash;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", name=" + name + ", description=" + description + ", fileName=" + fileName + ", filePath=" + filePath + ", fileHash=" + fileHash + '}';
    }
     
}
