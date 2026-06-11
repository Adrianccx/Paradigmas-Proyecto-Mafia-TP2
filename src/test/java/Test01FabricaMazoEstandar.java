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

        int cantidadCiudadano = 0;
        int cantidadMafioso= 0;
        int cantidadDetective = 0;

        for(Rol carta: mazoGenerado){
            if(carta instanceof Ciudadano){
                cantidadCiudadano++;
            }
            else if(carta instanceof Mafioso){
                cantidadMafioso++;
            }
            else if(carta instanceof Detective){
                cantidadDetective++;
            }
        }
        assertEquals(1, cantidadMafioso);
        assertEquals(1, cantidadDetective);
        assertEquals(3, cantidadCiudadano);
    }
    @Test

    public void testGenerarRolesParaSeisJugadores(){
        //Arrange
        FabricaMazo fabrica = new FabricaMazoEstandar();

        //Act
        List<Rol> mazoGenerado = fabrica.generarCartas(6);

        //Assert
        assertEquals(6,mazoGenerado.size());

        int cantidadCiudadano = 0;
        int cantidadMafioso= 0;
        int cantidadDetective = 0;

        for(Rol carta: mazoGenerado){
            if(carta instanceof Ciudadano){
                cantidadCiudadano++;
            }
            else if(carta instanceof Mafioso){
                cantidadMafioso++;
            }
            else if(carta instanceof Detective){
                cantidadDetective++;
            }
        }
        assertEquals(2, cantidadMafioso);
        assertEquals(1, cantidadDetective);
        assertEquals(3, cantidadCiudadano);
    }
}

