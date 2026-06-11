public class Detective implements Rol {
    public void ejecutarAccionNocturna(Jugador obj, RegistroNocturno reg) { reg.registrarInvestigacion(obj); }
    public Bando obtenerBandoReal() { return Bando.CIUDADANO; }
    public Bando obtenerBandoParaDetective() { return Bando.CIUDADANO; }

    @Override public int obtenerPrioridadNocturna() { return 2; }
}
