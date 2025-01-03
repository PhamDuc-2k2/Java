package com.ducpham.model;

import com.ducpham.enums.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Long id;
    private String code;
    private String name;
    private int age;
    private EnumGender gender;
}
