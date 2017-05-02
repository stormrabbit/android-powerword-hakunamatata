package com.siegfrield.hakuna_matata.model.data;

/**
 * Created by Administrator on 2017/5/2.
 */

public class MenuEntry implements TestDemo {


    public MenuEntry(String title, Class clazz) {
        this.title = title;
        this.clazz = clazz;
    }

    public String title;
    public Class clazz;




    @Override
    public String valueToString() {
        return "MenuEntry{" +
                "title='" + title + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    @Override
    public void stringToValue(String string) {
    }
}
