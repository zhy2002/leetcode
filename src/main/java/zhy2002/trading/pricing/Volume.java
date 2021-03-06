package zhy2002.trading.pricing;

import zhy2002.trading.Chart;

public class Volume implements PriceDecider {
    @Override
    public double decide(Chart chart, int index) {
        return chart.getCandle(index).getVolume();
    }
}
