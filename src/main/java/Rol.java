public interface Rol {
    void ejecutarAccionNocturna(Jugador objetivo, RegistroNocturno registro);
    Bando obtenerBandoReal();
    Bando obtenerBandoParaDetective();

    // para ordenar prioridad 

    int obtenerPrioridadNocturna();
}
