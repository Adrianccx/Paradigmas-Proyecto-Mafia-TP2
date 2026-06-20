public class Ciudadano extends Rol {
    @Override
    public Bando getBando() {
        return new BandoCiudadano();
    }
}

