package com.example.rex.hw4_2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private final int REQUEST = 0;
    private TextView txtViewAns,txtViewBMI;
    private Button btnBMI,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

    }

    protected void findViews(){
        txtViewAns=(TextView)findViewById(R.id.txtViewAns);
        txtViewBMI=(TextView)findViewById(R.id.txtViewBMI);
        btnBMI=(Button)findViewById(R.id.btnBMI);
        btnExit=(Button)findViewById(R.id.btnExit);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,BMIActivity.class);
                startActivityForResult(intent,REQUEST);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode!=REQUEST)
            return;

        switch (resultCode){
            case RESULT_OK:
                Bundle bundle=data.getExtras();
                Double bmi=bundle.getDouble("bmi");
                String bmiAns=bundle.getString("bmiAns");
                txtViewBMI.setText(bmi.toString());
                txtViewAns.setText(bmiAns);
                break;
            case RESULT_CANCELED:
                txtViewBMI.setText("");
                txtViewAns.setText("原來你只會逃避現實");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
