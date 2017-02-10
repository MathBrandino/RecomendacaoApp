package br.com.caelum.recomendacao.webservices;

import org.greenrobot.eventbus.EventBus;

import br.com.caelum.recomendacao.event.CarroRecebidoEvent;
import br.com.caelum.recomendacao.model.Carro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matheusbrandino on 2/10/17.
 */

public class WebClient {

    public void recomenda(Carro carro){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.3.228:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CarroService carroService = retrofit.create(CarroService.class);
        Call<Carro> call = carroService.recomenda(carro);

        call.enqueue(new Callback<Carro>() {
            @Override
            public void onResponse(Call<Carro> call, Response<Carro> response) {
                EventBus.getDefault().post(new CarroRecebidoEvent(response.body()));
            }

            @Override
            public void onFailure(Call<Carro> call, Throwable t) {

            }
        });
    }
}
