package com.example.waam;

import java.io.Serializable;

public class VideoPicModel implements Serializable {
    boolean isVideo;
    String videoPicUrl;

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public String getVideoPicUrl() {
        return videoPicUrl;
    }

    public void setVideoPicUrl(String videoPicUrl) {
        this.videoPicUrl = videoPicUrl;
    }
}
