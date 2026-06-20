import java.util.Collection;
import java.util.List;

public class Jugador {
    private final Rol rol;
    private boolean protegido;
    private Jugador ultimoInvestigado;
    private boolean vivo = true;

    public Jugador(Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("Un jugador no puede existir sin un rol asignado.");
        }
        this.rol = rol;
    }

    public boolean estaVivo() { return this.vivo; }
    public void eliminar() { this.vivo = false; }
    public boolean puedeSerEliminado() { return !this.estaProtegido(); }
    public boolean estaProtegido() { return this.protegido; }
    public void proteger() { this.protegido = true; }

    public Rol revelarRol(Jugador solicitante) {
        if (!this.vivo || this == solicitante) {
            return this.rol;
        }
        return null;
    }

    public void accionNocturna(Jugador jugador) {
        if (!this.vivo) {
            throw new IllegalStateException("Un jugador eliminado no puede realizar acciones.");
        }
        rol.accionNocturna(jugador);
    }

    public Bando investigar(Jugador objetivo) {
        if (this.ultimoInvestigado == objetivo) {
            throw new IllegalStateException("No podés investigar al mismo jugador dos noches consecutivas.");
        }
        this.ultimoInvestigado = objetivo;
        return objetivo.getBandoInvestigacion(); 
    }

    public Bando getBandoReal() { return this.rol.getBandoReal(); }
    public Bando getBandoInvestigacion() { return this.rol.getBandoInvestigacion(); }
    public String getNombreRol() { return this.rol.nombre(); }


    public List<Jugador> obtenerEquipo(Collection<Jugador> jugadoresVivos) {
        return this.rol.obtenerEquipo(this, jugadoresVivos);
    }

}