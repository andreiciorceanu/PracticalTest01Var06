package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {
    private String[] options = {"1","2","3","*"};
    private Button play;
    private EditText text1, text2, text3;
    private CheckBox check1,check2,check3;
    String number1, number2, number3;
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText3);
        play = findViewById(R.id.btn_invoke_activity);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private String generateNumber() {
        Random random = new Random();
        int number = random.nextInt(4);
        return options[number];
    }
}