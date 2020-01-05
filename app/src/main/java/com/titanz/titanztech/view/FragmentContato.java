package com.titanz.titanztech.view;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.titanz.titanztech.R;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class FragmentContato extends Fragment {

    private ImageView whatsappButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contato_fragment,container,false);

        view.setBackgroundColor(getResources().getColor(android.R.color.background_dark));

        whatsappButton = view.findViewById(R.id.titanz_whatsapp_button_id);

        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                irParaTitanz();
            }
        });


        return view;
    }

    public void irParaTitanz(){

        String titanz = "+55 11986661870"; // use country code with your phone number
        String url = "https://api.whatsapp.com/send?phone=" + titanz;
        try {
            PackageManager pm = getContext().getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

            final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(R.color.colorPrimary);
            pDialog.setTitleText("Carregando ...");

            pDialog.setCancelable(true);

            pDialog.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pDialog.dismiss();
                }
            },500);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getContext(), "... poxa o Whatsapp não está instalado!!!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
