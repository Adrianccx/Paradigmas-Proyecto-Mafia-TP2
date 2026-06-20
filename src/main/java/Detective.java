public class Detective extends Rol {
    @Override
    public Bando getBando() {
        return new BandoCiudadano();
    }
}
