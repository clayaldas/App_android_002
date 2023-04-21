package uta.fisei.app_android_002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTitle = findViewById(R.id.textViewTitle);

        // recibir / obtener los parametros del activity (login)
        Bundle bundle  = this.getIntent().getExtras();

        String userName = bundle.getString("keyUsername");
        String password = bundle.getString("keyPassword");
        textViewTitle.setText("Usuario: " + userName +
                "   " + "Clave:  " + password);
    }
}