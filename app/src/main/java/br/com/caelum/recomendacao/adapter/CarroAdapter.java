package br.com.caelum.recomendacao.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.caelum.recomendacao.R;
import br.com.caelum.recomendacao.event.CarroClickLongEvent;
import br.com.caelum.recomendacao.model.Carro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


/**
 * Created by matheusbrandino on 2/9/17.
 */
public class CarroAdapter extends RecyclerView.Adapter {

    private final List<Carro> carros;


    public CarroAdapter(List<Carro> carros) {
        this.carros = carros;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carro, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        ViewHolder holder = (ViewHolder) viewHolder;

        Carro carro = carros.get(position);

        holder.ano.setText(carro.getAno().toString());
        holder.marca.setText(carro.getMarca());
        holder.modelo.setText(carro.getModelo());
        holder.cor.setText(carro.getCor());

    }

    @Override
    public int getItemCount() {
        return carros.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_ano)
        TextView ano;
        @BindView(R.id.item_marca)
        TextView marca;
        @BindView(R.id.item_cor)
        TextView cor;
        @BindView(R.id.item_nome)
        TextView modelo;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);

        }

        @OnClick(R.id.item)
        public void clickItem(){
            Carro carro = carros.get(getAdapterPosition());

            EventBus.getDefault().post(carro);
        }

        @OnLongClick(R.id.item)
        public boolean clickLong(){
            Carro carro = carros.get(getAdapterPosition());

            EventBus.getDefault().post(new CarroClickLongEvent(carro));
            return true;
        }

    }
}
