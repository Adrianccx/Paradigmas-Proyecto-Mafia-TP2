public class Detective extends Rol {

    private Jugador ultimoInvestigado;

    @Override
    public String investigar(Jugador objetivo){
        if(this.ultimoInvestigado == objetivo){
            throw new IllegalStateException(
                    "No podes investigar al mismo jugador dos noches consecutivas."
            );
        }
        this.ultimoInvestigado = objetivo;
        return objetivo.recibirInvestigacion();
    }

    @Override
    public boolean esMafia() {
        return false;
    }
}
