package br.com.caelum.recomendacao.application;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import br.com.caelum.recomendacao.CriadorDeInjetores;
import br.com.caelum.recomendacao.DaggerCriadorDeInjetores;
import br.com.caelum.recomendacao.model.Carro;
import br.com.caelum.recomendacao.model.DaoMaster;
import br.com.caelum.recomendacao.model.DaoSession;

/**
 * Created by matheusbrandino on 2/10/17.
 */

public class CarroApplication extends Application {

    private static CarroApplication app;
    private CriadorDeInjetores component;

    public static CarroApplication getInstance() {

        return app;
    }


    private DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        Database db = new DaoMaster.DevOpenHelper(this, "Carros").getWritableDb();
        session = new DaoMaster(db).newSession();

        component = DaggerCriadorDeInjetores.builder().build();
    }

    public DaoSession getSession() {
        return session;
    }

    public CriadorDeInjetores getComponent() {
        return component;
    }
}
