package com.example.serious.demo.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.HashSet;

@Accessors(chain = true)
@Data
public class User implements Serializable {
    private int id;
    private  String name;
    private String password;
    private String mobilePhone;

}
