import java.util.ArrayList;
import java.util.List;

public class FabricaMazo {
    public Mazo generarMazoCompleto(AjustePartida ajustes) {
        List<Rol> listaRoles = new ArrayList<>(ajustes.extraerEspecialesPermitidos());
        int restantes = ajustes.obtenerCantidadJugadores() - listaRoles.size();
        int mafiaBase = Math.max(1, ajustes.obtenerCantidadJugadores() / 4);
        
        int mafiaAgregada = 0;
        for (int i = 0; i < restantes; i++) {
            long mafiasEnEspeciales = listaRoles.stream().filter(r -> r.obtenerBandoReal() == Bando.MAFIA).count();
            if (mafiasEnEspeciales + mafiaAgregada < mafiaBase) {
                listaRoles.add(new Mafioso());
                mafiaAgregada++;
            } else {
                listaRoles.add(new Ciudadano());
            }
        }
        return new Mazo(listaRoles);
    }
}
