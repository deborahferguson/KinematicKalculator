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


public class Projectile extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent intent = getIntent();


        setContentView(R.layout.activity_projectile);

        Button CalculateButton = (Button) findViewById(R.id.calc);
        Button ResetButton = (Button) findViewById(R.id.ResetButton);

        CalculateButton.setOnClickListener(this);
        ResetButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_projectile, menu);
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
            EditText xacceleration = (EditText) findViewById(R.id.acceleration);
            EditText xdistance = (EditText) findViewById(R.id.distance);
            EditText xinitVel = (EditText) findViewById(R.id.initVel);
            EditText xfinalVel = (EditText) findViewById(R.id.finalVel);
            EditText time = (EditText) findViewById(R.id.time);

            EditText yacceleration = (EditText) findViewById(R.id.yacceleration);
            EditText ydistance = (EditText) findViewById(R.id.ydistance);
            EditText yinitVel = (EditText) findViewById(R.id.yinitVel);
            EditText yfinalVel = (EditText) findViewById(R.id.yfinalVel);

            String ax = xacceleration.getText().toString();
            String dx = xdistance.getText().toString();
            String ix = xinitVel.getText().toString();
            String fx = xfinalVel.getText().toString();
            String t = time.getText().toString();

            String ay = yacceleration.getText().toString();
            String dy = ydistance.getText().toString();
            String iy = yinitVel.getText().toString();
            String fy = yfinalVel.getText().toString();

            //counting up how many things we know for x
            int numberx = 0;
            if(!ax.equals("")){
                numberx++;
            }
            if(!dx.equals("")){
                numberx++;
            }
            if(!ix.equals("")){
                numberx++;
            }
            if(!fx.equals("")){
                if(!ix.equals("")) {
                    if (!ax.equals("")) {
                        Double accx = new Double(ax);
                        if (accx != 0) {
                            numberx++;
                        }
                    }
                }
                else{
                    numberx++;
                }
            }

            //counting up how many things we know for y
            int numbery=0;
            if(!ay.equals("")){
                numbery++;
            }
            if(!dy.equals("")){
                numbery++;
            }
            if(!iy.equals("")){
                numbery++;
            }
            if(!fy.equals("")){
                numbery++;
            }

            //if we are given time
            //we need two values for x to calculate the rest and two values for y to calculate the rest
            if(!t.equals("")){
                if(numberx>1){
                    double[] newValues = Equations(ax,dx,ix,fx,t);
                    if(newValues[5]==1){
                        ErrorMessage.setText("These values are not possible");
                    }
                    else if(newValues[5]==2){
                        ErrorMessage.setText("Insufficient information");
                    }
                    else{
                        xacceleration.setText(Double.toString(newValues[0]));
                        xdistance.setText(Double.toString(newValues[1]));
                        xinitVel.setText(Double.toString(newValues[2]));
                        xfinalVel.setText(Double.toString(newValues[3]));
                        time.setText(Double.toString(newValues[4]));
                    }
                }
                if(numbery>1){
                    double[] newValues = Equations(ay,dy,iy,fy,t);
                    if(newValues[5]==1){
                        ErrorMessage.setText("These values are not possible");
                    }
                    else if(newValues[5]==2){
                        ErrorMessage.setText("Insufficient information");
                    }
                    else{
                        yacceleration.setText(Double.toString(newValues[0]));
                        ydistance.setText(Double.toString(newValues[1]));
                        yinitVel.setText(Double.toString(newValues[2]));
                        yfinalVel.setText(Double.toString(newValues[3]));
                        time.setText(Double.toString(newValues[4]));
                    }
                }
            }

            //if we don't have time
            //we need three of either x or y, use that to calculate time and then we need two of the other to calculate the rest
            else{
                if(numberx>2){
                    double[] newValues = Equations(ax,dx,ix,fx,t);
                    if(newValues[5]==1){
                        ErrorMessage.setText("These values are not possible");
                    }
                    else if(newValues[5]==2){
                        ErrorMessage.setText("Insufficient information");
                    }
                    else{
                        double[] newYValues = Equations(ay,dy,iy,fy,Double.toString(newValues[4]));
                        if(newValues[5]==1){
                            ErrorMessage.setText("These values are not possible");
                        }
                        else if(newValues[5]==2){
                            ErrorMessage.setText("Insufficient information");
                        }
                        else{
                            yacceleration.setText(Double.toString(newYValues[0]));
                            ydistance.setText(Double.toString(newYValues[1]));
                            yinitVel.setText(Double.toString(newYValues[2]));
                            yfinalVel.setText(Double.toString(newYValues[3]));
                        }
                        xacceleration.setText(Double.toString(newValues[0]));
                        xdistance.setText(Double.toString(newValues[1]));
                        xinitVel.setText(Double.toString(newValues[2]));
                        xfinalVel.setText(Double.toString(newValues[3]));
                        time.setText(Double.toString(newValues[4]));
                    }
                }
                else if(numbery>2){
                    double[] newValues = Equations(ay,dy,iy,fy,t);
                    if(newValues[5]==1){
                        ErrorMessage.setText("These values are not possible");
                    }
                    else if(newValues[5]==2){
                        ErrorMessage.setText("Insufficient information");
                    }
                    else{
                        double[] newXValues = Equations(ax,dx,ix,fx,Double.toString(newValues[4]));
                        if(newValues[5]==1){
                            ErrorMessage.setText("These values are not possible");
                        }
                        else if(newValues[5]==2){
                            ErrorMessage.setText("Insufficient information");
                        }
                        else{
                            xacceleration.setText(Double.toString(newXValues[0]));
                            xdistance.setText(Double.toString(newXValues[1]));
                            xinitVel.setText(Double.toString(newXValues[2]));
                            xfinalVel.setText(Double.toString(newXValues[3]));
                        }
                        yacceleration.setText(Double.toString(newValues[0]));
                        ydistance.setText(Double.toString(newValues[1]));
                        yinitVel.setText(Double.toString(newValues[2]));
                        yfinalVel.setText(Double.toString(newValues[3]));
                        time.setText(Double.toString(newValues[4]));
                    }
                }
                else{
                    ErrorMessage.setText("Insufficient information");
                }
            }
        }

        if(v.getId()==R.id.ResetButton){
            TextView ErrorMessage = (TextView) findViewById(R.id.errors);
            EditText xacceleration = (EditText) findViewById(R.id.acceleration);
            EditText xdistance = (EditText) findViewById(R.id.distance);
            EditText xinitVel = (EditText) findViewById(R.id.initVel);
            EditText xfinalVel = (EditText) findViewById(R.id.finalVel);
            EditText time = (EditText) findViewById(R.id.time);

            EditText yacceleration = (EditText) findViewById(R.id.yacceleration);
            EditText ydistance = (EditText) findViewById(R.id.ydistance);
            EditText yinitVel = (EditText) findViewById(R.id.yinitVel);
            EditText yfinalVel = (EditText) findViewById(R.id.yfinalVel);

            ErrorMessage.setText("");
            xacceleration.setText("");
            xdistance.setText("");
            xinitVel.setText("");
            xfinalVel.setText("");
            time.setText("");

            yacceleration.setText("");
            ydistance.setText("");
            yinitVel.setText("");
            yfinalVel.setText("");
        }
    }

    public double[] Equations(String a, String d, String i, String f, String t){
        double acceleration=0;
        double distance=0;
        double initialvel=0;
        double finalvel=0;
        double time=0;
        double error=0;//will be 0 for no error, 1 for values not possible, 2 for not enough information

        if(a.equals("") && !d.equals("") && !i.equals("") && !f.equals("") && !t.equals("")){
            double dist = new Double(d);
            double init = new Double(i);
            double fin = new Double(f);
            double tim = new Double(t);
            distance = dist;
            initialvel = init;
            finalvel = fin;
            time = tim;

            double acc = (fin-init)/tim;
            double acc2 = (dist-init*tim)*2/(tim*tim);
            if(Math.round(acc*10000)==Math.round(acc2*10000)){
                //result = "acceleration = "+acc;
                acceleration=acc;
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

            double acc = (fin-init)/tim;
            double dist = (init+fin)*tim/2.0;

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

            double init = dist*2/tim-fin;
            double acc = (fin-init)/tim;

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

            double acc = (dist-init*tim)*2/(tim*tim);
            double fin = dist*2/tim-init;

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

            double acc = (fin*fin-init*init)/(2*dist);
            double tim = dist*2/(init+fin);
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

            double dist = init*tim + acc*tim*tim/2;
            double dist2 = (init+fin)*tim/2;

            if(Math.round(dist*10000) == Math.round(dist2*10000)){
                //result = "distance = "+dist;
                distance = dist;
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

            double init = fin-acc*tim;
            double dist = fin*tim-(-2)*(acc*tim)*(acc*tim);

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

            double dist = init*tim+acc*tim*tim/2;
            double fin = init+acc*tim;

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

            double dist = (fin*fin-init*init)/(2*acc);
            double tim = (fin-init)/acc;
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

            double init = fin-acc*tim;
            double init2 = dist*2*tim-fin;

            if(Math.round(init*10000) == Math.round(init2*10000)){
                //result = "initial velocity = "+init;
                initialvel = init;
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

            double init = (dist-acc*tim*tim/2)/tim;
            double fin = (dist+(acc*tim)*(acc*tim)/2)/tim;

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

            double init = Math.sqrt(fin*fin-2*acc*dist);
            double tim = (fin-init)/acc;
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

            double fin = init+acc*tim;
            double fin2 = (dist*2/tim)-init;

            if(Math.round(fin*10000) == Math.round(fin2*10000)){
                //result = "final velocity = "+fin;
                finalvel = fin;
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

            double tim = (fin-init)/acc;
            double tim2 = dist*2/(init+fin);

            if(tim<0){
                tim=-1*tim;
            }
            if(Math.round(tim*10000) == Math.round(tim2*10000)){
                //result = "time = "+tim;
                time = tim;
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

        double[] returnVal = new double[6];
        returnVal[0]=acceleration;
        returnVal[1]=distance;
        returnVal[2]=initialvel;
        returnVal[3]=finalvel;
        returnVal[4]=time;
        returnVal[5]=error;

        return returnVal;
    }
}
