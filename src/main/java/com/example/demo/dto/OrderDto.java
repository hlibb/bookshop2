package com.example.demo.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data @Setter(AccessLevel.NONE)
public class OrderDto {
    private Long userId;
    private List<Long> bookIds;
}
