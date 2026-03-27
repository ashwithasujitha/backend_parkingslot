package com.example.demo.controller;

import com.example.demo.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public List<Map<String, Object>> searchParking(@RequestParam String location) {
        return searchService.search(location);
    }
}
