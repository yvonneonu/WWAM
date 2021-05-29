package com.example.waam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UnfriendBottomsheet extends BottomSheetDialogFragment {

    public UnfriendBottomsheet() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_unfriend, container, false);
        TextView block = view.findViewById(R.id.textView28);
        TextView cancel = view.findViewById(R.id.textView2);
        TextView unfriend = view.findViewById(R.id.textView27);

        unfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "This user is no longer in your friend list";
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        });


        block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "User Blocked";
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }
}
