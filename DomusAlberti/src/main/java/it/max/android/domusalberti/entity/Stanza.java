package it.max.android.domusalberti.entity;

public class Stanza {
    private String nomeStanza;
    private float  temperatura;
    private int    imgTemperatura;
    private float  umidita;
    private int    imgUmidita;

    public Stanza(String nomeStanza,
                  float  temperatura,
                  float  umidita,
                  int    imgTemperatura,
                  int    imgUmidita) {
        super();
        this.nomeStanza = nomeStanza;
        this.temperatura = temperatura;
        this.umidita = umidita;
        this.imgTemperatura = imgTemperatura;
        this.imgUmidita = imgUmidita;
    }

    public String getNomeStanza() {
        return nomeStanza;
    }

    public void setNomeStanza(String nomeStanza) {
        this.nomeStanza = nomeStanza;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public int getImgTemperatura() {
        return imgTemperatura;
    }

    public void setImgTemperatura(int imgTemperatura) {
        this.imgTemperatura = imgTemperatura;
    }

    public float getUmidita() {
        return umidita;
    }

    public void setUmidita(float umidita) {
        this.umidita = umidita;
    }

    public int getImgUmidita() {
        return imgUmidita;
    }

    public void setImgUmidita(int imgUmidita) {
        this.imgUmidita = imgUmidita;
    }
}
