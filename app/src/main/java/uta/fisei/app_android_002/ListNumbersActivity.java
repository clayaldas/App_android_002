package uta.fisei.app_android_002;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListNumbersActivity extends AppCompatActivity {

    private ListView listViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_numbers);

        listViewData = findViewById(R.id.listViewData);

        // cargar datos en el listView
        ArrayAdapter<String> adapterNumbers =
                new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, loadData());

        listViewData.setAdapter(adapterNumbers);

        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) listViewData.getAdapter().getItem(i);

               // Toast.makeText(ListNumbersActivity.this,
                 //       "Selecciono: " + selectedItem,
                   //     Toast.LENGTH_SHORT).show();

                // regresar el (selectedItem) hacia el activity padre
                Intent intent = new Intent();
                intent.setData(Uri.parse(selectedItem));

                // indicar al compilador que el activity se cierra de forma normal
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }

    private List<String>  loadData() {
        List<String> list = new ArrayList<String>();

        for (int i=1; i <= 10; i++) {
            String item = "Numero: " + i;

            list.add(item);
        }
        return list;
    }
}