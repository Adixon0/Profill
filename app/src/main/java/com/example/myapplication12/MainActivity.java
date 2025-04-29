package com.example.myapplication12;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.res.ColorStateList;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tekstEmail;
    TextView tekstHasla;
    TextView komunikat;
    Button przyciskEdytujProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tekstEmail = findViewById(R.id.tekst_email);
        tekstHasla = findViewById(R.id.tekst_hasla);
        komunikat = findViewById(R.id.komunikat);
        przyciskEdytujProfil = findViewById(R.id.przycisk_edytuj_profil);

        komunikat.setText("Witaj! Aplikacja wykonana przez: Adrian Iżykowski");

        przyciskEdytujProfil.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00BCD4")));
        przyciskEdytujProfil.setTextColor(Color.WHITE);

        przyciskEdytujProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokazOknoZmianyEmail();
            }
        });
    }




    private void pokazOknoZmianyHasla(String email) {
        View layoutHasla = getLayoutInflater().inflate(R.layout.password_dialog_layout, null);
        EditText poleHaslo1 = layoutHasla.findViewById(R.id.pole_haslo_1);
        EditText poleHaslo2 = layoutHasla.findViewById(R.id.pole_haslo_2);

        AlertDialog dialogHaslo = new AlertDialog.Builder(this)
                .setTitle("Zmień Hasło")
                .setView(layoutHasla)
                .setPositiveButton("Zapisz", (d, which) -> {
                    String p1 = poleHaslo1.getText().toString().trim();
                    String p2 = poleHaslo2.getText().toString().trim();
                    if (!p1.equals(p2)) {
                        komunikat.setTextColor(Color.RED);
                        komunikat.setText("Błąd: Hasła nie pasują do siebie.");
                    } else {
                        tekstHasla.setText(p1);
                        komunikat.setTextColor(Color.GREEN);
                        komunikat.setText("Profil zaktualizowany! Nowy email: " + email);
                    }
                })
                .setNegativeButton("Anuluj", (d, which) -> {
                    komunikat.setTextColor(Color.GRAY);
                    komunikat.setText("Edycja profilu anulowana.");
                })
                .show();

        dialogHaslo.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(Color.parseColor("#00BCD4"));
    }

    private void pokazOknoZmianyEmail() {
        final EditText poleEmail = new EditText(this);
        poleEmail.setHint("Nowy email");
        poleEmail.setText(tekstEmail.getText());

        AlertDialog dialogEmail = new AlertDialog.Builder(this)
                .setTitle("Zmień Email")
                .setMessage("Podaj nowy email:")
                .setView(poleEmail)
                .setPositiveButton("Zapisz", (d, which) -> {
                    String nowyEmail = poleEmail.getText().toString().trim();
                    if (!nowyEmail.contains("@")) {
                        komunikat.setTextColor(Color.RED);
                        komunikat.setText("Błąd: Nieprawidłowy format emaila.");
                    } else {
                        tekstEmail.setText(nowyEmail);
                        pokazOknoZmianyHasla(nowyEmail);
                    }
                })
                .setNegativeButton("Anuluj", (d, which) -> {
                    komunikat.setTextColor(Color.GRAY);
                    komunikat.setText("Edycja profilu anulowana.");
                })
                .show();

        dialogEmail.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(Color.parseColor("#00BCD4"));
    }
}
