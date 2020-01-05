package com.titanz.titanztech.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.titanz.titanztech.R;
import com.titanz.titanztech.models.Servicos;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetalhesServicosActivity extends AppCompatActivity {

    private CircleImageView servicoImageView;
    private TextView detalheServicoTextView;
    private TextView nomeDetalheServicoTextView;
    private ImageView whatsappButton;
    private ImageView homeButtonServicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destalhes_servicos);
        getSupportActionBar().hide();

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
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
        },1000);

        servicoImageView = findViewById(R.id.detalhes_servicos_circleImageView);
        detalheServicoTextView = findViewById(R.id.detalhes_servico_textView);
        nomeDetalheServicoTextView = findViewById(R.id.nome_detalhes_servico_textView);

        whatsappButton = findViewById(R.id.whatsapp_button_servicos_imageView);
        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                irParaTitanz();
            }
        });

        homeButtonServicos = findViewById(R.id.home_button_servicos_imageView);
        homeButtonServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                irParaHome();
            }
        });

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        Servicos servico = (Servicos) bundle.getSerializable("SERVICOS");

        nomeDetalheServicoTextView.setText(servico.getNome());
        detalheServicoTextView.setText(servico.getDescricao());
        Picasso.get().load(servico.getImagem()).into(servicoImageView);

    }

    private void irParaHome() {

        onBackPressed();
    }

    public void irParaTitanz(){

        String titanz = "+55 11986661870"; // use country code with your phone number
        String url = "https://api.whatsapp.com/send?phone=" + titanz;
        try {
            PackageManager pm = this.getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

            final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
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
            Toast.makeText(this, "... poxa o Whatsapp não está instalado!!!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
