public class Ciudadano implements Rol {
    public void ejecutarAccionNocturna(Jugador obj, RegistroNocturno reg) {} // No actúa de noche
    public Bando obtenerBandoReal() { return Bando.CIUDADANO; }
    public Bando obtenerBandoParaDetective() { return Bando.CIUDADANO; }

    @Override public int obtenerPrioridadNocturna() { return 99; }
}
