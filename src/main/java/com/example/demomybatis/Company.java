package com.example.demomybatis;

import lombok.Data;
import sun.plugin.util.UserProfile;

import java.util.List;

@Data
public class Company {
    private int id;
    private String name;
    private String address;
    private List<Employee> employeeList;

}
