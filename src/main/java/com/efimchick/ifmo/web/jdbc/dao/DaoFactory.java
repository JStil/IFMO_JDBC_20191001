package com.efimchick.ifmo.web.jdbc.dao;

import com.efimchick.ifmo.web.jdbc.ConnectionSource;
import com.efimchick.ifmo.web.jdbc.domain.Department;
import com.efimchick.ifmo.web.jdbc.domain.Employee;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DaoFactory {
    public EmployeeDao employeeDAO() {
        return new EmployeeDao() {
            @Override
            public List<Employee> getByDepartment(Department department) {
                List<Employee> employees = new LinkedList<>();
                String query = "select * from employee where department=?";

                return getEmployees(employees, query, department.getId());
            }

            @Override
            public List<Employee> getByManager(Employee employee) {
                List<Employee> employees = new LinkedList<>();
                String query = "select * from employee where manager=?";
                return getEmployees(employees, query, employee.getId());
            }

            @Override
            public Optional<Employee> getById(BigInteger Id) {
                String query = "select * from employee where id=?";
                return getOptionalEmployee(Id, query);
            }

            @Override
            public List<Employee> getAll() {
                List<Employee> employees = new LinkedList<>();
                String query = "select * from employee";
                try {
                    ResultSet RS = ConnectionSource.instance().createConnection().createStatement().executeQuery(query);
                    while (RS.next()) employees.add(Helper.getEmp(RS));
                    return employees;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Employee save(Employee employee) {
                try {
                    String query  = "insert into employee values (?,?,?,?,?,?,?,?,?)";
                    PreparedStatement statement = ConnectionSource.instance().createConnection().prepareStatement(query);
                    statement.setInt(1, employee.getId().intValue());
                    statement.setString(2, employee.getFullName().getFirstName());
                    statement.setString(3, employee.getFullName().getLastName());
                    statement.setString(4, employee.getFullName().getMiddleName());
                    statement.setString(5, employee.getPosition().toString());
                    statement.setInt(6, employee.getManagerId().intValue());
                    statement.setDate(7, Date.valueOf(employee.getHired()));
                    statement.setDouble(8, employee.getSalary().doubleValue());
                    statement.setInt(9, employee.getDepartmentId().intValue());
                    statement.executeUpdate();
                    return employee;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void delete(Employee employee) {
                String query = "delete from employee e where e.id=?";
                deleteById(query, employee.getId());
            }
        };
    }

    private List<Employee> getEmployees(List<Employee> employees, String query, BigInteger id) {
        try {
            PreparedStatement statement = ConnectionSource.instance().createConnection().prepareStatement(query);
            statement.setInt(1, id.intValue());
            ResultSet RS = statement.executeQuery();
            while (RS.next()) employees.add(Helper.getEmp(RS));
            return  employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Optional<Employee> getOptionalEmployee(BigInteger id, String query) {
        try {
            Optional<Employee> employee = Optional.empty();
            PreparedStatement statement = ConnectionSource.instance().createConnection().prepareStatement(query);
            statement.setInt(1, id.intValue());
            ResultSet RS = statement.executeQuery();
            return RS.next() ? Optional.of(Helper.getEmp(RS)) : employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public DepartmentDao departmentDAO() {
        return new DepartmentDao() {
            @Override
            public Optional<Department> getById(BigInteger Id) {
                String query = "select * from department dep where dep.id=?";
                return getOptionalDep(Id, query);
            }

            @Override
            public List<Department> getAll() {
                List<Department> departments = new LinkedList<>();
                String query = "select * from department dep";
                try {
                    ResultSet RS = ConnectionSource.instance().createConnection().createStatement().executeQuery(query);
                    while (RS.next()) departments.add(Helper.getDep(RS));
                    return departments;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Department save(Department department) {
                String query = "update department dep set name=?, location=? where id=?";
                PreparedStatement statement;
                try {
                    if (getById(BigInteger.valueOf(department.getId().intValue())).isPresent()) {
                        statement = ConnectionSource.instance().createConnection().prepareStatement(query);
                        statement.setString(1, department.getName());
                        statement.setString(2, department.getLocation());
                        statement.setInt(3, department.getId().intValue());
                        statement.executeUpdate();
                        return department;
                    }
                    query = "insert into department dep values (?,?,?)";
                    statement = ConnectionSource.instance().createConnection().prepareStatement(query);
                    statement.setInt(1, department.getId().intValue());
                    statement.setString(2, department.getName());
                    statement.setString(3, department.getLocation());
                    statement.executeUpdate();
                    return department;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return department;
            }

            @Override
            public void delete(Department department) {
                String query = "delete from department where id=?";
                deleteById(query, department.getId());
            }
        };
    }

    private Optional<Department> getOptionalDep(BigInteger id, String query) {
        try {
            Optional<Department> dep = Optional.empty();
            PreparedStatement statement = ConnectionSource.instance().createConnection().prepareStatement(query);
            statement.setInt(1, id.intValue());
            ResultSet RS = statement.executeQuery();
            return RS.next() ? Optional.of(Helper.getDep(RS)) : dep;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private void deleteById(String query, BigInteger id) {
        try {
            PreparedStatement statement = ConnectionSource.instance().createConnection().prepareStatement(query);
            statement.setInt(1, id.intValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
