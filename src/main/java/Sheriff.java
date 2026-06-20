public class Sheriff extends Rol {
    @Override
    public Bando getBando() {
        return new BandoCiudadano();
    }
}
