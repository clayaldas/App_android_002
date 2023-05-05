package uta.fisei.app_android_002;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private Button buttonFinish;
    private LinearLayout linearLayoutMain;

   // private int requiredCode = 1;

    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    // comprobar si la ventana hija (ListNumbersActivity)
                    // se cerro correctamente
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();

                        textViewTitle.setText(intent.getDataString());
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitle = findViewById(R.id.textViewTitle);
        buttonFinish = findViewById(R.id.buttonFinish);

        linearLayoutMain = findViewById(R.id.linerLayoutMain);

        // recibir / obtener los parametros del activity (login)
        Bundle bundle  = this.getIntent().getExtras();

        String userName = bundle.getString("keyUsername");
        String password = bundle.getString("keyPassword");
        textViewTitle.setText("Usuario: " + userName +
                "   " + "Clave:  " + password);

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

       // registerForContextMenu(linearLayoutMain);

        registerForContextMenu(textViewTitle);

    }

    public void onClick_buttonShowBrowser(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.google.com"));

        startActivity(intent);
    }

    public void onClick_buttonCallPhone (View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel: +5934743749"));
        startActivity(intent);
    }

    public void onClick_buttonShowListNumber (View view) {
        Intent intent = new Intent(this,
                ListNumbersActivity.class);

        //startActivity(intent);
       // startActivityForResult(intent,requiredCode);
        activityResultLauncher.launch(intent);
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

        if ( (requestCode == requiredCode) && (resultCode == RESULT_OK)) {
            textViewTitle.setText("Selecciono: " + data.getDataString());
        }
        else {
            Toast.makeText(this,
                    "Error al recuperar los datos",
                    Toast.LENGTH_SHORT).show();
        }
    }
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_file) {
            Toast.makeText(this,
                    "Presiono en: Archivo", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.menu_about) {
            Toast.makeText(this,
                    "Presiono en: Acerca de...", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.menu_edit_cut) {
            Toast.makeText(this,
                    "Presiono en: Cortar", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.menu_edit_copy) {
            Toast.makeText(this,
                    "Presiono en: Copiar", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.menu_edit_paste) {
            Toast.makeText(this,
                    "Presiono en: Pegar", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.menu_options_finish) {
            finish();
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_main_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_contextual_cut) {
            Toast.makeText(this,
                    "Presiono en menu contextual: cortar",
                    Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.menu_contextual_copy) {
            Toast.makeText(this,
                    "Presiono en menu contextual: copiar",
                    Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.menu_contextual_paste) {
            Toast.makeText(this,
                    "Presiono en menu contextual: pegar",
                    Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }
}