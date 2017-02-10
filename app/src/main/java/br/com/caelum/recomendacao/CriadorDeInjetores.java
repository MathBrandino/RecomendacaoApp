package br.com.caelum.recomendacao;

import javax.inject.Singleton;

import br.com.caelum.recomendacao.activity.MainActivity;
import dagger.Component;

/**
 * Created by matheusbrandino on 2/10/17.
 */

@Component(modules ={Injetor.class})
@Singleton
public interface CriadorDeInjetores {
    void inject(MainActivity activity);
}
