public class Jugador {

    private final String nombre;
    private Rol rol;


    public Jugador(){
        this.nombre = "";
    }

    public Jugador(String nombre){
        this.nombre = nombre;

    }

    public String getNombre(){
        return  nombre;
    }

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

    public Bando bandoAnteDetective(){
        return  rol.bandoAnteDetective();
    }

    public int prioridadNocturna(){
        return rol.prioridadNocturna();
    }

    public void accionNocturna(FaseNocturna faseNocturna){
        rol.accionNocturna(this, faseNocturna);
    }

    public void accionDiurna(FaseDiurna faseDiurna){
        rol.accionDiurna(this, faseDiurna);
    }


}