public class BandoMafia implements Bando {    @Override
    public boolean equals(BandoCiudadano bando) {
        return false;
    }

    @Override
    public boolean equals(BandoMafia bando) {
        return true;
    }
}
