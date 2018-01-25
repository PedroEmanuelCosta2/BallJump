package he_arc.balljump;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pedrocosta on 19.01.18.
 */

public class Statistics extends AppCompatActivity {

    private List<String> statList;
    private List<String> statHeader;
    private HashMap<String, List<String>> statChildren;
    private ExpandableListAdapter statAdapter;
    private ExpandableListView expandableListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        expandableListView = findViewById(R.id.expandableListView);
        statList = new ArrayList<>();
        statHeader = new ArrayList<>();
        statChildren = new HashMap<>();
        readFile();
        statAdapter = new ExpandableListAdapter(this,statHeader,statChildren);
        expandableListView.setAdapter(statAdapter);
    }

    private void readFile() {
        try {
            FileInputStream fis = openFileInput("score.txt");
            InputStreamReader ois = new InputStreamReader(fis);
            BufferedReader bf = new BufferedReader(ois);

            String receiveString = "";

            while ( (receiveString = bf.readLine()) != null ) {
                statList.add(receiveString);
            }

            bf.close();
            ois.close();
            fis.close();

            updateListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateListView() {

        statHeader.add("Average score");
        statHeader.add("Best score");
        statHeader.add("Last 5 scores");

        int average = 0;
        int best = -1;
        int total = 0;
        int cmpt = 0;
        int integerValueOfString = 0;

        List<String> averageList = new ArrayList<>();
        List<String> bestScoreList = new ArrayList<>();
        List<String> last5BestScoresList = new ArrayList<>();

        for (String score : statList) {

            integerValueOfString = Integer.parseInt(score);

            if (integerValueOfString > best){
                best = integerValueOfString;
            }

            if (cmpt < 5){
                last5BestScoresList.add(score + " pt(s)");
            }

            total += integerValueOfString;
            cmpt++;
        }

        average = total / statList.size();
        averageList.add(average+" pt(s)");

        bestScoreList.add(best+" pt(s)");

        statChildren.put(statHeader.get(0),averageList);
        statChildren.put(statHeader.get(1),bestScoreList);
        statChildren.put(statHeader.get(2),last5BestScoresList);

        statAdapter.notifyDataSetChanged();
    }
}
