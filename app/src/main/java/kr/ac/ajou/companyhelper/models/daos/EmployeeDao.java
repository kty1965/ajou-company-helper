package kr.ac.ajou.companyhelper.models.daos;

import com.activeandroid.query.Select;
import kr.ac.ajou.companyhelper.models.Employee;
import kr.ac.ajou.companyhelper.models.Project;

/**
 * Created by huy on 2016. 11. 8..
 */
public class EmployeeDao  extends SqlDao<Employee>  {
  public EmployeeDao() {
    super(Employee.class);
  }

  public static Employee findOrCreate() {
    return findOrCreate("employee@example.com");
  }

  public static Employee findOrCreate(String email) {
    Employee employee = new Select().from(Employee.class).where("email = ?", email).executeSingle();
    if (employee == null) {
      employee = new Employee(email, "TaeYoung", "0000");
      employee.save();
    }
    return employee;
  }
}
