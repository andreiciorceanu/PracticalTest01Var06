package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {
    private String[] options = {"1","2","3","*"};
    private Button play;
    private EditText text1, text2, text3;
    private CheckBox check1,check2,check3;
    String generatedNumber1, generatedNumber2, generatedNumber3;
    String a,b,c;
    private int nrCheckedBoxes = 3;
    private int scor = 0;
    IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("onReceive", intent.getStringExtra("message"));
            String mesaj =  "Victory " + intent.getStringExtra("message");
            //System.out.println("`````````````mesajul este:" + mesaj);
            Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        play = findViewById(R.id.btn_invoke_activity);
        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText3);
        check1 = findViewById(R.id.checkBox1);
        check2 = findViewById(R.id.checkBox2);
        check3 = findViewById(R.id.checkBox3);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nrCheckedBoxes = 3;
                generatedNumber1 = generateNumber();
                generatedNumber2 = generateNumber();
                generatedNumber3 = generateNumber();
                if(!check1.isChecked()) {
                    text1.setText(generatedNumber1);
                    nrCheckedBoxes--;
                    a = generatedNumber1;
                }
                if(!check2.isChecked()) {
                    text2.setText(generatedNumber2);
                    nrCheckedBoxes--;
                    b = generatedNumber2;
                }
                if(!check3.isChecked()) {
                    text3.setText(generatedNumber3);
                    nrCheckedBoxes--;
                    c = generatedNumber3;
                }

                String message = "Generated numbers: " + generatedNumber1 + ", " + generatedNumber2 + ", " + generatedNumber3;
                Toast.makeText(PracticalTest01Var06MainActivity.this, message, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PracticalTest01Var06MainActivity.this, PracticalTest01Var06SecondaryActivity.class);
                intent.putExtra("number1", a);
                intent.putExtra("number2",b);
                intent.putExtra("number3", c);
                intent.putExtra("boxes", nrCheckedBoxes);
                startActivityForResult(intent, 1);
            }
        });

        if(savedInstanceState != null) {
            if(savedInstanceState.containsKey("scor")) {
                scor = savedInstanceState.getInt("scor");
            } else {
                scor = 0;
            }
        } else {
            scor = 0;
        }
        //Toast.makeText(this, "The result of the operation is " + scor, Toast.LENGTH_SHORT).show();
        intentFilter.addAction("calculareScor");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            int scorPrimit = data.getIntExtra("scor", 0);
            scor += scorPrimit;
            Toast.makeText(this, "The result of the operation is " + scor, Toast.LENGTH_SHORT).show();
            if (scor > 0) {
                Intent intent1 = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
                intent1.putExtra("scorTotal", scor);
                //System.out.println("scor----------");
                getApplicationContext().startService(intent1);
            }
        }
    }

    private String generateNumber() {
        Random random = new Random();
        int number = random.nextInt(4);
        return options[number];
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scor", scor);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.containsKey("scor")) {
            scor = savedInstanceState.getInt("scor");
        } else  {
            scor = 0;
        }
        Toast.makeText(this, "The result of the operation is " + scor, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
        getApplicationContext().stopService(intent2);
        Toast.makeText(getApplicationContext(), "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        Log.d("onResume", "onResume() method was invoked");
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        Log.d("onPause", "onPause() method was invoked");
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}