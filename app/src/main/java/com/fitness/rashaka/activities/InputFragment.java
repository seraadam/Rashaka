package com.fitness.rashaka.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
    EditText results ;
    // RadioGroup gender ;
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
        // gender = (RadioGroup) v.findViewById(R.id.gender);
        results = (EditText) v.findViewById(R.id.results);
        result = (Button) v.findViewById(R.id.result);


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculate();
                valueheight = Double.parseDouble(height.getText().toString());
                valueheight = valueheight/100;

                valueweight = Double.parseDouble(weight.getText().toString());

                bmi = (valueweight/ (valueheight * valueheight));

                results.setText(Double.toString(bmi));


            }
        });
        return v;
    }

    private void calculate(){


    }

}
