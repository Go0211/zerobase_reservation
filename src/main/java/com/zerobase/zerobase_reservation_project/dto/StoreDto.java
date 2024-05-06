package com.zerobase.zerobase_reservation_project.dto;

import com.zerobase.zerobase_reservation_project.entity.Store;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

public class StoreDto {
    @Getter
    @Setter
    @ParameterObject
    public static class Request {
        String name;
        String location;
        String description;

        public Store toEntity(Request request) {
            return Store.builder()
                    .name(request.getName())
                    .location(request.getLocation())
                    .description(request.getDescription())
                    .build();
        }
    }

    public static class Response {

    }
}
