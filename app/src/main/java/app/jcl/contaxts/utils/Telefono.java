package app.jcl.contaxts.utils;

/**
 * Created by usuario on 22/10/17.
 */

class Telefono {
    private int telefono;
    private int cod_area;
    private int cod_pais;

    public Telefono(int cod_pais, int telefono, int cod_area) {
        this.telefono = telefono;
        this.cod_area = cod_area;
        this.cod_pais = cod_pais;
    }

    public Telefono(int telefono) {
    this.telefono = telefono;
    this.cod_area = 11;
    this.cod_pais = 54;
    }

    public String normalizado() {
        return "+" + cod_pais +"9" + cod_area + telefono;
    }


}
