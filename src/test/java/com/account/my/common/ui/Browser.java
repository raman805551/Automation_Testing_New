package com.account.my.common.ui;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    INTERNET_EXPLORE("internet explore"),
    SAFARI("safari"),
    CHROME_HEDLESS("chrome_hedless");

    private String value;

    Browser(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
