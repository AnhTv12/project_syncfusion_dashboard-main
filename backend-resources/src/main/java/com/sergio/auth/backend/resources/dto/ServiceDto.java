package com.sergio.auth.backend.resources.dto;
import lombok.*;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ServiceDto {

    private int serviceId;
    private String name;
    private String description;
    private String serviceShift;
    private List<Integer> customerIds;
    private List<Integer> orderIds;
    private Long price;

}

