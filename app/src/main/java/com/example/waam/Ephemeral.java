package com.example.waam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Ephemeral {

    private String id;
    private String object;
    private  List associated_objects;
    private String created;
    private String expires;
    private String secret;
    private boolean livemode;



    public String getEphemeralString() {
        return id;
    }

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public List getAssociated_objects() {
        return associated_objects;
    }

    public String getCreated() {
        return created;
    }

    public String getExpires() {
        return expires;
    }

    public String getSecret() {
        return secret;
    }

    public boolean isLivemode() {
        return livemode;
    }
}
