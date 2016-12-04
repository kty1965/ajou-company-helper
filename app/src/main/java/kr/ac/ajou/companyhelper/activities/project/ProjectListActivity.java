package kr.ac.ajou.companyhelper.activities.project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.activities.ApplicationActivity;
import kr.ac.ajou.companyhelper.models.Project;
import kr.ac.ajou.companyhelper.models.daos.ProjectDao;

import java.util.ArrayList;
import java.util.List;

public class ProjectListActivity extends ApplicationActivity {
  private ListView mListView;
  private List<Project> projects;
  private ArrayAdapter<String> adapter;

  public void initialize() {
    mListView = (ListView) findViewById(R.id.project_list_view);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_project_list);

    initialize();

    projects = currentEmployee().getProjects();
    ArrayList<String> listItems = new ArrayList<String>();
    for(int i = 0; i < projects.size(); i++){
      listItems.add(projects.get(i).getName());
    }

    adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
    mListView.setAdapter(adapter);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startProjectDetailActivity(projects.get(position));
      }
    });

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_project);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startProjectAddActivity();
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case PROJECT_ADD:
        if (data != null) {
          Project project = ProjectDao.createWithWorksOn(
              data.getStringExtra("name"),
              data.getStringExtra("description"),
              currentEmployee());
          projects.add(project);
          addProjectItem(project);
        }
        break;
    }
  }

  public void addProjectItem(Project project) {
    adapter.add(project.getName());
  }
}
