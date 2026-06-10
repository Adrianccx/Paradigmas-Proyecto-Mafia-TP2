public class Jugador {

    private Rol rol;

    public Rol getRol() {
        return this.rol;
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
}