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
        if (label instanceof ComboItem) {
            label = ((ComboItem)label).getLabel();
        }
        super.getListCellRendererComponent(list, label, index, isSelected, cellHasFocus);
        return this;
    }
}
