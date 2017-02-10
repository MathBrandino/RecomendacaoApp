package br.com.caelum.recomendacao.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.Serializable;

import br.com.caelum.recomendacao.R;
import br.com.caelum.recomendacao.application.CarroApplication;
import br.com.caelum.recomendacao.model.Carro;
import br.com.caelum.recomendacao.model.CarroDao;
import br.com.caelum.recomendacao.model.DaoSession;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormularioActivity extends AppCompatActivity {

    @BindView(R.id.formulario_ano)
    EditText ano;

    @BindView(R.id.formulario_marca)
    EditText marca;

    @BindView(R.id.formulario_modelo)
    EditText modelo;

    @BindView(R.id.formulario_cor)
    EditText cor;

    private Carro carro = new Carro();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("carro")) {
            carro = (Carro) intent.getSerializableExtra("carro");
            preenchoFormulario(carro);
        }


    }

    private void preenchoFormulario(Carro carro) {
        ano.setText(carro.getAno().toString());
        cor.setText(carro.getCor());
        modelo.setText(carro.getModelo());
        marca.setText(carro.getMarca());
    }

    @OnClick(R.id.formulario_salvar)
    public void salva() {
        Carro carro = getCarro();

        CarroApplication application = (CarroApplication) getApplication();
        DaoSession session = application.getSession();
        CarroDao carroDao = session.getCarroDao();

        if (carro.getId() == null) {
            carroDao.save(carro);
        } else {
            carroDao.update(carro);
        }

        finish();
    }

    public Carro getCarro() {
        carro.setAno(Integer.valueOf(ano.getText().toString()));
        carro.setMarca(marca.getText().toString());
        carro.setModelo(modelo.getText().toString());
        carro.setCor(cor.getText().toString());
        return carro;
    }
}
