package kr.ac.ajou.companyhelper.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by huy on 2016. 11. 8..
 */
@Table(name = "Project")
public class Project extends Model {
  @Column(name = "name", index = true, unique = true) private String name;
  @Column(name = "description") private String description;

  public Project() {
  }

  public Project(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
