package org.mrshoffen.cities_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageResponse {

    @JsonProperty("data")
    ImgData data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ImgData {
        @JsonProperty("url")
        String url;

        @JsonProperty("display_url")
        String displayUrl;
    }
}
