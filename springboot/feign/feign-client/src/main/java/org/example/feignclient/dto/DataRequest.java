package org.example.feignclient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class DataRequest {
    private String name;
    private int value;
}
