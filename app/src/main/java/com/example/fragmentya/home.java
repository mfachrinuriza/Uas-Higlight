package com.example.fragmentya;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class home extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        ImageButton btn_order = (ImageButton) view.findViewById( R.id.btn_order );
        btn_order.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent order = new Intent (getActivity(), TicketActivity.class);
                startActivity( order );
            }
        });


        return view;
    }

}
