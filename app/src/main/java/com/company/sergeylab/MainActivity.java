package com.company.sergeylab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button schet;
    TextView answer;
    EditText a,b,c;
    Double numA,numB,numC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //инициализация объектов UI
        schet = (Button) findViewById(R.id.buttonSch);
        answer = (TextView) findViewById(R.id.answertext);
        a = (EditText) findViewById(R.id.edit_kof_a);
        b = (EditText) findViewById(R.id.edit_kof_b);
        c = (EditText) findViewById(R.id.edit_kof_c);
        schet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //если пустые строки заполняем нулями
                if(a.getText().toString().length() == 0) {
                    numA = 0.0;
                } else {
                    numA = Double.parseDouble(a.getText().toString());
                }
                if(b.getText().toString().length() == 0) {
                    numB = 0.0;
                } else {
                    numB = Double.parseDouble(b.getText().toString());
                }
                if(c.getText().toString().length() == 0) {
                    numC = 0.0;
                } else {
                    numC = Double.parseDouble(c.getText().toString());
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Решение квадратных уравнений")


                        .setNegativeButton("Ок",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                //рассчет дискриминанта
                double D = numB*numB - 4*numA*numC;
                if (D < 0) {
                    answer.setText("Решение уравнения нет!");
                    builder.setMessage("Решение уравнения нет!");

                } else if (D == 0.0){
                    double x1 = -numB/(2*numA);
                    if (Long.parseLong((x1+"").split("\\.")[1]) == 0) {

                        answer.setText("x = " + (int) x1);
                        builder.setMessage("x = " + (int)x1);
                    } else {

                        answer.setText("x = " + String.format("%.2f",x1));
                        builder.setMessage("x = " + String.format("%.2f",x1));
                    }
                } else {
                    double x1 = (-numB+Math.sqrt(D))/(2*numA);
                    double x2 = (-numB-Math.sqrt(D))/(2*numA);
                    String str = "";

                    if (Long.parseLong((x1+"").split("\\.")[1]) == 0) {
                        str ="x1 = " + (int)x1;
                    } else {
                        str = "x1 = " + String.format("%.2f",x1);
                    }
                    if (Long.parseLong((x2+"").split("\\.")[1]) == 0) {
                        str += "\nx2 = " + (int)x2;
                    } else {
                        str += "\nx2 = " + String.format("%.2f",x2);
                    }
                    answer.setText(str);
                    builder.setMessage(str);
                }
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
}
