public class Jugador {

    private Rol rol;
    private boolean protegido;

    public Rol getRol() {
        return this.rol;
    }

    public boolean estaProtegido() {
        return this.protegido;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Rol revelarRol(Jugador solicitante) {
        if (this == solicitante) {
            return this.rol;
        }
        return null;
    }

    public void proteger() {
        protegido = true;
    }
}