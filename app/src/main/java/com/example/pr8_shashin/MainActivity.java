package com.example.pr8_shashin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Метод вывода предупреждения о незаполненных полях
    public void AlertDialogs(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alter = builder.create();
        alter.show();
    }
    // Метод рассчета
    public void Consider(View view) {
        EditText course = findViewById(R.id.cours);
        EditText count = findViewById(R.id.count);
        Switch dollar = findViewById(R.id.Switch);
        TextView tv = findViewById(R.id.MainTextView);

        if(course.getText().length() > 0) {
            if(count.getText().length() > 0) {
                float f_course = Float.parseFloat(String.valueOf(course.getText()));
                float f_count = Float.parseFloat(String.valueOf(count.getText()));

                float composition = 0;
                if(dollar.isChecked() == true) {
                    composition = f_course * f_count;
                    tv.setText((composition + " р."));
                } else {
                    composition = f_count / f_course;
                    tv.setText(composition + " $.");
                }
            } else {
                AlertDialogs("Уведомление", "Введите кол-во доллара.");
            }
        } else {
            AlertDialogs("Уведомление", "Введите курс доллара.");
        }
    }
    // Метод открытия ссылки
    public void URL(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sberbank.ru/ru/quotes/currencies"));
        startActivity(intent);
    }
}