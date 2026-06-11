public class Jugador {
    private final String nombre;
    private Rol rol;
    private boolean vivo = true;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() { return nombre; }
    public void asignarRol(Rol rol) { this.rol = rol; }
    public Rol obtenerRol() { return rol; }
    public boolean estaVivo() { return vivo; }
    public void eliminar() { this.vivo = false; }
}