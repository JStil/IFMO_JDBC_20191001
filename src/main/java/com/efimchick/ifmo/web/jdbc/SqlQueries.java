package com.efimchick.ifmo.web.jdbc;

/**
 * Implement sql queries like described
 */
public class SqlQueries {
    //Select all employees sorted by last name in ascending order
    //language=HSQLDB
    String select01 = "select * from employee order by lastName ASC";

    //Select employees having no more than 5 characters in last name sorted by last name in ascending order
    //language=HSQLDB
    String select02 = "select * from employee where length(lastName)<6 order by lastName ASC";

    //Select employees having salary no less than 2000 and no more than 3000
    //language=HSQLDB
    String select03 = "select * from employee where salary between 2000 and 3000";

    //Select employees having salary no more than 2000 or no less than 3000
    //language=HSQLDB
    String select04 = "select * from employee where salary not between 2001 and 2999";

    //Select employees assigned to a department and corresponding department name
    //language=HSQLDB
    String select05 = "select * from employee, department where employee.department = department.id";

    //Select all employees and corresponding department name if there is one.
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select06 = "select salary, d.name as depname, lastName from employee left join department d on employee.department = d.id";

    //Select total salary pf all employees. Name it "total".
    //language=HSQLDB
    String select07 = "select sum(employee.salary) as total from employee";

    //Select all departments and amount of employees assigned per department
    //Name column containing name of the department "depname".
    //Name column containing employee amount "staff_size".
    //language=HSQLDB
    String select08 = "select d.name as depname, count(d.name) as staff_size from employee e join department d on e.department=d.id group by depname";

    //Select all departments and values of total and average salary per department
    //Name column containing name of the department "depname".
    //language=HSQLDB
    String select09 = "select d.name as depname, sum(e.salary) total, avg(e.salary) average from employee e join department d on e.department=d.id group by depname";

    //Select all employees and their managers if there is one.
    //Name column containing employee lastname "employee".
    //Name column containing manager lastname "manager".
    //language=HSQLDB
    String select10 = "select e.lastname employee, m.lastname manager from employee e left join employee m on e.manager=m.id";


}
