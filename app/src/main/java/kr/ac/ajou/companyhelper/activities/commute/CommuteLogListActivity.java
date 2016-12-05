package kr.ac.ajou.companyhelper.activities.commute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import kr.ac.ajou.companyhelper.R;
import kr.ac.ajou.companyhelper.activities.ApplicationActivity;
import kr.ac.ajou.companyhelper.models.CommuteLog;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommuteLogListActivity extends ApplicationActivity {
  private ListView mListView;
  private List<CommuteLog> commuteLogs;
  private ArrayAdapter<String> adapter;

  public void initialize() {
    mListView = (ListView) findViewById(R.id.commute_log_list_view);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_commute_log_list);

    initialize();
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy년 MM월 dd일 k시 m분");
    commuteLogs = currentEmployee().getCommuteLogs();
    ArrayList<String> listItems = new ArrayList<String>();
    for(int i = 0; i < commuteLogs.size(); i++){
      listItems.add(commuteLogs.get(i).getCommutedAt().toString(fmt.withLocale(Locale.KOREA)));
    }

    adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
    mListView.setAdapter(adapter);
  }
}
