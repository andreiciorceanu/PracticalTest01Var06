package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {
    private TextView afisaj;
    private Button ok;
    private StringBuilder sb = new StringBuilder();
    private int scor = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        afisaj = findViewById(R.id.gained);
        ok = findViewById(R.id.ok);

        Bundle extras = getIntent().getExtras();

        // Get the terms as a string
        String number1 = extras.getString("number1");
        String number2 = extras.getString("number2");
        String number3 = extras.getString("number3");
        int nrBoxes = extras.getInt("boxes", 0);

        if(number1.equals("*")) {
            if(number2.equals(number3) || number2.equals("*") || number3.equals("*")) {
                sb.append("Gained");
                scor = calculeazaCastig(nrBoxes);
                sb.append(" ");
                sb.append(scor);
                afisaj.setText(sb.toString());
            }
        } else {
            if(number2.equals("*")) {
                if(number1.equals(number3) || number3.equals("*")) {
                    sb.append("Gained");
                    scor = calculeazaCastig(nrBoxes);
                    sb.append(" ");
                    sb.append(scor);
                    afisaj.setText(sb.toString());
                }
            } else {
                if(number3.equals("*")) {
                    if(number1.equals(number2)) {
                        sb.append("Gained");
                        scor = calculeazaCastig(nrBoxes);
                        sb.append(" ");
                        sb.append(scor);
                        afisaj.setText(sb.toString());
                    }
                } else {
                    if(number1.equals(number2) && number2.equals(number3)) {
                        sb.append("Gained");
                        scor = calculeazaCastig(nrBoxes);
                        sb.append(" ");
                        sb.append(scor);
                        afisaj.setText(sb.toString());
                    }
                }
            }
        }


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                Intent intent = new Intent();
                intent.putExtra("scor",scor);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private int calculeazaCastig(int nrBife) {
        switch (nrBife) {
            case 0:
                return 100;
            case 1:
                return 50;
            case 2:
                return 10;
            default:
                return 0;
        }
    }

}