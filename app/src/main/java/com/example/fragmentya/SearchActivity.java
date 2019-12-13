package com.example.fragmentya;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


public class SearchActivity extends Fragment {
    private String KEY_QUERY = "TONY STARK";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.activity_search, container, false);

        Button submitCari = (Button) view.findViewById( R.id.submitBrowse );
        submitCari.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                EditText nameField = (EditText) view.findViewById( R.id.field_browse );
                Editable nameEditable = nameField.getText();
                String query = nameEditable.toString();

                Intent in = new Intent (getActivity(), Hightlight.class);
                in.putExtra(KEY_QUERY, query);
                startActivity( in );
            }
        });


        return view;
    }
}
