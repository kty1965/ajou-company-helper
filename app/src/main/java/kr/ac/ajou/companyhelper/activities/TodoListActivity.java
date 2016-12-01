package kr.ac.ajou.companyhelper.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.models.Project;
import kr.ac.ajou.companyhelper.models.ToDo;
import kr.ac.ajou.companyhelper.models.daos.ProjectDao;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends ApplicationActivity {

  private ListView mListView;

  public void initialize() {
    mListView = (ListView) findViewById(R.id.todo_list_view);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todo_list);

    initialize();

    List<ToDo> toDos = currentEmployee().getToDos();
    String[] listItems = new String[toDos.size()];
    for(int i = 0; i < toDos.size(); i++){
      listItems[i] = toDos.get(i).getTitle();
    }

    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
    mListView.setAdapter(adapter);
  }
}
