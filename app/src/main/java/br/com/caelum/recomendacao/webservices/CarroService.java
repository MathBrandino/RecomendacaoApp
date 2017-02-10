package br.com.caelum.recomendacao.webservices;

import br.com.caelum.recomendacao.model.Carro;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by matheusbrandino on 2/10/17.
 */

public interface CarroService {

    @POST("recomendacao")
    Call<Carro> recomenda(@Body Carro carro);
}
