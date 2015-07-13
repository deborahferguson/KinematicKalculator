package com.developer.debbie.kinematickalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;



public class LinearSolutions extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        double acceleration = intent.getDoubleExtra("acceleration",0.0);
        double distance = intent.getDoubleExtra("distance",0.0);
        double iVelocity = intent.getDoubleExtra("initial velocity",0.0);
        double fVelocity = intent.getDoubleExtra("final velocity",0.0);
        double time = intent.getDoubleExtra("time",0.0);

        //rounding to keep 2 decimal places
        acceleration = ((double)(Math.round(acceleration*100)))/100.0;
        distance = ((double)(Math.round(distance*100)))/100.0;
        iVelocity = ((double)(Math.round(iVelocity*100)))/100.0;
        fVelocity = ((double)(Math.round(fVelocity*100)))/100.0;
        time = ((double)(Math.round(time*100)))/100.0;

        //keeping track of which equations are used
        double firstEquation = intent.getDoubleExtra("first equation", 0.0);
        double secondEquation = intent.getDoubleExtra("second equation", 0.0);

        setContentView(R.layout.activity_linear_solutions);

        TextView AccelerationValue = (TextView) findViewById(R.id.AccelerationValue);
        TextView DistanceValue = (TextView) findViewById(R.id.DistanceValue);
        TextView InitialVelocityValue = (TextView) findViewById(R.id.InitialVelocityValue);
        TextView FinalVelocityValue = (TextView) findViewById(R.id.FinalVelocityValue);
        TextView TimeValue = (TextView) findViewById(R.id.TimeValue);

        if(intent.getDoubleExtra("agiven",0.0)==0.0)
            AccelerationValue.setTextColor(Color.GREEN);
        if(intent.getDoubleExtra("dgiven",0.0)==0.0)
            DistanceValue.setTextColor(Color.GREEN);
        if(intent.getDoubleExtra("igiven",0.0)==0.0)
            InitialVelocityValue.setTextColor(Color.GREEN);
        if(intent.getDoubleExtra("fgiven",0.0)==0.0)
            FinalVelocityValue.setTextColor(Color.GREEN);
        if(intent.getDoubleExtra("tgiven",0.0)==0.0)
            TimeValue.setTextColor(Color.GREEN);

        AccelerationValue.setText(Double.toString(acceleration));
        DistanceValue.setText(Double.toString(distance));
        InitialVelocityValue.setText(Double.toString(iVelocity));
        FinalVelocityValue.setText(Double.toString(fVelocity));
        TimeValue.setText(Double.toString(time));

        ImageView Equation1 = (ImageView) findViewById(R.id.FirstEquationPicture);
        ImageView Equation2 = (ImageView) findViewById(R.id.SecondEquationPicture);

        switch ((int)firstEquation) {
            case 1:
                Equation1.setImageResource(R.drawable.equation1a);
                break;
            case 2:
                Equation1.setImageResource(R.drawable.equation1d);
                break;
            case 3:
                Equation1.setImageResource(R.drawable.equation1i);
                break;
            case 4:
                Equation1.setImageResource(R.drawable.equation1t);
                break;
            case 5:
                Equation1.setImageResource(R.drawable.equation2a);
                break;
            case 6:
                Equation1.setImageResource(R.drawable.equation2d);
                break;
            case 7:
                Equation1.setImageResource(R.drawable.equation2f);
                break;
            case 8:
                Equation1.setImageResource(R.drawable.equation2i);
                break;
            case 9:
                Equation1.setImageResource(R.drawable.equation3a);
                break;
            case 10:
                Equation1.setImageResource(R.drawable.equation3f);
                break;
            case 11:
                Equation1.setImageResource(R.drawable.equation3i);
                break;
            case 12:
                Equation1.setImageResource(R.drawable.equation3t);
                break;
            case 13:
                Equation1.setImageResource(R.drawable.equation4d);
                break;
            case 14:
                Equation1.setImageResource(R.drawable.equation4f);
                break;
            case 15:
                Equation1.setImageResource(R.drawable.equation4i);
                break;
            case 16:
                Equation1.setImageResource(R.drawable.equation4t);
                break;
            default:
                break;
        }

        switch ((int)secondEquation) {
            case 1:
                Equation2.setImageResource(R.drawable.equation1a);
                break;
            case 2:
                Equation2.setImageResource(R.drawable.equation1d);
                break;
            case 3:
                Equation2.setImageResource(R.drawable.equation1i);
                break;
            case 4:
                Equation2.setImageResource(R.drawable.equation1t);
                break;
            case 5:
                Equation2.setImageResource(R.drawable.equation2a);
                break;
            case 6:
                Equation2.setImageResource(R.drawable.equation2d);
                break;
            case 7:
                Equation2.setImageResource(R.drawable.equation2f);
                break;
            case 8:
                Equation2.setImageResource(R.drawable.equation2i);
                break;
            case 9:
                Equation2.setImageResource(R.drawable.equation3a);
                break;
            case 10:
                Equation2.setImageResource(R.drawable.equation3f);
                break;
            case 11:
                Equation2.setImageResource(R.drawable.equation3i);
                break;
            case 12:
                Equation2.setImageResource(R.drawable.equation3t);
                break;
            case 13:
                Equation2.setImageResource(R.drawable.equation4d);
                break;
            case 14:
                Equation2.setImageResource(R.drawable.equation4f);
                break;
            case 15:
                Equation2.setImageResource(R.drawable.equation4i);
                break;
            case 16:
                Equation2.setImageResource(R.drawable.equation4t);
                break;
            default:
                break;
        }

        //setContentView(R.layout.activity_linear_solutions);
        // Create the text view
        //TextView textView = new TextView(this);
        //textView.setTextSize(40);
        //textView.setText(Double.toString(acceleration)+" "+Double.toString(distance)+" "+Double.toString(iVelocity)+" "+Double.toString(fVelocity)+" "+Double.toString(time));

        // Set the text view as the activity layout
        //setContentView(textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_linear_solutions, menu);
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
