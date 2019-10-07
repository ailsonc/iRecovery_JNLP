package com.br.irecovery.models;

import com.br.irecovery.util.JWMICustom;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 *
 * @author ailson
 */
public class Devices {
    private Device device = new Device();
    private ArrayList<Device> devices = new ArrayList();
           
    public void setDevices() {
	final JWMICustom custom = new JWMICustom();
        device.setDeviceID(custom.getNaWMI("Win32_LogicalDisk WHERE Description = 'Removable Disk' OR Description = 'Disco Removível' ", "DeviceID"));
        device.setSize(custom.getNaWMI("Win32_LogicalDisk WHERE Description = 'Removable Disk' OR Description = 'Disco Removível' ", "Size"));
        device.setCaption(custom.getNaWMI("Win32_DiskDrive WHERE MediaType = 'Removable Media'", "Caption"));
        device.setIndex(custom.getNaWMI("Win32_DiskDrive WHERE MediaType = 'Removable Media'", "Index"));
        System.out.println(device.toString());			
    }
        
    public ArrayList<Device> getDevices() {
        StringTokenizer deviceID = new StringTokenizer(this.device.getDeviceID(), "\n");
	StringTokenizer caption = new StringTokenizer(this.device.getCaption(), "\n");	
        StringTokenizer size = new StringTokenizer(this.device.getSize(), "\n");
        StringTokenizer index = new StringTokenizer(this.device.getIndex(), "\n");
        
        while (deviceID.hasMoreTokens() && caption.hasMoreTokens() && size.hasMoreTokens() && index.hasMoreTokens()) {
	    Device device = new Device();
	    device.setDeviceID(deviceID.nextToken().trim());
	    device.setCaption(caption.nextToken().trim());
            device.setSize(size.nextToken().trim());
            device.setIndex(index.nextToken().trim());
	    devices.add(device);
	}
	return devices;
    }
    
}
