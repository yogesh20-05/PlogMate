package com.example.megamart.common;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.megamart.R;



public class NetworkChangeListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!NetworkDetails.isConnectedToInternet(context))
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            View layoutDailog= LayoutInflater.from(context).inflate(R.layout.check_net_connection,null);
            builder.setView(layoutDailog);

            AppCompatButton btnRetry=layoutDailog.findViewById(R.id.btnRetryConnection);

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);

            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    onReceive(context,intent);
                }
            });
        }
        else{
            Toast.makeText(context,"Internet is connected",Toast.LENGTH_SHORT).show();
        }
    }
}
