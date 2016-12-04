package kr.ac.ajou.companyhelper.activities.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.activities.ApplicationActivity;
import kr.ac.ajou.companyhelper.models.Project;

import java.util.ArrayList;
import java.util.List;

public class TodoAddActivity extends ApplicationActivity {

  private Spinner projectSpinner;
  private Spinner prioritySpinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todo_add);

    projectSpinner = (Spinner) findViewById(R.id.todo_project);
    prioritySpinner = (Spinner) findViewById(R.id.todo_priority);
    addItemsOnProjectSpinner();
  }

  public void addItemsOnProjectSpinner() {
    List<String> list = new ArrayList<String>();
    for (Project project : currentEmployee().getProjects()) {
      list.add(project.getName());
    }

    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, list);
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    projectSpinner.setAdapter(dataAdapter);
  }

  public void addTodo(View button) {
    final EditText titleField = (EditText) findViewById(R.id.todo_title);
    String title = titleField.getText().toString();

    final EditText contentField = (EditText) findViewById(R.id.todo_content);
    String content = contentField.getText().toString();
    String priority = String.valueOf(prioritySpinner.getSelectedItem());
    String projectName = String.valueOf(projectSpinner.getSelectedItem());


    Intent data = new Intent();
    data.putExtra("title", title);
    data.putExtra("content", content);
    data.putExtra("priority", priority);
    data.putExtra("projectName", projectName);
    setResult(TODO_ADD, data);
    finish();
  }
}
