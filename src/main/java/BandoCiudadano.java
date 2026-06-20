public class BandoCiudadano implements Bando {

    @Override
    public boolean equals(BandoCiudadano bando) {
        return true;
    }

    @Override
    public boolean equals(BandoMafia bando) {
        return false;
    }
}
