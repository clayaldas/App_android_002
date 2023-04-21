package uta.fisei.app_android_002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonOk = findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                if ( !userName.matches("") &&  !password.matches("") ) {
                    // mostrar el activity principal del proyecto
                    Intent intent = new Intent(getApplicationContext(),
                            MainActivity.class);

                    // agregar parametros al Intent (activity)
                    intent.putExtra("keyUsername", userName);
                    intent.putExtra("keyPassword", password);

                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Usuario/Claves son requeridos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClic_ButtonOk(View view) {
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();

        if ( !userName.matches("") &&  !password.matches("") ) {
            // mostrar el activity principal del proyecto
            Intent intent = new Intent(this,
                    MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,
                    "Usuario/Claves son requeridos",
                    Toast.LENGTH_SHORT).show();
        }
    }
}