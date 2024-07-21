package com.school.Payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SchoolDto {

    private Long id;


    private String name;


    private String email;


    private String mobile;

}