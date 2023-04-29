package uta.fisei.app_android_002;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private Button buttonFinish;

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

        //return super.onCreateOptionsMenu(menu);
        return true;
    }
}