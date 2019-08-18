package com.example.myfirstapp;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EDIT_TEXT="EDIT_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText= (EditText) findViewById(R.id.editMessage);

        editText.addTextChangedListener(new TextChangedListener<EditText>(editText) {


            @Override
            public void onTextChanged(EditText target, Editable s) {
                //Do stuff
                Button button=(Button) findViewById(R.id.button);
                String message=target.getText().toString();
                if(message.length()>0){

                    button.setEnabled(true);
                }else{
                    button.setEnabled(false);
                }
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        final EditText editText= (EditText) findViewById(R.id.editMessage);
        Log.d("Debug","Saved instance");
        CharSequence userText = editText.getText();
        outState.putCharSequence("savedText", userText);
        super.onSaveInstanceState(outState, outPersistentState);


    }

    protected void sendMessage(View view){
        Intent intent= new Intent(this, DisplayMessageActivity.class);

         EditText editText= (EditText) findViewById(R.id.editMessage);
        String message= editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);

        startActivity(intent);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        final EditText textBox =
                (EditText) findViewById(R.id.editMessage);

        CharSequence userText =
                savedInstanceState.getCharSequence("savedText");

        textBox.setText(userText);
    }


}
