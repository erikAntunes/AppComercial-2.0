package com.titanz.titanztech.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.titanz.titanztech.R;
import com.titanz.titanztech.interfaces.ServicosListener;
import com.titanz.titanztech.models.Servicos;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServicosAdapter extends RecyclerView.Adapter<ServicosAdapter.DetalheServicosViewHolder> {


    private List<Servicos> listaServicos;

    private ServicosListener servicosListener;

    public ServicosAdapter(List<Servicos> listaServicos, ServicosListener servicosListener){

        this.servicosListener = servicosListener;
        this.listaServicos = listaServicos;
    }



    @NonNull
    @Override
    public ServicosAdapter.DetalheServicosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celula_servicos,viewGroup,false);

        DetalheServicosViewHolder viewHolderDetalhe = new DetalheServicosViewHolder(view);

        return viewHolderDetalhe;
    }


    @Override
    public void onBindViewHolder(@NonNull DetalheServicosViewHolder detalheServicosViewHolder, final int i) {
        final Servicos servicos = listaServicos.get(i);

        detalheServicosViewHolder.setupServicos(servicos);

        detalheServicosViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicosListener.onServicoClicado(listaServicos.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaServicos.size();
    }

    class DetalheServicosViewHolder extends RecyclerView.ViewHolder {

        private TextView textDescricaoServicosView;

        private TextView textIdServicosView;

        private CircleImageView servicosImageView;

        public DetalheServicosViewHolder(@NonNull View itemView) {
            super(itemView);

            textDescricaoServicosView = itemView.findViewById(R.id.textDescricaoServicosView_id);
            textIdServicosView = itemView.findViewById(R.id.servicos_textView_id_id);
            servicosImageView = itemView.findViewById(R.id.servicos_imageView_id);
        }

        public void setupServicos(Servicos servicos){

            textDescricaoServicosView.setText(servicos.getNome());
            textIdServicosView.setText(servicos.getId());
            Picasso.get().load(servicos.getImagem()).into(servicosImageView);
        }

    }

}
