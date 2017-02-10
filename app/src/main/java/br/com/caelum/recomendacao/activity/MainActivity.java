package br.com.caelum.recomendacao.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.recomendacao.R;
import br.com.caelum.recomendacao.adapter.CarroAdapter;
import br.com.caelum.recomendacao.application.CarroApplication;
import br.com.caelum.recomendacao.event.CarroClickLongEvent;
import br.com.caelum.recomendacao.model.Carro;
import br.com.caelum.recomendacao.model.CarroDao;
import br.com.caelum.recomendacao.webservices.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lista_carros)
    RecyclerView listaCarros;

    @Inject
    CarroDao carroDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CarroApplication.getInstance().getComponent().inject(this);


    }

    @Subscribe
    public void lidaComClick(Carro carro) {
        Intent intent = new Intent(this, FormularioActivity.class);
        intent.putExtra("carro", carro);
        startActivity(intent);
    }

    @Subscribe
    public void lidaComClickLong(final CarroClickLongEvent event){
        new AlertDialog.Builder(this)
                .setTitle(event.carro.getModelo())
                .setMessage("O que você quer fazer ?")
                .setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        carroDao.delete(event.carro);

                        carregaLista();
                    }
                })
                .setNegativeButton("Recomendar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new WebClient().recomenda(event.carro);
                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        carregaLista();


    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void carregaLista() {

        List<Carro> carros = carroDao.loadAll();


        listaCarros.setLayoutManager(new LinearLayoutManager(this));
        listaCarros.setAdapter(new CarroAdapter(carros));
    }

    @OnClick(R.id.fab)
    public void clickNoFAB() {

        Intent intent = new Intent(this, FormularioActivity.class);
        startActivity(intent);
    }
}
