package com.angehack.sharedparking.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.angehack.sharedparking.R;


public class LoadingDialog {
    Context context;
    Dialog dialog;
    TextView textView;
    ProgressBar progressBar;

    public LoadingDialog(Context context) {
        this.context = context;

        dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = View.inflate(context, R.layout.loading_dialog, null);
        textView = view.findViewById(R.id.dialog_submit_text);
        progressBar = view.findViewById(R.id.dialog_pb);
        dialog.setContentView(view);
    }

    public void setMessage(String message) {
        textView.setText(message);
    }

    public void show() {
        if(dialog.isShowing()) {
            dialog.dismiss();
        }
        textView.setTextColor(Color.parseColor("#00a0e9"));
        progressBar.setVisibility(View.VISIBLE);
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public Dialog getDialog() {
        return dialog;
    }
}
