public class FaseNocturna implements Fase {
    
    private EstadoPartida estado;
    private Jugador elegidoPorMafia;

    public FaseNocturna(EstadoPartida estado) {
        this.estado = estado;
    }

    public void registrarVictimaMafia(Jugador victima) {
        if (victima == null) {
            throw new IllegalArgumentException("La víctima no puede ser nula.");
        }

        // 1. Verificación de vida: un fantasma no puede morir dos veces
        if (estado != null && !estado.estaVivo(victima)) {
            throw new IllegalArgumentException("El jugador seleccionado ya esta eliminado");
        }
        
        // 2. Verificación de bando: evita el "fuego amigo" usando el Enum Bando
        if (victima.getBando().equals(new BandoMafia())) {
            throw new IllegalArgumentException("La Mafia no puede atacarse a si misma");
        }
        
        // Si pasa los filtros, se confirma el objetivo de la noche
        this.elegidoPorMafia = victima;
    }

    @Override
    public void ejecutarFase() {
        if (elegidoPorMafia == null) return;

        if (elegidoPorMafia.estaDesprotegido()) {
            if (estado != null) {
                estado.eliminarJugador(elegidoPorMafia);
            } else {
                elegidoPorMafia.eliminar();
            }
        }

        this.elegidoPorMafia = null;
    }

    public Jugador getElegidoPorMafia() {
        return this.elegidoPorMafia;
    }
}