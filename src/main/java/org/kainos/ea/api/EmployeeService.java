package org.kainos.ea.api;

import org.kainos.ea.cli.*;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    public List<IPayable> getEmployees(){
        Employee e1 = new Employee(1,"Ieuan", 30000);
        SalesEmployee se1 = new SalesEmployee(1, "Ieuan", 30000, 1000, 0.2f);
        DeliveryEmployee de1 = new DeliveryEmployee(1, "Ieuan", 30000);
        Contractor c1 = new Contractor("Ieuan", 300, 22);

        List<IPayable> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(se1);
        employees.add(de1);
        employees.add(c1);

        for (IPayable e : employees) {
            System.out.println(e.calcPay());
        }

        return employees;
    }
}
