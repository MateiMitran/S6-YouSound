package com.backend.userservice.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_settings")
@Data
@Getter
@Setter
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

}
