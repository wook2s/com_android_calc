package com.example.asb_pr_5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;

    Button btnClear;

    Button[] btnNum = new Button[10];
    Integer[] btnNums = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                        R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

    Button[] btnOp = new Button[4];
    Integer[] btnOps = {R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide};

    Button btnDel;

    TextView tv1;

    String numTmp = "";
    int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("계산기");

        btnClear = (Button) findViewById(R.id.btnClear);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        for(int i=0; i<btnNum.length; i++){
            btnNum[i] = (Button) findViewById(btnNums[i]);
        }
        for(int i=0; i<btnOp.length; i++){
            btnOp[i] = (Button) findViewById(btnOps[i]);
        }

        btnDel = (Button) findViewById(R.id.btnDel);

        tv1 = (TextView) findViewById(R.id.tv1);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.setText("");
                et1.clearFocus();
                et2.setText("");
                et2.clearFocus();
                numTmp = "";
            }
        });

        for(int i=0; i<btnNum.length; i++){
            final int idx = i;
            btnNum[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numTmp += btnNum[idx].getText().toString();

                    if(numTmp.equals("0")){
                        numTmp = "0";
                    }else if(numTmp.startsWith("0") && numTmp.length()>=2){
                        numTmp = numTmp.substring(1, numTmp.length());
                    }
                    
                    switch (flag){
                        case 1: et1.setText(numTmp);
                            break;
                        case 2 : et2.setText(numTmp);
                            break;
                    }

                }
            });
        }

        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                et1.setShowSoftInputOnFocus(false);
                numTmp = "";
                flag = 1;
            }
        });

        et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                et2.setShowSoftInputOnFocus(false);
                numTmp = "";
                flag = 2;
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tmp = "";

                switch (flag){
                    case 1: tmp = et1.getText().toString();
                        break;
                    case 2 : tmp = et2.getText().toString();
                        break;
                }

                if(tmp.length() == 0){
                    return;
                }else if(tmp.length() == 1 && tmp.equals("0")){
                    return;
                }else if(tmp.length() == 1){
                    tmp = "0";
                }else{
                    tmp = tmp.substring(0, tmp.length()-1);
                }
                numTmp = tmp;
                switch (flag){
                    case 1: et1.setText(numTmp);
                        break;
                    case 2 : et2.setText(numTmp);
                        break;
                }

            }
        });

        for(int i=0; i<btnOp.length; i++){
            final int idx = i;
            btnOp[idx].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Float num1 = Float.parseFloat(et1.getText().toString());
                        Float num2 = Float.parseFloat(et2.getText().toString());
                        Float num3 = (float) 0;

                        switch (idx) {
                            case 0:
                                num3 = num1 + num2;
                                break;
                            case 1:
                                num3 = num1 - num2;
                                break;
                            case 2:
                                num3 = num1 * num2;
                                break;
                            case 3:
                                num3 = num1 / num2;
                                break;
                        }
                        et1.clearFocus();
                        et2.clearFocus();
                        tv1.setText(String.valueOf(num3));
                    }catch (Exception e){
                        tv1.setText("입력값을 확인해주세요");
                    }
                }
            });
        }
    }
}