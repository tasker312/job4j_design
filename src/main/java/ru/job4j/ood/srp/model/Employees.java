package ru.job4j.ood.srp.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Employees {

    @XmlElement(name = "employee")
    private final List<Employee> employees;

    public Employees() {
        employees = new ArrayList<>();
    }

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
