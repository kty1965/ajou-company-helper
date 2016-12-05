package kr.ac.ajou.companyhelper.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import org.joda.time.DateTime;

/**
 * Created by huy on 2016. 12. 2..
 */
@Table(name = "CommuteLog")
public class CommuteLog extends Model {

  @Column(name = "commutedAt")
  private DateTime commutedAt;

  @Column(name = "employee", onDelete = Column.ForeignKeyAction.CASCADE)
  private Employee employee;

  public CommuteLog(DateTime commutedAt, Employee employee) {
    this.commutedAt = commutedAt;
    this.employee = employee;
  }

  public CommuteLog() {

  }

  public DateTime getCommutedAt() {
    return commutedAt;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setCommutedAt(DateTime commutedAt) {
    this.commutedAt = commutedAt;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
}
