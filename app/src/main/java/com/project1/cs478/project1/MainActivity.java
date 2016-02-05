package com.project1.cs478.project1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {
    String textString;
    Intent sendIntent;
    EditText phoneText;
    private String phoneNumber;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button phoneButton = (Button)findViewById(R.id.phoneButton);
        phoneText = (EditText)findViewById(R.id.phoneText);
        phoneText.setWidth(1000);



        phoneButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        textString = phoneText.getText().toString();
                        boolean isValid = isPhoneNumberValid(textString);
                        if (isValid == true) {
                            sendIntent = new Intent(Intent.ACTION_VIEW);
                            sendIntent.setData(Uri.parse("sms:" + MainActivity.this.getTextString()));
                            startActivity(sendIntent);
                        }
                    }
                }
        );
    }

    public boolean isPhoneNumberValid(String textString){
        Pattern pattern = Pattern.compile("\\([0-9]{3}\\)\\s?[0-9]{3}-[0-9]{4}");
        Matcher matchPattern = pattern.matcher(textString);
        if(matchPattern.find()){
            this.setTextString(matchPattern.group(0));
            return true;
        }
        return false;
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

    @Override
    protected void onResume() {
        super.onResume();
        counter++;
        if(counter>1){
            phoneText.setText("Returning from Compose Message...");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void setTextString(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public String getTextString(){
        return phoneNumber;
    }
}
