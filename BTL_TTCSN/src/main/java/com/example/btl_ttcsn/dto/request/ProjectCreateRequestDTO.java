package com.example.btl_ttcsn.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateRequestDTO {
    private String name;
    private String material;
    private Double budget;
    private String description;
    private Date startday;
    private Date deadline;
}
