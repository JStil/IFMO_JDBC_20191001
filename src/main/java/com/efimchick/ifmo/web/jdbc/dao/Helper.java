package com.efimchick.ifmo.web.jdbc.dao;

import com.efimchick.ifmo.web.jdbc.domain.Department;
import com.efimchick.ifmo.web.jdbc.domain.Employee;
import com.efimchick.ifmo.web.jdbc.domain.FullName;
import com.efimchick.ifmo.web.jdbc.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Helper {
    public static Employee getEmp(ResultSet RS) throws SQLException {
        BigInteger id = new BigInteger(RS.getString("id"));
        FullName fName = new FullName(
                RS.getString("firstName"),
                RS.getString("lastName"),
                RS.getString("middleName")
        );
        Position position = Position.valueOf(RS.getString("position"));
        LocalDate date = LocalDate.parse(RS.getString("hireDate"));
        BigDecimal salary = RS.getBigDecimal("salary");
        BigInteger man;
        BigInteger dep;
        Object manObject = RS.getObject("manager");
        Object depObject = RS.getObject("department");
        if (manObject != null) man = new BigInteger(RS.getString("manager"));
        else man = new BigInteger("0");
        if (depObject != null) dep = new BigInteger(RS.getString("department"));
        else dep = new BigInteger("0");
        return new Employee(id, fName, position, date, salary, man, dep);
    }

    public static Department getDep(ResultSet RS) throws SQLException {
        System.out.println("id = "+RS.getString("id"));
        return new Department(new BigInteger(RS.getString("id")), RS.getString("name"), RS.getString("location"));
    }

//    public List<Employee> getList() {
//
//    }
}
