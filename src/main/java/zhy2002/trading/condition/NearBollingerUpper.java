package zhy2002.trading.condition;

import lombok.AllArgsConstructor;
import zhy2002.trading.Chart;

@AllArgsConstructor
public class NearBollingerUpper implements TradeCondition {

    private final double atrRatio;
    private final int window;

    public NearBollingerUpper(double atrRatio) {
        this(atrRatio, 1);
    }

    public NearBollingerUpper() {
        this(0);
    }

    @Override
    public boolean isMet(Chart chart, int index) {
        var atr = chart.getATR();
        var band = chart.getBollingerBand();
        for (int i = 0; i < window; i++) {
            if (chart.getCandle(index - i).getClose() < band.getUpper(index - i) + atr.get(index - i) * atrRatio) {
                return false;
            }
        }
        return true;
    }
}
