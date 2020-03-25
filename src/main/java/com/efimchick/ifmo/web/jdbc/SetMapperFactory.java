package com.efimchick.ifmo.web.jdbc;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.efimchick.ifmo.web.jdbc.domain.Employee;
import com.efimchick.ifmo.web.jdbc.domain.FullName;
import com.efimchick.ifmo.web.jdbc.domain.Position;

public class SetMapperFactory {

    public SetMapper<Set<Employee>> employeesSetMapper() {
        return new SetMapper<Set<Employee>>() {
            public Set<Employee> mapSet(ResultSet RS) {
                try {
                        Set<Employee> employeeSet = new HashSet<>();
                        while (RS.next()) {
                            employeeSet.add(getEmployeeOrManager(RS, true));
                        }
                        return employeeSet;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                return null;
            }
        };
    }

    public Employee getEmployeeOrManager(ResultSet RS, boolean empOverMan) throws SQLException{
        if (empOverMan) {
            boolean manager = true;
            if (RS.getInt("manager") == 0) {
                manager = false;
            }
            return new Employee(
                    new BigInteger(RS.getString("id")),
                    new FullName(RS.getString("firstname"), RS.getString("lastname"), RS.getString("middlename")),
                    Position.valueOf(RS.getString("position")),
                    LocalDate.parse(RS.getString("hiredate")),
                    RS.getBigDecimal("salary"),
                    manager ? getEmployeeOrManager(RS, false) : null
            );
        } else {
            int row = RS.getRow();
            int manID = RS.getInt("manager");
            Employee man = null;
            RS.beforeFirst();
            while (RS.next()) {
                if (RS.getInt("id") == manID ) man = getEmployeeOrManager(RS, true);
            }
            RS.absolute(row);
            return man;
        }
    }
}
