package com.developer.debbie.kinematickalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



/*
    TODO Figure out how to determine whether or not the final velocity should be positive or negative when not given time
 */



public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public final static String EXTRA_MESSAGE = "com.developer.debbie.kinematickalculator.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button linear2D = (Button) findViewById(R.id.linear2D);
        linear2D.setOnClickListener(this);

        Button radial = (Button) findViewById(R.id.radial);
        radial.setOnClickListener(this);

        Button projectile = (Button) findViewById(R.id.projectile2D);
        projectile.setOnClickListener(this);
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


    public void startLinear2D(){
        Intent intent = new Intent(this, Linear2D.class);
  //      EditText acceleration = (EditText) findViewById(R.id.acceleration);
  //      String message = acceleration.getText().toString();
  //      intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void startRadial(){
        Intent intent = new Intent(this, Radial.class);
        startActivity(intent);
    }

    public void startProjectile(){
        Intent intent = new Intent(this, Projectile.class);
        startActivity(intent);
    }

    public void onClick(View v){
        if(v.getId()==R.id.linear2D){
            startLinear2D();
        }

        if(v.getId()==R.id.radial){
            startRadial();
        }

        if(v.getId()==R.id.projectile2D){
            startProjectile();
        }
    }


}
