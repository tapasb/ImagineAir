package tapas.example.com.imagineair;
/**
 * Created by Tapas Behera on 22/07/2014.
 */
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.support.wearable.activity.InsetActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class MyActivity  extends InsetActivity{
    private TextView mTextView;
    private static String TAG="WearActivity";
    private static final int SPEECH_REQUEST_CODE = 0;
    private View myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onReadyForContent() {
//        setContentView(R.layout.activity_my_wear);
//        setContentView(R.layout.rect_activity_my_wear);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        myView  = inflater.inflate(R.layout.rect_activity_my, null);
        setContentView(myView);

        final String[] values = new String[] {
                "ARRIVAL",
                "DEPARTURE"
        };
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final ListView listView = (ListView) myView.findViewById(R.id.actionsListView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        // Assign adapter to ListView
        listView.setAdapter(arrayAdapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) listView.getItemAtPosition(position);
                // Show Alert
                if (position == 0) {
                    Intent arrivalIntent = new Intent(MyActivity.this, ArrivalFlightsIntent.class);
                    MyActivity.this.startActivity(arrivalIntent);
                } else if (position == 1) {
                    Intent departureIntent = new Intent(MyActivity.this, DepartureFlightsIntent.class);
                    MyActivity.this.startActivity(departureIntent);
                }
            }
        });
    }
}