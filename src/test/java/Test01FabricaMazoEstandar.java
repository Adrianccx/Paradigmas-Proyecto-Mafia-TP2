import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01FabricaMazoEstandar {
    @Test

    public void testGenerarRolesParaCincoJugadores(){

        //Arrange
        FabricaMazo fabrica = new FabricaMazoEstandar();

        //Act
        List<Rol> mazoGenerado = fabrica.generarCartas(5);

        //Assert
        assertEquals(5,mazoGenerado.size());
        assertEquals(3, contarRoles(mazoGenerado, "Ciudadano"));
        assertEquals(1, contarRoles(mazoGenerado, "Mafioso"));
        assertEquals(1, contarRoles(mazoGenerado, "Detective"));
    }
    @Test

    public void testGenerarRolesParaSeisJugadores(){
        //Arrange
        FabricaMazo fabrica = new FabricaMazoEstandar();

        //Act
        List<Rol> mazoGenerado = fabrica.generarCartas(6);

        //Assert
        assertEquals(6,mazoGenerado.size());
        assertEquals(2, contarRoles(mazoGenerado, "Mafioso"));
        assertEquals(1, contarRoles(mazoGenerado, "Detective"));
        assertEquals(3, contarRoles(mazoGenerado, "Ciudadano"));
    }

    @Test
    public void testGenerarRolesParaSieteJugadores() {
        //Arrange
        FabricaMazo fabrica = new FabricaMazoEstandar();

        //Act
        List<Rol> mazoGenerado = fabrica.generarCartas(7);

        //Assert
        assertEquals(2, contarRoles(mazoGenerado, "Mafioso"));
        assertEquals(1, contarRoles(mazoGenerado, "Detective"));
        assertEquals(1, contarRoles(mazoGenerado, "Medico"));
        assertEquals(3, contarRoles(mazoGenerado, "Ciudadano"));
    }

    @Test
    public void testGenerarRolesParaDiezJugadores() {
        //Arrange
        FabricaMazo fabrica = new FabricaMazoEstandar();

        //Act
        List<Rol> mazoGenerado = fabrica.generarCartas(10);

        //Assert
        assertEquals(10, mazoGenerado.size());
        assertEquals(2, contarRoles(mazoGenerado, "Mafioso"));
        assertEquals(1, contarRoles(mazoGenerado, "Padrino"));;
        assertEquals(1, contarRoles(mazoGenerado, "Detective"));;
        assertEquals(1, contarRoles(mazoGenerado, "Medico"));;
        assertEquals(1, contarRoles(mazoGenerado, "Sheriff"));;
        assertEquals(4, contarRoles(mazoGenerado, "Ciudadano"));;
    }

    private long contarRoles(List<Rol> roles, String nombre){
        return roles.stream()
                .filter(rol -> rol.nombre().equals(nombre))
                .count();
    }
}

