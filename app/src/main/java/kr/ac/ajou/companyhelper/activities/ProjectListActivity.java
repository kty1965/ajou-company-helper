package kr.ac.ajou.companyhelper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.models.Project;
import kr.ac.ajou.companyhelper.models.daos.ProjectDao;

import java.util.List;

public class ProjectListActivity extends ApplicationActivity {
  private ListView mListView;

  public void initialize() {
    mListView = (ListView) findViewById(R.id.project_list_view);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_project_list);

    initialize();

    List<Project> projects = currentEmployee().getProjects();
    String[] listItems = new String[projects.size()];
    for(int i = 0; i < projects.size(); i++){
      listItems[i] = projects.get(i).getName();
    }

    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
    mListView.setAdapter(adapter);
  }
}
