package com.example.waam;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UnfriendBottomsheet extends BottomSheetDialogFragment {

    public UnfriendBottomsheet() {

    }





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setMargins(view);
    }

    @Override
    public int getTheme() {
        return R.style.NoBackgroundDialogTheme;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_unfriend, container, false);

//        view.setBackgroundColor(Color.TRANSPARENT);
//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent())
//                .getLayoutParams();
//        CoordinatorLayout.Behavior behavior = params.getBehavior();
//        ((View) contentView.getParent()).setBackgroundColor(Color.TRANSPARENT);

        view.setBackgroundColor(Color.TRANSPARENT);

        TextView block = view.findViewById(R.id.textView28);
        ImageView cancel = view.findViewById(R.id.textView2);
        TextView unfriend = view.findViewById(R.id.textView27);


        unfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Unfrien();
//                String message = "This user is no longer in your friend list";
                String message = "User has been Blocked";

                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        });


        block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "User has been Reported";
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

    private void Unfrien() {
        //if (CurrentS)
    }

    void setMargins(View view) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(10, 0, 10, 0);
            view.requestLayout();
        }
    }

}