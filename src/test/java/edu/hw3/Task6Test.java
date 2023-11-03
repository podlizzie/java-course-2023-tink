package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task6Test {
    @Test
    @DisplayName("Test that add stock to market")
    public void testThatAddStockToMarket() {
        StockMarker stockMarket = new StockMarker();
        Stock stock = new Stock("Газпром", 100);

        stockMarket.add(stock);

        assertThat(stockMarket.mostValuableStock()).isEqualTo(stock);
    }

    @Test
    @DisplayName("Test that remove stock from market")
    public void testThatRemoveStockFromMarket() {
        StockMarker stockMarket = new StockMarker();
        Stock stock = new Stock("Лукойл", 100);
        stockMarket.add(stock);

        stockMarket.remove(stock);

        assertThat(stockMarket.mostValuableStock()).isNull();
    }

    @Test
    @DisplayName("Test that mostValuableStock returns null for empty market")
    public void testThatMostValuableStockReturnsNullForEmptyMarket() {
        StockMarker stockMarket = new StockMarker();

        assertThat(stockMarket.mostValuableStock()).isNull();
    }

    @Test
    @DisplayName("Test that mostValuableStock returns most valuable stock")
    public void testThatMostValuableStockReturnsMostValuableStock() {
        StockMarker stockMarket = new StockMarker();
        Stock stock1 = new Stock("Газпром", 100);
        Stock stock2 = new Stock("Лукойл", 200);
        Stock stock3 = new Stock("Роснефть", 150);

        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        assertThat(stockMarket.mostValuableStock()).isEqualTo(stock2);
    }

}
