package com.efimchick.ifmo.web.jdbc.service;

import com.efimchick.ifmo.web.jdbc.ConnectionSource;
import com.efimchick.ifmo.web.jdbc.domain.Department;
import com.efimchick.ifmo.web.jdbc.domain.Employee;
import com.efimchick.ifmo.web.jdbc.domain.FullName;
import com.efimchick.ifmo.web.jdbc.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ServiceFactory {

    public EmployeeService employeeService(){
        return new EmployeeService() {
            @Override
            public List<Employee> getAllSortByHireDate(Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                                "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                                " from employee e left join employee m on e.manager=m.id order by e.hireDate limit " + limit + " offset " + offset;
                return getEmployees(query);
            }

            @Override
            public List<Employee> getAllSortByLastname(Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id order by e.lastName limit " + limit + " offset " + offset;
                return getEmployees(query);
            }

            @Override
            public List<Employee> getAllSortBySalary(Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id order by e.salary limit " + limit + " offset " + offset;
                return getEmployees(query);
            }

            @Override
            public List<Employee> getAllSortByDepartmentNameAndLastname(Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id left join department d on e.department=d.id order by d.name, e.lastName limit " + limit + " offset " + offset;
                return getEmployees(query);
            }

            @Override
            public List<Employee> getByDepartmentSortByHireDate(Department department, Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id where" +
                        " e.department=" + department.getId().intValue() + " order by e.hireDate limit " + limit + " offset " + offset;
                        return getEmployees(query);
            }

            @Override
            public List<Employee> getByDepartmentSortBySalary(Department department, Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id where" +
                        " e.department=" + department.getId().intValue() + " order by e.salary limit " + limit + " offset " + offset;
                return getEmployees(query);
            }

            @Override
            public List<Employee> getByDepartmentSortByLastname(Department department, Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id where" +
                        " e.department=" + department.getId().intValue() + " order by e.lastName limit " + limit + " offset " + offset;
                return getEmployees(query);
            }

            @Override
            public List<Employee> getByManagerSortByLastname(Employee manager, Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id where" +
                        " e.manager=" + manager.getId().intValue() + " order by e.lastName limit " + limit + " offset " + offset;
                return getEmployees(query);
            }

            @Override
            public List<Employee> getByManagerSortByHireDate(Employee manager, Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id where" +
                        " e.manager=" + manager.getId().intValue() + " order by e.hireDate limit " + limit + " offset " + offset;
                return getEmployees(query);
            }

            @Override
            public List<Employee> getByManagerSortBySalary(Employee manager, Paging paging) {
                int limit = paging.itemPerPage;
                int offset = paging.itemPerPage*(paging.page-1);
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id where" +
                        " e.manager=" + manager.getId().intValue() + " order by e.salary limit " + limit + " offset " + offset;
                return getEmployees(query);
            }

            @Override
            public Employee getWithDepartmentAndFullManagerChain(Employee employee) {
                String query = "select e.*, d.name dname, d.location dlocation from employee e left join department d on e.department=d.id where e.id=" + employee.getId().toString();
                try {
                    ResultSet RS = ConnectionSource.instance().createConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query);
                    if (RS.next()) {
                        return getChain(RS);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public Employee getTopNthBySalaryByDepartment(int salaryRank, Department department) {
                String query =  "select e.*, m.id mid, m.firstName mfirstName, m.lastName mlastName, m.middleName mmiddleName, m.position mposition, " +
                        "m.hireDate mhiredate, m.salary msalary, m.department mdepartment" +
                        " from employee e left join employee m on e.manager=m.id where e.department=" +
                        department.getId().intValue() +
                        " order by e.salary DESC limit " + 1 + " offset " + (salaryRank-1);
                return (Objects.requireNonNull(getEmployees(query))).get(0);
            }
        };

    }

    private List<Employee> getEmployees(String query) {
        List<Employee> employees = new LinkedList<>();
        try {
            ResultSet RS = ConnectionSource.instance().createConnection().createStatement().executeQuery(query);
            while (RS.next()) employees.add(getEmpWManager(RS));
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Employee getEmpWManager(ResultSet RS) {
        try {
            BigInteger id = new BigInteger(RS.getString("id"));
            FullName fName = new FullName(RS.getString("firstName"), RS.getString("lastName"), RS.getString("middleName"));
            Position position = Position.valueOf(RS.getString("position"));
            LocalDate date = LocalDate.parse(RS.getString("hireDate"));
            BigDecimal salary = RS.getBigDecimal("salary");
            Department dep;
            Department mdep;
            Employee man;
            if (RS.getObject("manager") != null) {
                BigInteger mid = new BigInteger(RS.getString("mid"));
                FullName mfName = new FullName(RS.getString("mfirstName"), RS.getString("mlastName"), RS.getString("mmiddleName"));
                Position mposition = Position.valueOf(RS.getString("mposition"));
                LocalDate mdate = LocalDate.parse(RS.getString("mhireDate"));
                BigDecimal msalary = RS.getBigDecimal("msalary");
                int mdepInt = (RS.getObject("mdepartment")!= null) ? RS.getInt("mdepartment") : 0;
                int depInt = (RS.getObject("department") != null) ? RS.getInt("department") : 0;
                dep = (depInt != 0) ? getDep(depInt) : null;
                mdep = (mdepInt != 0) ? getDep(mdepInt) : null;
                man = new Employee(mid, mfName, mposition, mdate, msalary, null, mdep);
                return new Employee(id, fName, position, date, salary, man, dep);
            }
                dep = RS.getObject("department") != null ? getDep(Integer.parseInt(RS.getString("department"))) : null;
                return new Employee(id, fName, position, date, salary, null, dep);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Department getDep(Integer id) throws SQLException {
        String query = "select * from department where id=?";
        PreparedStatement statement = ConnectionSource.instance().createConnection().prepareStatement(query);
        statement.setInt(1, id);
        ResultSet RS = statement.executeQuery();
        if (RS.next()) return new Department(new BigInteger(RS.getString("id")), RS.getString("name"), RS.getString("location"));
        return null;
    }

    private Employee getChain(ResultSet RS) throws SQLException {
        Department dep = (RS.getObject("department") == null) ? null : new Department(new BigInteger(RS.getString("department")), RS.getString("dname"), RS.getString("dlocation"));
        return new Employee(
                BigInteger.valueOf(RS.getInt("id")),
                new FullName(RS.getString("firstName"),RS.getString("lastName"),RS.getString("middleName")),
                Position.valueOf(RS.getString("position")),
                LocalDate.parse(RS.getString("hireDate")),
                RS.getBigDecimal("salary"),
                (RS.getObject("manager")!=null) ? getManChain(BigInteger.valueOf(RS.getInt("manager"))) : null,
                dep
        );
    }

    private Employee getManChain(BigInteger id) throws SQLException {
        String query = "select e.*, d.name dname, d.location dlocation from employee e left join department d on e.department=d.id where e.id=" + String.valueOf(id);
        ResultSet RS = ConnectionSource.instance().createConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query);
        if (RS.next()) return getChain(RS);
        return null;
    }
}
