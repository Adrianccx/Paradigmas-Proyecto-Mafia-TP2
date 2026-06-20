public class Sheriff extends Rol {
    @Override
    public Bando getBandoReal() {
        return new BandoCiudadano();
    }
}
