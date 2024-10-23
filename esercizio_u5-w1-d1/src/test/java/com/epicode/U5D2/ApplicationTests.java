package com.epicode.U5D2;

import com.epicode.U5D2.entities.Drink;
import com.epicode.U5D2.entities.Order;
import com.epicode.U5D2.entities.Pizza;
import com.epicode.U5D2.entities.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ApplicationTests {

    private double seatPrice = 2.00;

    private Table table;
    private Order order;

    @BeforeEach
    public void setUp() {
        table = new Table(1, 4, true, seatPrice);
        order = new Order(1, table);
    }

    @Test
    public void testCalcoloTotale() {
        Pizza margherita = new Pizza("Margherita", new ArrayList<>(), false);
        margherita.setPrice(5.00);
        Drink lemonade = new Drink("Lemonade", 128, 1.29);

        order.addItem(margherita);
        order.addItem(lemonade);


        double expectedTotal = 5.00 + 1.29 + seatPrice;
        assertEquals(expectedTotal, order.getTotal(), 0.01, "Il totale calcolato è errato");
    }

    @Test
    public void testCreazioneOrdineNumeroCopertiTroppoAlto() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Order(4, table);
        });
    }
}


