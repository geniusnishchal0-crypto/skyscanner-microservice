package com.skyscanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HoenScannerConfiguration> bootstrap) {
    }

    @Override
    public void run(HoenScannerConfiguration configuration, Environment environment) throws Exception {
        ObjectMapper mapper = environment.getObjectMapper();
        List<SearchResult> searchResults = new ArrayList<>();

        try (InputStream hotels = getClass().getResourceAsStream("/hotels.json");
             InputStream cars = getClass().getResourceAsStream("/rental_cars.json")) {

            if (hotels == null || cars == null) {
                throw new IllegalStateException("Required JSON resource files were not found.");
            }

            searchResults.addAll(mapper.readValue(hotels, new TypeReference<List<SearchResult>>() {}));
            searchResults.addAll(mapper.readValue(cars, new TypeReference<List<SearchResult>>() {}));
        }

        environment.jersey().register(new SearchResource(searchResults));
    }
}
