public class ResultadoNoche {
    private final Jugador victima;
    private final Jugador protegido;
    private final Jugador investigado;
    private final Bando bandoRevelado;

    public ResultadoNoche(Jugador victima, Jugador protegido, Jugador investigado, Bando bandoRevelado) {
        this.victima = victima;
        this.protegido = protegido;
        this.investigado = investigado;
        this.bandoRevelado = bandoRevelado;
    }
    public Jugador getVictima() { return victima; }
    public Jugador getProtegido() { return protegido; }
    public Jugador getInvestigado() { return investigado; }
    public Bando getBandoRevelado() { return bandoRevelado; }
}