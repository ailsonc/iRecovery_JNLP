package com.br.irecovery.models;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author ailson
 */
public class ComboObjectListCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(
                                   JList list,
                                   Object label,
                                   int index,
                                   boolean isSelected,
                                   boolean cellHasFocus) {
        if (label instanceof Device) {
            label = ((Device)label).getDeviceID() + ((Device)label).getCaption();
        }
        super.getListCellRendererComponent(list, label, index, isSelected, cellHasFocus);
        return this;
    }
}
