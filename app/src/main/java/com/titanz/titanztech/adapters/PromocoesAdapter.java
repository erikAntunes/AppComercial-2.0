package com.titanz.titanztech.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.titanz.titanztech.R;
import com.titanz.titanztech.interfaces.PromocoesListener;
import com.titanz.titanztech.models.Promocoes;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PromocoesAdapter extends RecyclerView.Adapter<PromocoesAdapter.DetalhePromocoesViewHolder> {



    private List<Promocoes> listaPromocoes;

    private PromocoesListener promocoesListener;

    public PromocoesAdapter(List<Promocoes> listaPromocoes, PromocoesListener promocoesListener){

        this.promocoesListener = promocoesListener;
        this.listaPromocoes = listaPromocoes;
    }



    @NonNull
    @Override
    public DetalhePromocoesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.celula_promocoes,viewGroup,false);

        DetalhePromocoesViewHolder detalhePromocoesViewHolder = new DetalhePromocoesViewHolder(view);

        return  detalhePromocoesViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull DetalhePromocoesViewHolder detalhePromocoesViewHolder, final int i) {
        final Promocoes promocoes = listaPromocoes.get(i);

        detalhePromocoesViewHolder.setupPromocoes(promocoes);

        detalhePromocoesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                promocoesListener.onPromocaoClicada(listaPromocoes.get(i));

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaPromocoes.size();
    }

    class DetalhePromocoesViewHolder extends RecyclerView.ViewHolder {

        private TextView texNomePromocoesView;

        private TextView textValorPromocoesView;



        public DetalhePromocoesViewHolder(@NonNull View itemView) {
            super(itemView);

            texNomePromocoesView = itemView.findViewById(R.id.textPromocoesNomeView_id);
            textValorPromocoesView = itemView.findViewById(R.id.textValorPromocoesView_id);


        }

        public void setupPromocoes(Promocoes promocoes){

            textValorPromocoesView.setText(promocoes.getValor());
            texNomePromocoesView.setText(promocoes.getNome());

        }

    }

}
