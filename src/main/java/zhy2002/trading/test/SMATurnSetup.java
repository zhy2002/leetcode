package zhy2002.trading.test;

import zhy2002.trading.condition.And;
import zhy2002.trading.condition.Or;
import zhy2002.trading.condition.SMATurn;
import zhy2002.trading.condition.SMATurnSell;
import zhy2002.trading.condition.TrailingStopLoss;
import zhy2002.trading.strategy.ParameterCrossProduct;
import zhy2002.trading.strategy.StrategyGeneratorV2;
import zhy2002.trading.strategy.StrategyPair;

import java.util.List;

public class SMATurnSetup extends BackTestSetup {

    @Override
    public List<StrategyPair> createStrategyPairs() {
        var buys = new StrategyGeneratorV2(
                "SMATurn",
                new ParameterCrossProduct()
                        .withParameter("smaPeriods", new int[]{9, 14, 20})
                        .withParameter("downWindow", new int[]{9, 10, 12, 15})
                        .withParameter("upWindow", new int[]{3, 5, 6})
                        .withParameter("downRate", new double[]{0, -0.005, -0.007, -0.01, -0.011})
                        .withParameter("upRate", new double[]{0, 0.005, 0.007, 0.01, 0.011}),
                ps -> new And(
                        new SMATurn(
                                ps.getInt("smaPeriods"),
                                ps.getInt("downWindow"),
                                ps.getInt("upWindow"),
                                ps.getDouble("downRate"),
                                ps.getDouble("upRate")
                        )
                ));
        var sells = new StrategyGeneratorV2(
                "TrailingStopLoss",
                new ParameterCrossProduct()
                        .withParameter("percent", new double[]{0.97, 0.96}),
                ps -> new Or(
                        new SMATurnSell(),
                        new TrailingStopLoss(ps.getDouble("percent"), Integer.MAX_VALUE)
                ));
        return allStrategyPairs(buys, sells);
    }
}
