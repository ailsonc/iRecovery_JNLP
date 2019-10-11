package com.br.irecovery.models;

import com.br.irecovery.util.JWMICustom;
import com.br.irecovery.util.Log;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
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
        Log.setLog(Level.INFO, device.toString());		
    }
        
    public ArrayList<Device> getDevices() {
        StringTokenizer deviceID = new StringTokenizer(this.device.getDeviceID(), "\n");
	StringTokenizer caption = new StringTokenizer(this.device.getCaption(), "\n");	
        StringTokenizer size = new StringTokenizer(this.device.getSize(), "\n");
        StringTokenizer index = new StringTokenizer(this.device.getIndex(), "\n");
        
        while (deviceID.hasMoreTokens() && caption.hasMoreTokens() && size.hasMoreTokens() && index.hasMoreTokens()) {
	    Device devicePlace = new Device();
	    devicePlace.setDeviceID(deviceID.nextToken().trim());
	    devicePlace.setCaption(caption.nextToken().trim());
            devicePlace.setSize(size.nextToken().trim());
            devicePlace.setIndex(index.nextToken().trim());
	    devices.add(devicePlace);
	}
	return devices;
    }
    
}
