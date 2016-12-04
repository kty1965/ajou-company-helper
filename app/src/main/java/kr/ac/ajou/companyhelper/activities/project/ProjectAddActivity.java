package kr.ac.ajou.companyhelper.activities.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.activities.ApplicationActivity;

public class ProjectAddActivity extends ApplicationActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_project_add);
  }

  public void addProject(View button) {
    final EditText nameField = (EditText) findViewById(R.id.project_add_name);
    String name = nameField.getText().toString();

    final EditText descriptionField = (EditText) findViewById(R.id.project_add_description);
    String description = descriptionField.getText().toString();

    System.out.println(name);
    System.out.println(description);

    Intent data = new Intent();
    data.putExtra("name", name);
    data.putExtra("description", description);
    setResult(PROJECT_ADD, data);
    finish();
  }
}
