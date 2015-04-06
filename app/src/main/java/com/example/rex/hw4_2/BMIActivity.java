package com.example.rex.hw4_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by REX on 2015/4/5.
 */
public class BMIActivity extends ActionBarActivity {
    private EditText editTxtH, editTxtW;
    private Button btnOK, btnBack, btnClean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        findviews();
    }

    protected void findviews() {
        editTxtH = (EditText) findViewById(R.id.editTxtH);
        editTxtW = (EditText) findViewById(R.id.editTxtW);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnClean = (Button) findViewById(R.id.btnClean);
        btnOK = (Button) findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                try {
                    double height = Double.parseDouble(editTxtH.getText().toString());
                    double weight = Double.parseDouble(editTxtW.getText().toString());
                    //比對輸入的值是否為空值
                    boolean h = editTxtH.equals("");
                    boolean w = editTxtW.equals("");
                    //若身高或體重有一個為空值則拋出例外
                    if (h || w) {
                        throw new Exception();
                    }
                    double bmi = weight / ((height * height) / 10000);
                    //將bmi的值取到小數點兩位
                    DecimalFormat df=new DecimalFormat("##.00");
                    bmi=Double.parseDouble(df.format(bmi));
                    bundle.putDouble("bmi", bmi);
                    if (bmi < 18.5) {
                        bundle.putString("bmiAns", getString(R.string.bmiThin));
                    } else if (bmi > 24) {
                        bundle.putString("bmiAns", getString(R.string.bmiFat));
                    } else {
                        bundle.putString("bmiAns", getString(R.string.bmiOK));
                    }


                } catch (Exception e) {
                    Toast.makeText(BMIActivity.this, "請輸入身高體重", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                BMIActivity.this.finish();

            }
        });

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTxtH.setText("");
                editTxtW.setText("");
                return;
            }
        });
    }
}
