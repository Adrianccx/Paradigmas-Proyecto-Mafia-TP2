public class Ciudadano extends Rol {
    @Override
    public Bando getBandoReal() {
        return new BandoCiudadano();
    }
}

