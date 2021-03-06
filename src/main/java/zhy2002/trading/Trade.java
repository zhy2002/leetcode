package zhy2002.trading;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Trade {

    private final Chart chart;
    private final int buyDayIndex;
    private final double buyPrice;
    private final int volume;

    private int sellDayIndex = -1;
    private double sellPrice = -1;
    private double minPrice;
    private double maxPrice;

    @Setter
    private double pastPerformance;


    public Trade(Chart chart, int buyDayIndex, double buyPrice, int volume) {
        this.chart = chart;
        this.buyDayIndex = buyDayIndex;
        this.buyPrice = this.maxPrice = this.minPrice = buyPrice;
        this.volume = volume;
    }

    public void complete(int sellDayIndex, double sellPrice) {
        this.sellDayIndex = sellDayIndex;
        this.sellPrice = sellPrice;
    }

    public void updatePrice(double price) {
        if (price > maxPrice) {
            maxPrice = price;
        }
        if (price < minPrice) {
            minPrice = price;
        }
    }

    @Override
    public String toString() {
        if (sellDayIndex >= 0) {
            return String.format("Bought %s on %s and Sold on %s with profit %.2f hold days %d",
                    chart.getSymbol(),
                    chart.getCandle(buyDayIndex).date,
                    chart.getCandle(sellDayIndex).date,
                    (sellPrice - buyPrice) / buyPrice,
                    sellDayIndex - buyDayIndex);
        } else {
            return String.format("Buy %s on %s at %.2f with min: %.2f",
                    chart.getSymbol(),
                    chart.getCandle(buyDayIndex).date,
                    getBuyPrice(),
                    getMinPrice());
        }
    }

    public boolean isComplete() {
        return this.sellPrice != -1;
    }

    public double getDrawDown() {
        return (buyPrice - minPrice) / buyPrice;
    }

    public Trade scale(double fund) {
        Trade result = new Trade(chart, buyDayIndex, buyPrice, (int) (fund / buyPrice));
        result.complete(sellDayIndex, sellPrice);
        result.minPrice = minPrice;
        result.maxPrice = maxPrice;
        result.pastPerformance = pastPerformance;
        return result;
    }
}
