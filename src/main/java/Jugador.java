import java.util.List;
import java.util.Objects;

public class Jugador {

    private Rol rol;
    private boolean protegido;
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

    public boolean esMafia() {
        return this.rol.esMafia();
    }

    public boolean puedeSerEliminado(){
        return this.vivo && !this.protegido;
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

    public String recibirInvestigacion(){
        if(!this.vivo){
            throw new IllegalStateException(
                    "Un se puede investigar a un jugador eliminado"
            );
        }
        return this.rol.resultadoDeInvestigacion();
    }

    public String investigar(Jugador objetivo) {
        if (!this.vivo) {
            throw new IllegalStateException(
                    "Un jugador eliminado no puede realizar acciones."
            );
        }
        return this.rol.investigar(objetivo);
    }

    public void recibirComplices(List<Jugador> complices){
        this.rol.recibirComplices(complices);
    }

    public List<Jugador> obtenerComplices(){
        return this.rol.obtenerComplices();
    }
}