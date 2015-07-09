package com.developer.debbie.kinematickalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Radial extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_radial);


        Button CalculateButton = (Button) findViewById(R.id.calc);
        Button ResetButton = (Button) findViewById(R.id.ResetButton);

        CalculateButton.setOnClickListener(this);
        ResetButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_radial, menu);
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
    public void onClick(View v) {
        if(v.getId()==R.id.calc){
            TextView ErrorMessage = (TextView) findViewById(R.id.errors);
            EditText acceleration = (EditText) findViewById(R.id.acceleration);
            EditText distance = (EditText) findViewById(R.id.distance);
            EditText initVel = (EditText) findViewById(R.id.initVel);
            EditText finalVel = (EditText) findViewById(R.id.finalVel);
            EditText time = (EditText) findViewById(R.id.time);

            String a = acceleration.getText().toString();
            String d = distance.getText().toString();
            String i = initVel.getText().toString();
            String f = finalVel.getText().toString();
            String t = time.getText().toString();

            String result = Equations(a, d, i, f, t);
            if(result.contains("NaN")){
                result = "These values are not possible";
            }
            ErrorMessage.setText(result);
        }

        if(v.getId()==R.id.ResetButton){
            TextView ErrorMessage = (TextView) findViewById(R.id.errors);
            EditText acceleration = (EditText) findViewById(R.id.acceleration);
            EditText distance = (EditText) findViewById(R.id.distance);
            EditText initVel = (EditText) findViewById(R.id.initVel);
            EditText finalVel = (EditText) findViewById(R.id.finalVel);
            EditText time = (EditText) findViewById(R.id.time);

            ErrorMessage.setText("");
            acceleration.setText("");
            distance.setText("");
            initVel.setText("");
            finalVel.setText("");
            time.setText("");
        }
    }

    public String Equations(String a, String d, String i, String f, String t){
        String result = "";
        if(a.equals("") && !d.equals("") && !i.equals("") && !f.equals("") && !t.equals("")){
            double dist = new Double(d);
            double init = new Double(i);
            double fin = new Double(f);
            double tim = new Double(t);

            double acc = (fin-init)/tim;
            double acc2 = (dist-init*tim)*2/(tim*tim);
            if(Math.round(acc*10000)==Math.round(acc2*10000)){
                result = "acceleration = "+acc;
            }
            else{
                result = "These values are not possible ";
            }
        }
        else if(a.equals("") && d.equals("") && !i.equals("") && !f.equals("") && !t.equals("")){
            double init = new Double(i);
            double fin = new Double(f);
            double tim = new Double(t);

            double acc = (fin-init)/tim;
            double dist = (init+fin)*tim/2.0;

            result = "acceleration = "+acc+" and distance = "+dist;
        }
        else if(a.equals("") && !d.equals("") && i.equals("") && !f.equals("") && !t.equals("")){
            double dist = new Double(d);
            double fin = new Double(f);
            double tim = new Double(t);

            double init = dist*2/tim-fin;
            double acc = (fin-init)/tim;

            result = "acceleration = "+acc+" and initial velocity = "+init;
        }
        else if(a.equals("") && !d.equals("") && !i.equals("") && f.equals("") && !t.equals("")){
            double dist = new Double(d);
            double init = new Double(i);
            double tim = new Double(t);

            double acc = (dist-init*tim)*2/(tim*tim);
            double fin = dist*2/tim-init;

            result = "acceleration = "+acc+" and final velocity = "+fin;
        }
        else if(a.equals("") && !d.equals("") && !i.equals("") && !f.equals("") && t.equals("")){
            double dist = new Double(d);
            double init = new Double(i);
            double fin = new Double(f);

            double acc = (fin*fin-init*init)/(2*dist);
            double tim = dist*2/(init+fin);
            if(tim<0){
                tim = tim*-1;
            }

            result = "acceleration = "+acc+" and time = "+tim;
        }

        else if(!a.equals("") && d.equals("") && !i.equals("") && !f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double init = new Double(i);
            double fin = new Double(f);
            double tim = new Double(t);

            double dist = init*tim + acc*tim*tim/2;
            double dist2 = (init+fin)*tim/2;

            if(Math.round(dist*10000) == Math.round(dist2*10000)){
                result = "distance = "+dist;
            }
            else{
                result = "Those values are not possible";
            }
        }
        else if(!a.equals("") && d.equals("") && i.equals("") && !f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double fin = new Double(f);
            double tim = new Double(t);

            double init = fin-acc*tim;
            double dist = fin*tim-(-2)*(acc*tim)*(acc*tim);

            result = "distance = "+dist+" and initial velocity = "+init;
        }
        else if(!a.equals("") && d.equals("") && !i.equals("") && f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double init = new Double(i);
            double tim = new Double(t);

            double dist = init*tim+acc*tim*tim/2;
            double fin = init+acc*tim;

            result = "distance = "+dist+" and final velocity = "+fin;
        }
        else if(!a.equals("") && d.equals("") && !i.equals("") && !f.equals("") && t.equals("")){
            double acc = new Double(a);
            double init = new Double(i);
            double fin = new Double(f);

            double dist = (fin*fin-init*init)/(2*acc);
            double tim = (fin-init)/acc;
            if(tim<0){
                tim=tim*-1;
            }
            result = "distance = "+dist+" and time = "+tim;
        }

        else if(!a.equals("") && !d.equals("") && i.equals("") && !f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double fin = new Double(f);
            double tim = new Double(t);

            double init = fin-acc*tim;
            double init2 = dist*2*tim-fin;

            if(Math.round(init*10000) == Math.round(init2*10000)){
                result = "initial velocity = "+init;
            }
            else {
                result = "Those values are not possible";
            }
        }
        else if(!a.equals("") && !d.equals("") && i.equals("") && f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double tim = new Double(t);

            double init = (dist-acc*tim*tim/2)/tim;
            double fin = (dist+(acc*tim)*(acc*tim)/2)/tim;

            result = "initial velocity = "+init+" and final velocity = "+fin;
        }
        else if(!a.equals("") && !d.equals("") && i.equals("") && !f.equals("") && t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double fin = new Double(f);

            double init = Math.sqrt(fin*fin-2*acc*dist);
            double tim = (fin-init)/acc;
            if(tim<0){
                tim=tim*-1;
            }

            result = "initial velocity = "+init+" and time = "+tim;
        }

        else if(!a.equals("") && !d.equals("") && !i.equals("") && f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double init = new Double(i);
            double tim = new Double(t);

            double fin = init+acc*tim;
            double fin2 = (dist*2/tim)-init;

            if(Math.round(fin*10000) == Math.round(fin2*10000)){
                result = "final velocity = "+fin;
            }
            else{
                result = "Those values are not possible";
            }
        }
        else if(!a.equals("") && !d.equals("") && !i.equals("") && f.equals("") && t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double init = new Double(i);

            double fin = Math.sqrt(init*init+2*acc*dist);
            double tim = (fin-init)/acc;

            double dist2 = init*tim+acc*tim*tim/2;
            Log.i("tag", "" + dist2);
            if(Math.round(dist*10000) != Math.round(dist2*10000)){
                fin=-1*fin;
                tim = (fin-tim)/acc;
            }
            if(tim<0){
                tim=tim*-1;
            }

            result = "final velocity = "+fin+" and time = "+tim;
        }

        else if(!a.equals("") && !d.equals("") && !i.equals("") && !f.equals("") && t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double init = new Double(i);
            double fin = new Double(f);

            double tim = (fin-init)/acc;
            double tim2 = dist*2/(init+fin);

            if(tim<0){
                tim=-1*tim;
            }
            if(Math.round(tim*10000) == Math.round(tim2*10000)){
                result = "time = "+tim;
            }
            else{
                result = "Those values are not possible";
            }
        }

        else{
            result = "Please only leave 1 or 2 spaces blank";
        }

        return result;
    }

}
