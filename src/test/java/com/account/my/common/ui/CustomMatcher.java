package com.account.my.common.ui;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomMatcher {

    public static Matcher matches (final Object expected){
        return new BaseMatcher() {
            protected Object expectedObject = expected;
            public boolean matches(Object item){
                return expectedObject.equals(item);
            }
            public void describeTo(Description description){
                description.appendText(expectedObject.toString());
            }

        };
    }
}
