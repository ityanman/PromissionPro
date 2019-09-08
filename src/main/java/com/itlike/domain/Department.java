package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter@Getter@ToString
public class Department {
    private Long id;
    private String name;
    private String sn;
    private Long managerId;
    private Long parentId;
    private Boolean state;

}