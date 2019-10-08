package com.br.irecovery.models;

/**
 *
 * @author ailson
 */
public class Image {
    private String name;
    private String profile;
    private String md5;
    private String description;
    private String system;

    public Image(String name, String profile, String md5, String description, String system) {
        this.name = name;
        this.profile = profile;
        this.md5 = md5;
        this.description = description;
        this.system = system;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "Image{" + "name=" + name + ", profile=" + profile + ", description=" + description + ", system=" + system + '}';
    }
    
}
