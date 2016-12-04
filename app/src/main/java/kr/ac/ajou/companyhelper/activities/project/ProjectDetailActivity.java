package kr.ac.ajou.companyhelper.activities.project;

import android.os.Bundle;
import android.widget.TextView;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.activities.ApplicationActivity;

public class ProjectDetailActivity extends ApplicationActivity {

  public void initialize() {
    TextView name = (TextView) findViewById(R.id.project_detail_name);
    name.setText(this.getIntent().getStringExtra("name"));
    TextView description = (TextView) findViewById(R.id.project_detail_description);
    description.setText(this.getIntent().getStringExtra("description"));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_project_detail);

    initialize();
  }
}
