package com.hfad.vacationdestination;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogShowDestination extends DialogFragment {

    private VacationDestination vaDe;
    private TextView tvName;

    public DialogShowDestination(VacationDestination vd) {
        vaDe = vd;
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_row, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        tvName = dialogView.findViewById(R.id.tvName);
        Button btnDone = dialogView.findViewById(R.id.btnDone);
        //RadioButton rbFav = dialogView.findViewById(R.id.rbFavorite);
        CheckBox cbFav = dialogView.findViewById(R.id.cbFav);

        tvName.setText(vaDe.getName());

        if(vaDe.isFavorite())
        {
            cbFav.setChecked(true);
        }
        else
        {
            cbFav.setChecked(false);
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        builder.setView(dialogView);
        return builder.create();
    }


}
