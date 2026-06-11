public class Medico implements Rol {
    public void ejecutarAccionNocturna(Jugador obj, RegistroNocturno reg) { reg.registrarProtegido(obj); }
    public Bando obtenerBandoReal() { return Bando.CIUDADANO; }
    public Bando obtenerBandoParaDetective() { return Bando.CIUDADANO; }

    @Override public int obtenerPrioridadNocturna() { return 3; }
}
