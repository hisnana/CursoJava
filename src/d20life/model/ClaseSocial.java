package d20life.model;

/** enum con datos adjuntos (modificadores + dinero extra). */
public enum ClaseSocial {
    BAJA("Baja",  -2,   0),
    MEDIA("Media", 0,  40),
    ALTA("Alta",  +2, 100);

    private final String etiqueta;
    private final int modRecursos;
    private final int dineroInicialExtra;

    ClaseSocial(String etiqueta, int modRecursos, int dineroInicialExtra) {
        this.etiqueta = etiqueta;
        this.modRecursos = modRecursos;
        this.dineroInicialExtra = dineroInicialExtra;
    }

    public String getEtiqueta() { return etiqueta; }
    public int getModRecursos() { return modRecursos; }
    public int getDineroInicialExtra() { return dineroInicialExtra; }
}
