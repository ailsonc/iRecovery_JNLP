package com.br.irecovery.models;

/**
 *
 * @author ailson
 */
public class ComboItem {
    private String label;
    private String value;

    public ComboItem(String label, String value)
    {
        this.label = label;
        this.value = value;
    }

    public String getLabel()
    {
        return label;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString() {
        return "ComboItem{" + "label=" + label + ", value=" + value + '}';
    }
    
}
