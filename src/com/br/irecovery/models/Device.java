package com.br.irecovery.models;

/**
 *
 * @author ailson
 */
public class Device {
    private String deviceID;
    private String caption;
    private String size;
    private String index;

    public String getDeviceID() {
	return deviceID;
    }
    
    public void setDeviceID(String deviceID) {
	this.deviceID = deviceID;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    } 
    
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Device{" + "deviceID=" + deviceID + ", caption=" + caption + ", size=" + size + ", index=" + index + '}';
    }
    
}
