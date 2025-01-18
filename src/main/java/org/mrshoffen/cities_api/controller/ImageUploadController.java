package org.mrshoffen.cities_api.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mrshoffen.cities_api.dto.ImageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image-upload-api")
public class ImageUploadController {

    private final RestClient restClient;

    @Value("${app.image.imgbb-key}")
    private String key;

    @Value("${app.image.upload-url}")
    private String url;

    @GetMapping
    ResponseEntity<Map<String, String>> testGet() {
        return ResponseEntity.ok(Map.of("get", "is working"));
    }

    @SneakyThrows
    @PostMapping(consumes = {"multipart/form-data"})
    ResponseEntity<Map<String, String>> uploadImage(@RequestParam("image") MultipartFile image) {

        URI uri = URI.create(url.formatted(key));


        MultiValueMap<String, Object> parts = buildMultipart(image);


        ImageResponse imageResponse = restClient
                .post()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(parts)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });


        return ResponseEntity.ok(
                Map.of("imageUrl", imageResponse.getData().getUrl())
        );

    }

    private static MultiValueMap<String, Object> buildMultipart(MultipartFile image) throws IOException {
        InputStreamResource imageResource = new InputStreamResource(image.getInputStream()) {
            @Override
            public String getFilename() {
                return image.getOriginalFilename();
            }

            @Override
            public long contentLength() throws IOException {
                // Return the resource size
                return image.getSize();
            }
        };

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("image", imageResource);
        return parts;
    }

}
