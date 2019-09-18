package com.itlike.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter@Getter@ToString
public class Role {
    private Long rid;

    private String rnum;

    private String rname;

    private List<Permission> permissions;

}