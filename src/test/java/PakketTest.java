import model.Pakket;
import model.PakkettenBus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PakketTest {
    private PakkettenBus testBus;

    @Test
    public void laadPakketMetNegatieveWaardeWeigeren() {
        maakBus();
        Pakket foutPakket = new Pakket("Aike", "Texas spel", -3);
        testBus.laadPakket(foutPakket);
        assertEquals(0, testBus.getLading().size());
    }

    @Test
    public void laadZwaarPakket() {
        maakBus();
        Pakket pakket1 = new Pakket("Aike", "Texas spel", 20);
        Pakket pakket2 = new Pakket("Aike", "Texas spel", 25);
        testBus.laadPakket(pakket1);
        testBus.laadPakket(pakket2);
        assertEquals(1, testBus.getLading().size());
    }

    @Test
    public void testTotaalGewicht() {
        maakBus();
        Pakket pakket1 = new Pakket("Aike", "Texas spel", 10);
        Pakket pakket2 = new Pakket("Aike", "Texas spel", 15);
        Pakket pakket3 = new Pakket("Aike", "Texas spel", 12);
        Pakket pakket4 = new Pakket("Aike", "Texas spel", 3);
        testBus.laadPakket(pakket1);
        testBus.laadPakket(pakket2);
        testBus.laadPakket(pakket3);
        testBus.laadPakket(pakket4);
        assertEquals(40, testBus.getTotaalGewicht());
        assertEquals(4, testBus.getLading().size());
    }

    @Test
    public void testOverTotaalGewicht() {
        maakBus();
        Pakket pakket1 = new Pakket("Aike", "Texas spel", 10);
        Pakket pakket2 = new Pakket("Aike", "Texas spel", 15);
        Pakket pakket3 = new Pakket("Aike", "Texas spel", 12);
        Pakket pakket4 = new Pakket("Aike", "Texas spel", 4);
        testBus.laadPakket(pakket1);
        testBus.laadPakket(pakket2);
        testBus.laadPakket(pakket3);
        // This would put it over maximumweight (40)
        testBus.laadPakket(pakket4);
        assertEquals(37, testBus.getTotaalGewicht());
        assertEquals(3, testBus.getLading().size());
    }


    private void maakBus() {
        testBus = new PakkettenBus("Rode bus", 40);
    }

}
