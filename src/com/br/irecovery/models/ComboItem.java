package com.br.irecovery.models;

/**
 *
 * @author ailson
 */
public class ComboItem {
    private String key;
    private String value;

    public ComboItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString() {
        return "ComboItem{" + "key=" + key + ", value=" + value + '}';
    }
    
}
