package br.com.caelum.recomendacao;

import javax.inject.Singleton;

import br.com.caelum.recomendacao.application.CarroApplication;
import br.com.caelum.recomendacao.model.CarroDao;
import br.com.caelum.recomendacao.model.DaoSession;
import dagger.Module;
import dagger.Provides;

/**
 * Created by matheusbrandino on 2/10/17.
 */
@Module
@Singleton
public class Injetor {

    @Provides
    public CarroApplication getCarroApplication() {
        return CarroApplication.getInstance();
    }

    @Provides
    @Singleton
    public CarroDao getCarroDao(CarroApplication application) {

        DaoSession session = application.getSession();
        CarroDao carroDao = session.getCarroDao();

        return carroDao;
    }
}
