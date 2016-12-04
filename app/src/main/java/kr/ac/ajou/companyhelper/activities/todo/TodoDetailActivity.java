package kr.ac.ajou.companyhelper.activities.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.activities.ApplicationActivity;

public class TodoDetailActivity extends ApplicationActivity {

  public void initialize() {
    TextView title = (TextView) findViewById(R.id.todo_title);
    title.setText(String.format("title: %s", this.getIntent().getStringExtra("title")));

    TextView content = (TextView) findViewById(R.id.todo_content);
    content.setText(String.format("content: %s", this.getIntent().getStringExtra("content")));

    TextView priority = (TextView) findViewById(R.id.todo_priority);
    priority.setText(String.format("priority: %s", this.getIntent().getStringExtra("priority")));


    TextView state = (TextView) findViewById(R.id.todo_state);
    state.setText(String.format("state: %s", this.getIntent().getStringExtra("state")));

    TextView projectName = (TextView) findViewById(R.id.todo_project_name);
    projectName.setText(String.format("project name: %s", this.getIntent().getStringExtra("project_name")));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todo_detail);
    initialize();
  }
}
