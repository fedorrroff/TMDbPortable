package com.fedorrroff.trainee2;

import java.io.Serializable;

public class SomeObj implements Serializable {

    private String someText;

    public void setSomeText(String someText) {
        this.someText = someText;
    }

    public String getSomeText() {
        return someText;
    }
}
