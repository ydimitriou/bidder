package com.bluebanana.bidder.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

public class FixtureLoader {

    public String load(String url) {
        String json;
        try {
            json = Resources.toString(Resources.getResource(url), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Can't find resources on path" + url);
        }

        return json;
    }
}
