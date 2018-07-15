package com.suli.suliapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.suli.suliapp.R;
import com.suli.suliapp.data.models.AccessTokenResponse;
import com.suli.suliapp.ui.callbacks.LoginCallback;
import com.suli.suliapp.ui.utils.Utils;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        utils = new Utils(this);

        if (utils.isLogged()) {
            startActivity(new Intent(this, ProjectsActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

    }

    public void Login() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando Sesión");
        progressDialog.show();
        etUsername.setError(null);
        etPassword.setError(null);
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        Integer errors = 0;
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Ingrese un usario válido");
            errors++;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Ingrese una contraseña válida");
            errors++;
        }
        if (errors > 0) {
            Toast.makeText(this, "Verifica los datos ingresados", Toast.LENGTH_SHORT).show();
        } else {
            utils.postAuth(username, password, new LoginCallback() {
                @Override
                public void onSuccess(AccessTokenResponse accessToken) {
                    progressDialog.dismiss();
                    startActivity(new Intent(LoginActivity.this, ProjectsActivity.class));
                    finish();
                }

                @Override
                public void onFailure(String error) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}