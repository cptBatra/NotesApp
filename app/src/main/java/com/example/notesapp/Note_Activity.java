package com.example.notesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Note_Activity extends AppCompatActivity {

    private Timer timer = new Timer();
    private final long DELAY = 1000; // in ms
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_);
        Intent intent = getIntent();
        final int position = intent.getIntExtra("position",0);
        final EditText editTextStop = (EditText) findViewById(R.id.editText);
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.notesapp",MODE_PRIVATE);
       if(position < MainActivity.myNotes.size()) {
           editTextStop.setText(MainActivity.myNotes.get(position));
       }


    /*    editTextStop.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {
               if(timer != null)
                 timer.cancel();
            }
            @Override
            public void afterTextChanged(final Editable s) {
                //avoid triggering event when text is too short
                if (s.length() >= 3) {

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // TODO: do what you need here (refresh list)
                            // you will probably need to use
                            // runOnUiThread(Runnable action) for some specific
                            // actions

                            String noteText = editTextStop.getText().toString();
                            MainActivity.myNotes.set(position, noteText);
                            //MainActivity.arrayAdapter.notifyDataSetChanged();
                            try {
                                sharedPreferences.edit().putString("myNotes",ObjectSerializer.serialize(MainActivity.myNotes)).apply();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                   }, DELAY);
                }
            }
        });
*/

            editTextStop.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {

                        if(position==MainActivity.myNotes.size()){
                            MainActivity.myNotes.add("");
                        }
                        String noteText = editTextStop.getText().toString();
                        Log.i("Note id and status","Filled"+String.valueOf(position));
                        MainActivity.myNotes.set(position, noteText);
                        MainActivity.arrayAdapter.notifyDataSetChanged();
                        try {
                            sharedPreferences.edit().putString("myNotes", ObjectSerializer.serialize(MainActivity.myNotes)).apply();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });
    }
}
