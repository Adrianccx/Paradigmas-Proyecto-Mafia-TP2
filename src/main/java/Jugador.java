import java.util.Objects;

public class Jugador {

    private Rol rol;
    private boolean protegido;
    private Jugador ultimoInvestigado;
    private boolean vivo = true;

    public Jugador(Rol rol){
        this.rol = Objects.requireNonNull(rol, "El rol no puede ser nulo");
    }

    public boolean estaVivo() {
        return this.vivo;
    }

    public void eliminar() {
        this.vivo = false;
    }

    public Rol getRol() {
        return this.rol;
    }

    public boolean estaProtegido() {
        return this.protegido;
    }

    public Rol revelarRol(Jugador solicitante) {
        if (!this.vivo || this == solicitante) {
            return this.rol;
        }
        return null;
    }

    public void proteger() {
        protegido = true;
    }

    public void accionNocturna(Jugador jugador) {
        if (!this.vivo) {
            throw new IllegalStateException("Un jugador eliminado no puede realizar acciones.");
        }
        rol.accionNocturna(jugador);
    }

    public boolean esMafia() {
        return this.rol.esMafia();
    }

    public String investigar(Jugador objetivo) {
        if (this.ultimoInvestigado == objetivo) {
            throw new IllegalStateException("No podés investigar al mismo jugador dos noches consecutivas.");
        }

        this.ultimoInvestigado = objetivo;

        if (objetivo.getRol() instanceof Padrino) {
            return "Ciudadano";
        }
        else if (objetivo.esMafia()) {
            return "Mafia";
        }
        else {
            return "Ciudadano";
        }
    }
}