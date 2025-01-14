package org.mrshoffen.cities_api.controller;

import lombok.RequiredArgsConstructor;
import org.mrshoffen.cities_api.dto.CityResponseDto;
import org.mrshoffen.cities_api.repository.CitiesRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities-autofill-api")
@RequiredArgsConstructor
public class CityController {

    private final CitiesRepository citiesRepository;
    @GetMapping
    public ResponseEntity<List<CityResponseDto>> getCities(@RequestParam(value = "name") String name) {


        Pageable pageRequest = PageRequest.of(0, 8);
        List<CityResponseDto> list = citiesRepository.findUniqueCities(name, pageRequest)
                .stream()
                .map(CityResponseDto::new)
                .toList();

        return ResponseEntity.ok(list);
    }
}
