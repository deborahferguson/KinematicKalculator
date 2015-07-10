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


public class Linear2D extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Intent intent = getIntent();

        /*
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        */

        // Set the text view as the activity layout
        setContentView(R.layout.activity_linear2_d);

        Button CalculateButton = (Button) findViewById(R.id.calc);
        Button ResetButton = (Button) findViewById(R.id.ResetButton);

        CalculateButton.setOnClickListener(this);
        ResetButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_linear2_d, menu);
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

            double[] result = Equations(a, d, i, f, t);
            //if(result.contains("NaN")){
            //    result = "These values are not possible";
            //}
            //ErrorMessage.setText(result);
            boolean error = false;
            for(int j=0; j<6; j++){
                if(Double.toString(result[j]).equals("NaN")){
                    error=true;
                    break;
                }
            }
            if(error){
                result[5]=1;
            }
            if(result[5]==1){
                ErrorMessage.setText("These values are not possible");
            }
            else if(result[5]==2){
                ErrorMessage.setText("Insufficient information");
            }
            else {
                startSolutions(result);
            }
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

    public void startSolutions(double[] values){
        Intent intent = new Intent(this, LinearSolutions.class);
        intent.putExtra("acceleration", values[0]);
        intent.putExtra("distance", values[1]);
        intent.putExtra("initial velocity", values[2]);
        intent.putExtra("final velocity", values[3]);
        intent.putExtra("time", values[4]);
        intent.putExtra("agiven", values[6]);
        intent.putExtra("dgiven", values[7]);
        intent.putExtra("igiven", values[8]);
        intent.putExtra("fgiven", values[9]);
        intent.putExtra("tgiven", values[10]);
        intent.putExtra("first equation", values[11]);
        intent.putExtra("second equation", values[12]);

        startActivity(intent);
    }

    public double[] Equations(String a, String d, String i, String f, String t){
        //equation1a - 1
        //equation1d - 2
        //equation1i - 3
        //equation1t - 4
        //equation2a - 5
        //equation2d - 6
        //equation2f - 7
        //equation2i - 8
        //equation3a - 9
        //equation3f - 10
        //equation3i - 11
        //equation3t - 12
        //equation4d - 13
        //equation4f - 14
        //equation4i - 15
        //equation4t - 16
        double acceleration=0;
        double distance=0;
        double initialvel=0;
        double finalvel=0;
        double time=0;
        double error=0;//will be 0 for no error, 1 for values not possible, 2 for not enough information
        double agiven = 0;
        double dgiven = 0;
        double igiven = 0;
        double fgiven = 0;
        double tgiven = 0;
        double firstEquation = 0;
        double secondEquation = 0;

        if(a.equals("") && !d.equals("") && !i.equals("") && !f.equals("") && !t.equals("")){
            double dist = new Double(d);
            double init = new Double(i);
            double fin = new Double(f);
            double tim = new Double(t);
            distance = dist;
            initialvel = init;
            finalvel = fin;
            time = tim;
            dgiven = 1;
            igiven = 1;
            fgiven = 1;
            tgiven = 1;

            double acc = (fin-init)/tim;
            double acc2 = (dist-init*tim)*2/(tim*tim);
            if(Math.round(acc*10000)==Math.round(acc2*10000)){
                //result = "acceleration = "+acc;
                acceleration=acc;
                firstEquation = 9;
            }
            else{
                //result = "These values are not possible ";
                error=1;
            }
        }
        else if(a.equals("") && d.equals("") && !i.equals("") && !f.equals("") && !t.equals("")){
            double init = new Double(i);
            double fin = new Double(f);
            double tim = new Double(t);
            initialvel = init;
            finalvel = fin;
            time = tim;
            igiven = 1;
            fgiven = 1;
            tgiven = 1;

            double acc = (fin-init)/tim;
            firstEquation = 9;
            double dist = (init+fin)*tim/2.0;
            secondEquation = 13;

            //result = "acceleration = "+acc+" and distance = "+dist;
            acceleration = acc;
            distance = dist;
        }
        else if(a.equals("") && !d.equals("") && i.equals("") && !f.equals("") && !t.equals("")){
            double dist = new Double(d);
            double fin = new Double(f);
            double tim = new Double(t);
            distance = dist;
            finalvel = fin;
            time = tim;
            dgiven = 1;
            fgiven = 1;
            tgiven = 1;

            double init = dist*2/tim-fin;
            firstEquation = 15;
            double acc = (fin-init)/tim;
            secondEquation = 9;

            //result = "acceleration = "+acc+" and initial velocity = "+init;
            acceleration = acc;
            initialvel = init;
        }
        else if(a.equals("") && !d.equals("") && !i.equals("") && f.equals("") && !t.equals("")){
            double dist = new Double(d);
            double init = new Double(i);
            double tim = new Double(t);
            distance = dist;
            initialvel = init;
            time = tim;
            dgiven = 1;
            igiven = 1;
            tgiven = 1;

            double acc = (dist-init*tim)*2/(tim*tim);
            firstEquation = 1;
            double fin = dist*2/tim-init;
            secondEquation = 14;
            //result = "acceleration = "+acc+" and final velocity = "+fin;
            acceleration = acc;
            finalvel = fin;
        }
        else if(a.equals("") && !d.equals("") && !i.equals("") && !f.equals("") && t.equals("")){
            double dist = new Double(d);
            double init = new Double(i);
            double fin = new Double(f);
            distance = dist;
            initialvel = init;
            finalvel = fin;
            dgiven = 1;
            igiven = 1;
            fgiven = 1;

            double acc = (fin*fin-init*init)/(2*dist);
            firstEquation = 5;
            double tim = dist*2/(init+fin);
            secondEquation = 16;
            if(tim<0){
                tim = tim*-1;
            }

            //result = "acceleration = "+acc+" and time = "+tim;
            acceleration = acc;
            time = tim;
        }

        else if(!a.equals("") && d.equals("") && !i.equals("") && !f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double init = new Double(i);
            double fin = new Double(f);
            double tim = new Double(t);
            acceleration = acc;
            initialvel = init;
            finalvel = fin;
            time = tim;
            agiven = 1;
            igiven = 1;
            fgiven = 1;
            tgiven = 1;

            double dist = init*tim + acc*tim*tim/2;
            double dist2 = (init+fin)*tim/2;

            if(Math.round(dist*10000) == Math.round(dist2*10000)){
                //result = "distance = "+dist;
                distance = dist;
                firstEquation = 2;
            }
            else{
                //result = "Those values are not possible";
                error = 1;
            }
        }
        else if(!a.equals("") && d.equals("") && i.equals("") && !f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double fin = new Double(f);
            double tim = new Double(t);
            acceleration = acc;
            finalvel = fin;
            time = tim;
            agiven = 1;
            fgiven = 1;
            tgiven = 1;

            double init = fin-acc*tim;
            firstEquation = 11;
            //this equation seemed to be wrong....
            //check this
            //keep checking it
            //double dist = fin*tim-(-2)*(acc*tim)*(acc*tim);
            double dist = fin*tim-(1/2)*acc*tim*tim;
            secondEquation = 2;

            //result = "distance = "+dist+" and initial velocity = "+init;
            distance = dist;
            initialvel = init;
        }
        else if(!a.equals("") && d.equals("") && !i.equals("") && f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double init = new Double(i);
            double tim = new Double(t);
            acceleration = acc;
            initialvel = init;
            time = tim;
            agiven = 1;
            igiven = 1;
            tgiven = 1;

            double dist = init*tim+acc*tim*tim/2;
            firstEquation = 2;
            double fin = init+acc*tim;
            secondEquation = 10;
            //result = "distance = "+dist+" and final velocity = "+fin;
            distance = dist;
            finalvel = fin;
        }
        else if(!a.equals("") && d.equals("") && !i.equals("") && !f.equals("") && t.equals("")){
            double acc = new Double(a);
            double init = new Double(i);
            double fin = new Double(f);
            acceleration = acc;
            initialvel = init;
            finalvel = fin;
            agiven = 1;
            igiven = 1;
            fgiven = 1;

            double dist = (fin*fin-init*init)/(2*acc);
            firstEquation = 6;
            double tim = (fin-init)/acc;
            secondEquation = 12;
            if(tim<0){
                tim=tim*-1;
            }
            //result = "distance = "+dist+" and time = "+tim;
            distance = dist;
            time = tim;
        }

        else if(!a.equals("") && !d.equals("") && i.equals("") && !f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double fin = new Double(f);
            double tim = new Double(t);
            acceleration = acc;
            distance = dist;
            finalvel = fin;
            time = tim;
            agiven = 1;
            dgiven = 1;
            fgiven = 1;
            tgiven = 1;

            double init = fin-acc*tim;
            double init2 = dist*2*tim-fin;

            if(Math.round(init*10000) == Math.round(init2*10000)){
                //result = "initial velocity = "+init;
                initialvel = init;
                firstEquation = 11;
            }
            else {
                //result = "Those values are not possible";
                error = 1;
            }
        }
        else if(!a.equals("") && !d.equals("") && i.equals("") && f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double tim = new Double(t);
            acceleration = acc;
            distance = dist;
            time = tim;
            agiven = 1;
            dgiven = 1;
            tgiven = 1;

            double init = (dist-acc*tim*tim/2)/tim;
            firstEquation = 3;
            //double check this
            //double check this
            //double fin = (dist+(acc*tim)*(acc*tim)/2)/tim;
            double fin = (dist+(acc*tim*tim)/2)/tim;
            secondEquation = 10;

            //result = "initial velocity = "+init+" and final velocity = "+fin;
            initialvel = init;
            finalvel = fin;
        }
        else if(!a.equals("") && !d.equals("") && i.equals("") && !f.equals("") && t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double fin = new Double(f);
            acceleration = acc;
            distance = dist;
            finalvel = fin;
            agiven = 1;
            dgiven = 1;
            fgiven = 1;

            double init = Math.sqrt(fin*fin-2*acc*dist);
            firstEquation = 8;
            double tim = (fin-init)/acc;
            secondEquation = 12;
            if(tim<0){
                tim=tim*-1;
            }

            //result = "initial velocity = "+init+" and time = "+tim;
            initialvel = init;
            time = tim;
        }

        else if(!a.equals("") && !d.equals("") && !i.equals("") && f.equals("") && !t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double init = new Double(i);
            double tim = new Double(t);
            acceleration = acc;
            distance = dist;
            initialvel = init;
            time = tim;
            agiven = 1;
            dgiven = 1;
            igiven = 1;
            tgiven = 1;

            double fin = init+acc*tim;
            double fin2 = (dist*2/tim)-init;

            if(Math.round(fin*10000) == Math.round(fin2*10000)){
                //result = "final velocity = "+fin;
                finalvel = fin;
                firstEquation = 10;
            }
            else{
                //result = "Those values are not possible";
                error = 1;
            }
        }
        else if(!a.equals("") && !d.equals("") && !i.equals("") && f.equals("") && t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double init = new Double(i);
            acceleration = acc;
            distance = dist;
            initialvel = init;
            agiven = 1;
            dgiven = 1;
            igiven = 1;

            double fin = Math.sqrt(init*init+2*acc*dist);
            firstEquation = 7;
            double tim = (fin-init)/acc;
            secondEquation = 12;

            double dist2 = init*tim+acc*tim*tim/2;
            Log.i("tag", "" + dist2);
            if(Math.round(dist*10000) != Math.round(dist2*10000)){
                fin=-1*fin;
                tim = (fin-tim)/acc;
            }
            if(tim<0){
                tim=tim*-1;
            }

            //result = "final velocity = "+fin+" and time = "+tim;
            finalvel = fin;
            time = tim;
        }

        else if(!a.equals("") && !d.equals("") && !i.equals("") && !f.equals("") && t.equals("")){
            double acc = new Double(a);
            double dist = new Double(d);
            double init = new Double(i);
            double fin = new Double(f);
            acceleration = acc;
            distance = dist;
            initialvel = init;
            finalvel = fin;
            agiven = 1;
            dgiven = 1;
            igiven = 1;
            fgiven = 1;

            double tim = (fin-init)/acc;
            double tim2 = dist*2/(init+fin);

            if(tim<0){
                tim=-1*tim;
            }
            if(Math.round(tim*10000) == Math.round(tim2*10000)){
                //result = "time = "+tim;
                time = tim;
                firstEquation = 12;
            }
            else{
                //result = "Those values are not possible";
                error = 1;
            }
        }

        else{
            //result = "Please only leave 1 or 2 spaces blank";
            error = 2;
        }

        //return result;
        double[] returnVal = new double[13];
        returnVal[0]=acceleration;
        returnVal[1]=distance;
        returnVal[2]=initialvel;
        returnVal[3]=finalvel;
        returnVal[4]=time;
        returnVal[5]=error;
        returnVal[6]=agiven;
        returnVal[7]=dgiven;
        returnVal[8]=igiven;
        returnVal[9]=fgiven;
        returnVal[10]=tgiven;
        returnVal[11]=firstEquation;
        returnVal[12]=secondEquation;
        return returnVal;
    }


}
