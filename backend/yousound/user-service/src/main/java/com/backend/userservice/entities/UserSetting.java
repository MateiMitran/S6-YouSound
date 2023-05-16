package com.backend.userservice.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_settings")
@Data
public class UserSetting {

    @Id
    private String id;
    private String user_id;
    private Boolean offline;
    private Boolean gapless_playback;
    private Boolean mono_audio;
    private Boolean autoplay;
    private Boolean explicit_content;

    private String audio_quality;

    public UserSetting(String id, String user_id, Boolean offline, Boolean gapless_playback, Boolean mono_audio, Boolean autoplay, Boolean explicit_content, String audio_quality) {
        this.id = id;
        this.user_id = user_id;
        this.offline = offline;
        this.gapless_playback = gapless_playback;
        this.mono_audio = mono_audio;
        this.autoplay = autoplay;
        this.explicit_content = explicit_content;
        this.audio_quality = audio_quality;
    }

    public UserSetting() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Boolean getOffline() {
        return offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

    public Boolean getGapless_playback() {
        return gapless_playback;
    }

    public void setGapless_playback(Boolean gapless_playback) {
        this.gapless_playback = gapless_playback;
    }

    public Boolean getMono_audio() {
        return mono_audio;
    }

    public void setMono_audio(Boolean mono_audio) {
        this.mono_audio = mono_audio;
    }

    public Boolean getAutoplay() {
        return autoplay;
    }

    public void setAutoplay(Boolean autoplay) {
        this.autoplay = autoplay;
    }

    public Boolean getExplicit_content() {
        return explicit_content;
    }

    public void setExplicit_content(Boolean explicit_content) {
        this.explicit_content = explicit_content;
    }

    public String getAudio_quality() {
        return audio_quality;
    }

    public void setAudio_quality(String audio_quality) {
        this.audio_quality = audio_quality;
    }
}
