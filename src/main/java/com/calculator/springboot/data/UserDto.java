package com.calculator.springboot.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long age;
    private String name;
    private Address address;
}
