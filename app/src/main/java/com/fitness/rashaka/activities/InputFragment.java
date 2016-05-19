package com.fitness.rashaka.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.fitness.rashaka.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {


    public InputFragment() {
        // Required empty public constructor
    }

    EditText weight ;
    EditText height ;
    EditText age;
    EditText results ;
    Spinner wspinner;
    Spinner hspinner;
    Spinner aspinner;
    // RadioGroup gender ;
    ImageButton male;
    ImageButton female;

    Button result;
    double valueheight =0;
    double valueweight =0 ;
    double bmi =0 ;
    int w , h , a ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_input, container, false);
        weight = (EditText) v.findViewById(R.id.weight);
        height = (EditText) v.findViewById(R.id.height);
        age = (EditText) v.findViewById(R.id.age);

        // gender = (RadioGroup) v.findViewById(R.id.gender);
        results = (EditText) v.findViewById(R.id.results);
        result = (Button) v.findViewById(R.id.result);

        male = (ImageButton) v.findViewById(R.id.imageButton);
        female = (ImageButton) v.findViewById(R.id.imageButton2);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setImageResource(R.drawable.male_selected);
                female.setImageResource(R.drawable.female_unselected);
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setImageResource(R.drawable.male_unselected);
                female.setImageResource(R.drawable.female_selected);
            }
        });

       wspinner = (Spinner) v.findViewById(R.id.wspinner);
        hspinner = (Spinner) v.findViewById(R.id.hspinner);
        aspinner = (Spinner) v.findViewById(R.id.aspinner);


        ArrayAdapter<CharSequence> wadapter = ArrayAdapter.createFromResource(getContext(), R.array.weight,
//        android.R.layout.simple_spinner_item);
                R.layout.spinner_item_text);
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wadapter.setDropDownViewResource(R.layout.spinner_item_text);
        wspinner.setAdapter(wadapter);
        wspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> hadapter = ArrayAdapter.createFromResource(getContext(), R.array.height,
//        android.R.layout.simple_spinner_item);
                R.layout.spinner_item_text);
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hadapter.setDropDownViewResource(R.layout.spinner_item_text);
        hspinner.setAdapter(hadapter);
        hspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> aadapter = ArrayAdapter.createFromResource(getContext(), R.array.activity,
//        android.R.layout.simple_spinner_item);
                R.layout.spinner_item_text);
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aadapter.setDropDownViewResource(R.layout.spinner_item_text);
        aspinner.setAdapter(aadapter);
        aspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               try {

                   calculate();


                   valueheight = Double.parseDouble(height.getText().toString().replace(",", ""));

                   valueheight = valueheight / 100;

                   valueweight = Double.parseDouble(weight.getText().toString());

                   bmi = (valueweight / (valueheight * valueheight));

                   results.setText( String.format( "%.1f", bmi ) );
                   //results.setText(Double.toString(bmi));


               }
               catch (NumberFormatException e){
                   Toast.makeText(getContext(),"please enter values",Toast.LENGTH_LONG).show();
               }
            }
        });
        return v;
    }

    private void calculate(){

/*
* This calculator provides an estimate of your total daily caloric expenditure by multiplying the Harris-Benedict equations for basal metabolic rate by an "Activity Level Factor" that accounts for your daily physical activity levels and the thermic effect of food. The equations used by this calculator are shown below.

    Male (metric): DCE = ALF x ((13.75 x WKG) + (5 x HC) - (6.76 x age) + 66)
    Male (imperial): DCE = ALF x ((6.25 x WP) + (12.7 x HI) - (6.76 x age) + 66)
    Female (metric): DCE = ALF x ((9.56 x WKG) + (1.85 x HC) - 4.68 x age) + 655)
    Female (imperial): DCE = ALF x ((4.35 x WP) + (4.7 x HI) - 4.68 x age) + 655)

where

ALF = Activity Level Factor
DCE = Daily Caloric Expenditure
HC = Height in Centimetres
HI = Height in Inches
WKG = Weight in Kilograms
WP = Weight in Pounds

and "Activity Level Factor" has the following values

"Sedentary": Activity Level Factor = 1.2
"Lightly Active": Activity Level Factor = 1.375
"Moderately Active": Activity Level Factor = 1.55
"Very Active": Activity Level Factor = 1.725
"Extremely Active": Activity Level Factor = 1.9
*
*
* */
    }

}
