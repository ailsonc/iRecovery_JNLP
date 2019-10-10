package com.br.irecovery.models;

/**
 *
 * @author ailson
 */
public class Image {
    private String name;
    private String fileName;
    private String fileDir;
    private String fileHash;

    public Image(String name, String fileName, String fileDir, String fileHash) {
        this.name = name;
        this.fileName = fileName;
        this.fileDir = fileDir;
        this.fileHash = fileHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    @Override
    public String toString() {
        return "Image{" + "name=" + name + ", fileName=" + fileName + ", fileDir=" + fileDir + ", fileHash=" + fileHash + '}';
    }
     
}
