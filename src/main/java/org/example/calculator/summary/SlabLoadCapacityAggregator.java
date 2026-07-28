package org.example.calculator.summary;

import org.example.calculator.paragraph_6.LoadsCalculator;
import org.example.context.BridgeContext;
import org.example.dto.LoadCapacityRow;

import java.util.ArrayList;
import java.util.List;

public class SlabLoadCapacityAggregator {

    public static List<LoadCapacityRow> aggregateSlabResults(BridgeContext ctx) {
        List<LoadCapacityRow> rows = new ArrayList<>();

        double lambdaSlab = LoadsCalculator.getLambda(ctx, true);

        // 1. Изгибающий момент
        rows.add(createRow("Изгибающий момент", lambdaSlab, 0.5, ctx.k_monolithic, ctx.K_monolithic, ctx.ballastThickness));

        // 2. Поперечная сила
        rows.add(createRow("Поперечная сила", lambdaSlab, 0.5, ctx.k_shear_monolithic, ctx.K_shear_monolithic, ctx.ballastThickness));

        // 3. Выносливость арматуры
        rows.add(createRow("Выносливость арматуры", lambdaSlab, 0.5, ctx.k_fatigue_slab_rebar, ctx.K_fatigue_slab_rebar, ctx.ballastThickness));

        // 4. Выносливость бетона
        rows.add(createRow("Выносливость бетона", lambdaSlab, 0.5, ctx.k_fatigue_slab_concrete, ctx.K_fatigue_slab_concrete, ctx.ballastThickness));

        return rows;
    }

    private static LoadCapacityRow createRow(String type, double lambda, double alpha, double k, double kClass, double hb) {
        LoadCapacityRow row = new LoadCapacityRow();
        row.setCalculationType(type);
        row.setLambda(lambda);
        row.setAlpha(alpha);
        row.setK(k);
        row.setKs(getKcByBallast(hb));
        row.setKClass(kClass);

        // Реальные расчеты классов нагрузок по методичке
        row.setLoadClassII(getCategoryIIClass(lambda, alpha));
        row.setLoadClass27(calculateWagonClass(27.0, hb));
        row.setLoadClass25(calculateWagonClass(25.0, hb));

        return row;
    }

    // --- Вспомогательные методы из методички (без изменений) ---

    private static double getKcByBallast(double hb) {
        if (hb < 0.30) return 19.1;
        if (hb < 0.40) return 19.0;
        if (hb < 0.50) return 18.7;
        if (hb < 0.60) return 18.4;
        if (hb < 0.70) return 18.3;
        if (hb < 0.80) return 18.2;
        if (hb < 0.90) return 18.2;
        if (hb < 1.00) return 18.1;
        return 18.1;
    }

    private static double getCategoryIIClass(double lambda, double alpha) {
        double[] lambdas = {10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 25.0, 30.0};
        double[] classes = {9.56, 9.82, 9.87, 9.91, 9.81, 9.94, 10.68, 10.82};

        if (lambda <= lambdas[0]) return classes[0];
        if (lambda >= lambdas[lambdas.length - 1]) return classes[classes.length - 1];

        for (int i = 0; i < lambdas.length - 1; i++) {
            if (lambda >= lambdas[i] && lambda <= lambdas[i + 1]) {
                double ratio = (lambda - lambdas[i]) / (lambdas[i + 1] - lambdas[i]);
                return classes[i] + ratio * (classes[i + 1] - classes[i]);
            }
        }
        return 9.56;
    }

    private static double calculateWagonClass(double axleLoadTons, double hb) {
        double k0_1t;
        if (hb < 0.30) {
            k0_1t = 0.40;
        } else if (hb < 0.50) {
            k0_1t = 0.35;
        } else if (hb < 0.75) {
            k0_1t = 0.32;
        } else {
            k0_1t = 0.29;
        }
        return axleLoadTons * k0_1t;
    }
}