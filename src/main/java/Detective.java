public class Detective extends Rol {
    @Override
    public Bando getBandoReal() {
        return new BandoCiudadano();
    }
}
