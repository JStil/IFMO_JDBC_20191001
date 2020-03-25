package com.efimchick.ifmo.web.jdbc;

import com.efimchick.ifmo.web.jdbc.domain.Employee;
import com.efimchick.ifmo.web.jdbc.domain.FullName;
import com.efimchick.ifmo.web.jdbc.domain.Position;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;

public class RowMapperFactory {

    public RowMapper<Employee> employeeRowMapper() {
        return RS -> {
            try {
                return new Employee(
                    new BigInteger(RS.getString("id")),
                    new FullName(RS.getString("firstname"), RS.getString("lastname"), RS.getString("middlename")),
                    Position.valueOf(RS.getString("position")),
                    LocalDate.parse(RS.getString("hiredate")),
                    RS.getBigDecimal("salary")
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}


