package org.example.controller;

import org.example.context.BridgeContext;
import org.example.dto.LoadCapacityRow;
import org.example.calculator.summary.SlabLoadCapacityAggregator;
import org.example.calculator.paragraph_7.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/summary")
@CrossOrigin(origins = "*")
public class SummaryController {

    @PostMapping("/slab")
    public Map<String, Object> getSlabSummary(@RequestBody BridgeContext ctx) {

        // 1. Перехватываем System.out
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        try {

            System.out.println("\nI. РАСЧЁТ ПЛИТЫ НА ПРОЧНОСТЬ (ИЗГИБАЮЩИЙ МОМЕНТ, п. 7.2)");
            System.out.println("\n ");
            SlabCalculator.calculateAndPrintReport(ctx);

            System.out.println();
            System.out.println("\nII. РАСЧЁТ ПЛИТЫ ПО ПОПЕРЕЧНОЙ СИЛЕ (п. 7.2.4)");
            System.out.println("\n ");
            SlabShearCalculator.calculateAndPrintReport(ctx);

            System.out.println("\n ");
            System.out.println("III. РАСЧЁТ ПЛИТЫ НА ВЫНОСЛИВОСТЬ (п. 7.3)");
            System.out.println("\n ");
            SlabFatigueCalculator.calculateAndPrintReport(ctx);

            System.out.println();
            System.out.println("\nIV. ИТОГОВЫЕ РЕЗУЛЬТАТЫ");
            System.out.println("\n ");

            System.out.printf("   • Класс по прочности (изгибающий момент):  K = %.2f%n", ctx.K_monolithic);
            System.out.printf("   • Класс по поперечной силе:                K = %.2f%n", ctx.K_shear_monolithic);
            System.out.printf("   • Класс по выносливости бетона:            K = %.2f%n", ctx.K_fatigue_slab_concrete);
            System.out.printf("   • Класс по выносливости арматуры:          K = %.2f%n", ctx.K_fatigue_slab_rebar);

            double minK = Math.min(Math.min(ctx.K_monolithic, ctx.K_shear_monolithic),
                    Math.min(ctx.K_fatigue_slab_concrete, ctx.K_fatigue_slab_rebar));

            System.out.printf("\nМИНИМАЛЬНЫЙ КЛАСС ПЛИТНОГО ПРОЛЁТНОГО СТРОЕНИЯ: K = %.2f%n", minK);

        } finally {
            System.setOut(originalOut);
        }

        // 4. Получаем собранный отчёт
        String detailedReport = baos.toString();

        // 5. Собираем сводную таблицу
        List<LoadCapacityRow> rows = SlabLoadCapacityAggregator.aggregateSlabResults(ctx);
        double minClass = rows.stream()
                .mapToDouble(LoadCapacityRow::getKClass)
                .min()
                .orElse(0.0);

        // 6. Формируем ответ
        Map<String, Object> response = new HashMap<>();
        response.put("rows", rows);
        response.put("minClass", minClass);
        response.put("detailedReport", detailedReport);

        return response;
    }
}