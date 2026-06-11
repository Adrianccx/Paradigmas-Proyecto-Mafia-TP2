
public class Mafioso implements Rol {
    public void ejecutarAccionNocturna(Jugador obj, RegistroNocturno reg) { reg.registrarVotoMafia(obj); }
    public Bando obtenerBandoReal() { return Bando.MAFIA; }
    public Bando obtenerBandoParaDetective() { return Bando.MAFIA; }

    @Override public int obtenerPrioridadNocturna() { return 1; }
}