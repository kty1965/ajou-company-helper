package kr.ac.ajou.companyhelper.activities.todo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.activities.ApplicationActivity;
import kr.ac.ajou.companyhelper.models.Project;
import kr.ac.ajou.companyhelper.models.ToDo;
import kr.ac.ajou.companyhelper.models.daos.ProjectDao;
import kr.ac.ajou.companyhelper.models.daos.ToDoDao;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends ApplicationActivity {

  private ListView mListView;
  private List<ToDo> toDos;
  private ArrayAdapter<String> adapter;

  public void initialize() {
    mListView = (ListView) findViewById(R.id.todo_list_view);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todo_list);

    initialize();

    toDos = currentEmployee().getToDos();
    ArrayList<String> listItems = new ArrayList<String>();
    for(int i = 0; i < toDos.size(); i++){
      listItems.add(toDos.get(i).getTitle());
    }

    adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
    mListView.setAdapter(adapter);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startToDoDetailActivity(toDos.get(position));
      }
    });

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_todo);
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            startToDoAddActivity();
          }
      });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case TODO_ADD:
        if (data != null) {
          Project project = ProjectDao.findByName(data.getStringExtra("projectName"));
          String title = data.getStringExtra("title");
          String priority = data.getStringExtra("priority");
          String content = data.getStringExtra("content");

          ToDo todo = ToDoDao.create(title, content, priority, project, currentEmployee());
          toDos.add(todo);
          addToDoItem(todo);
        }
        break;
    }
  }

  public void addToDoItem(ToDo toDo) {
    adapter.add(toDo.getTitle());
  }
}
