package com.skyscanner;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {

    private final List<SearchResult> searchResults;

    public SearchResource(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    @POST
    public List<SearchResult> search(Search search) {
        if (search == null || search.getCity() == null) {
            return List.of();
        }

        String city = search.getCity().trim().toLowerCase(Locale.ROOT);

        return searchResults.stream()
                .filter(result -> result.getCity() != null
                        && result.getCity().trim().toLowerCase(Locale.ROOT).equals(city))
                .collect(Collectors.toList());
    }
}
