package br.com.caelum.recomendacao.event;

import br.com.caelum.recomendacao.model.Carro;

/**
 * Created by matheusbrandino on 2/10/17.
 */
public class CarroClickLongEvent {
    public final Carro carro;

    public CarroClickLongEvent(Carro carro) {
        this.carro = carro;
    }
}
