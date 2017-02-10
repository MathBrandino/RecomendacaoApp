package br.com.caelum.recomendacao.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by matheusbrandino on 2/9/17.
 */
@Entity
public class Carro implements Serializable {

    private static final long serialVersionUID = 536871008;

    @Id
    private Long id;
    @NotNull
    private String modelo;
    private Integer ano;
    private String marca;
    private String cor;


    @Generated(hash = 7690906)
    public Carro(Long id, @NotNull String modelo, Integer ano, String marca,
            String cor) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.marca = marca;
        this.cor = cor;
    }

    @Generated(hash = 45579899)
    public Carro() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
