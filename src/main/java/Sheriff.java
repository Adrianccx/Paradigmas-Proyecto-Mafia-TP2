public class Sheriff extends Rol {
    @Override
    public Bando getBandoReal() {
        return Bando.CIUDADANO;
    }
}
