package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestelBusTest {

    private BestelBus<Pakket> testBus;

    @BeforeEach
    void maakTestBusAan() {
        // Arrange
        testBus = new BestelBus<>("Rode bus", 30);
    }

    @Test
    void laadPakket() {
        // Activate
        testBus.laadPakket(new Pakket("Vincent", "Framework Laptop", 10));

        // Assert
        assertEquals(1, testBus.getLading().size());
        assertEquals(10, testBus.getTotaalGewicht());
    }
}