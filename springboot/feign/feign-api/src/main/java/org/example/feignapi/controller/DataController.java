package org.example.feignapi.controller;

import jakarta.annotation.PostConstruct;
import org.example.feignapi.dto.DataRequest;
import org.example.feignapi.dto.DataResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {
    private Map<Long, DataResponse> dataStore = new HashMap<>();
    private Long idCounter = 1L;

    @PostConstruct
    public void initDataStore() {
        dataStore.put(idCounter++, new DataResponse(1L, "Item 1", 100));
        dataStore.put(idCounter++, new DataResponse(2L, "Item 2", 200));
        dataStore.put(idCounter++, new DataResponse(3L, "Item 3", 300));
        dataStore.put(idCounter++, new DataResponse(4L, "Item 4", 400));
        dataStore.put(idCounter++, new DataResponse(5L, "Item 5", 500));
    }

    @GetMapping("/{id}")
    public DataResponse getDataById( @PathVariable Long id ) {

        DataResponse dataResponse = dataStore.get(id);
        if (dataResponse == null) {
            throw new RuntimeException("DataResponse not found" + id);
        }
        return dataResponse;
    }

    @PostMapping
    public DataResponse createData( @RequestBody DataRequest dataRequest ) {
        DataResponse build = DataResponse.builder()
                .id(idCounter++)
                .value(dataRequest.getValue())
                .name(dataRequest.getName())
                .build();

        dataStore.put(build.getId(), build);

        return build;
    }

    @PutMapping("/{id}")
    public DataResponse updateData(
            @PathVariable("id") Long id,
            @RequestBody DataRequest dataRequest
    ) {
        DataResponse dataResponse = dataStore.get(id);
        if (dataResponse == null) {
            throw new RuntimeException("DataResponse not found(update)" + id);
        }
        DataResponse updated = new DataResponse(
                id,
                dataRequest.getName(),
                dataRequest.getValue()
        );

        dataStore.put(id, updated);
        return updated;
    }

    @DeleteMapping("/{id}")
    public String deleteData( @PathVariable("id") Long id ) {
        DataResponse dataResponse = dataStore.remove(id);
        if (dataResponse == null) {
            throw new RuntimeException("DataResponse not found(delete)" + id);
        }
        return "Data deleted with id: " + id;
    }
}
