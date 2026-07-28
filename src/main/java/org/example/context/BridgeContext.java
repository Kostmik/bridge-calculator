package org.example.context;

import com.fasterxml.jackson.annotation.JsonProperty; // <-- ДОБАВЛЕННЫЙ ИМПОРТ
import org.example.model.RebarType;
import org.example.model.TrackType;

public class BridgeContext {
    // Исходные данные
    @JsonProperty("spanLength")
    public double spanLength;                    // Расчетный пролет l, м

    @JsonProperty("ballastThickness")
    public double ballastThickness;              // Толщина балласта hb, м

    @JsonProperty("trackType")
    public TrackType trackType;                  // Тип пути

    @JsonProperty("concreteStrengthR")
    public double concreteStrengthR;             // Фактическая прочность бетона R, МПа

    // Геометрия балок
    @JsonProperty("distanceBetweenBeams")
    public double distanceBetweenBeams; // Расстояние между осями главных балок c, м

    @JsonProperty("trackOffsetLeft")
    public double trackOffsetLeft;      // Смещение оси пути у левой опоры e1, м

    @JsonProperty("trackOffsetRight")
    public double trackOffsetRight;     // Смещение оси пути у правой опоры e2, м

    // Удельные веса
    @JsonProperty("gammaReinforcedConcrete")
    public double gammaReinforcedConcrete = 24.52;  // кН/м³

    @JsonProperty("gammaBallastWithTrack")
    public double gammaBallastWithTrack;            // кН/м³

    // Коэффициенты надежности
    @JsonProperty("np")
    public double np = 1.1;      // для веса ж/б

    @JsonProperty("npPrime")
    public double npPrime = 1.2; // для веса балласта

    @JsonProperty("nk")
    public double nk = 1.15;     // для временной нагрузки

    // === ХАРАКТЕРИСТИКИ МАТЕРИАЛОВ (Раздел 5) ===
    @JsonProperty("Rb")
    public double Rb;          // Расчетное сопротивление бетона сжатию, МПа

    @JsonProperty("Rbt")
    public double Rbt;         // Расчетное сопротивление бетона растяжению, МПа

    @JsonProperty("Eb")
    public double Eb;          // Модуль упругости бетона, МПа

    @JsonProperty("nPrime")
    public double nPrime;      // Условное отношение модулей упругости арматуры и бетона (n')

    @JsonProperty("Rs")
    public double Rs;          // Расчетное сопротивление арматуры растяжению, МПа

    @JsonProperty("Es")
    public double Es = 206000; // Модуль упругости арматуры, МПа (2.06 * 10^5)

    @JsonProperty("rebarType")
    public String rebarType;   // Тип арматуры (для вывода)

    // Результаты расчетов (Раздел 6)
    @JsonProperty("dynamicCoeffBeam")
    public Double dynamicCoeffBeam;

    @JsonProperty("dynamicCoeffSlab")
    public Double dynamicCoeffSlab;

    @JsonProperty("ppSlab")
    public Double ppSlab;

    @JsonProperty("pbSlab")
    public Double pbSlab;

    @JsonProperty("ppBeam")
    public Double ppBeam;

    @JsonProperty("pbBeam")
    public Double pbBeam;

    // === ДОЛИ ВРЕМЕННОЙ НАГРУЗКИ (Раздел 6.6 - 6.7) ===
    @JsonProperty("epsilonM") // Фронтенд шлет epsilonM
    public Double epsilonM_Beam1; // Доля на балку 1 по моменту

    @JsonProperty("epsilonQ") // Фронтенд шлет epsilonQ
    public Double epsilonQ_Beam1; // Доля на балку 1 по силе

    @JsonProperty("epsilonM_Beam2")
    public Double epsilonM_Beam2; // Доля на балку 2 по моменту

    @JsonProperty("epsilonQ_Beam2")
    public Double epsilonQ_Beam2; // Доля на балку 2 по силе

    // === ГЕОМЕТРИЯ И АРМАТУРА ПЛИТЫ (Раздел 7.2) ===
    @JsonProperty("slabHeight")
    public double slabHeight;                // Высота плиты h, м

    @JsonProperty("asTensile")
    public double as_tensile;                // Расстояние до центра растянутой арматуры as, м

    @JsonProperty("asCompressive")
    public double as_compressive;            // Расстояние до центра сжатой арматуры as', м

    @JsonProperty("asTensileArea")
    public double As_tensile;                // Площадь растянутой арматуры As, м²

    @JsonProperty("asCompressiveArea")
    public double As_compressive;            // Площадь сжатой арматуры As', м²

    // Геометрия поперечного сечения плиты
    @JsonProperty("lp")
    public double lp;                        // Расстояние между внутренними гранями ребер lp, м

    @JsonProperty("B")
    public double B;                         // Расстояние между наружными гранями ребер B, м

    @JsonProperty("ls")
    public double ls;                        // Длина шпалы ls, м

    @JsonProperty("lbPrime")
    public double lb_prime;                  // Расстояние от наружной грани ребра до внутренней грани левого борта l'b, м

    @JsonProperty("lbDoubleprime")
    public double lb_doubleprime;            // Расстояние от наружной грани ребра до внутренней грани правого борта l''b, м

    @JsonProperty("hbPrime")
    public double hb_prime;                  // Толщина балласта под левым концом шпалы h'b, м

    @JsonProperty("hbDoubleprime")
    public double hb_doubleprime;            // Толщина балласта под правым концом шпалы h''b, м

    // Моменты от постоянных нагрузок (вводятся или считаются)
    @JsonProperty("mpMonolithic")
    public double Mp_monolithic;             // Момент от пост. нагрузок в монолитном участке, кН·м

    @JsonProperty("mpExternalCantilever")
    public double Mp_external_cantilever;    // Момент от пост. нагрузок во внешней консоли, кН·м

    // Предельные моменты (считаются программой)
    @JsonProperty("M_pred_I")
    public double M_pred_I;                  // Предельный момент в сечении I-I, кН·м

    @JsonProperty("M_pred_II")
    public double M_pred_II;                 // Предельный момент в сечении II-II, кН·м

    @JsonProperty("M_pred_III")
    public double M_pred_III;                // Предельный момент в сечении III-III, кН·м

    // Допускаемые нагрузки и классы
    @JsonProperty("kMonolithic")
    public double k_monolithic;              // Допускаемая нагрузка для монолитного участка, кН/м

    @JsonProperty("KMonolithic")
    public double K_monolithic;              // Класс монолитного участка

    @JsonProperty("kExternalCantilever")
    public double k_external_cantilever;     // Допускаемая нагрузка для внешней консоли, кН/м

    @JsonProperty("KExternalCantilever")
    public double K_external_cantilever;     // Класс внешней консоли

    @JsonProperty("b")
    public double b;

    @JsonProperty("l0")
    public double l0;

    @JsonProperty("j")
    public double j;

    @JsonProperty("designYear")
    public int designYear;

    @JsonProperty("beta")
    public double beta;

    // Дополнительные параметры для поперечной силы
    @JsonProperty("lt")
    public double lt;           // Длина внешней консоли с учетом тротуара, м

    @JsonProperty("lk")
    public double lk;           // Длина внешней консоли плиты, м

    @JsonProperty("P0")
    public double P0;           // Нагрузка от веса перил, кН/м

    @JsonProperty("pt")
    public double pt;           // Нагрузка от веса тротуара, кН/м

    // === ПОПЕРЕЧНАЯ СИЛА (7.2.4) ===
    @JsonProperty("Q_pred_I")
    public double Q_pred_I;           // Предельная поперечная сила в сечении I-I, кН

    @JsonProperty("Q_pred_II")
    public double Q_pred_II;          // Предельная поперечная сила в сечении II-II, кН

    @JsonProperty("Q_pred_III")
    public double Q_pred_III;         // Предельная поперечная сила в сечении III-III, кН

    @JsonProperty("Qp_I")
    public double Qp_I;               // Поперечная сила от пост. нагрузок в сечении I-I, кН

    @JsonProperty("Qp_II")
    public double Qp_II;              // Поперечная сила от пост. нагрузок в сечении II-II, кН

    @JsonProperty("Qp_III")
    public double Qp_III;             // Поперечная сила от пост. нагрузок в сечении III-III, кН

    @JsonProperty("kShearMonolithic")
    public double k_shear_monolithic; // Допускаемая нагрузка по поперечной силе (монолитный), кН/м

    @JsonProperty("KShearMonolithic")
    public double K_shear_monolithic; // Класс по поперечной силе (монолитный)

    @JsonProperty("kShearExternal")
    public double k_shear_external;   // Допускаемая нагрузка по поперечной силе (консоль), кН/м

    @JsonProperty("KShearExternal")
    public double K_shear_external;   // Класс по поперечной силе (консоль)

    @JsonProperty("etaQ")
    public double etaQ;               // Коэффициент неравномерности для поперечной силы

    // === ГЛАВНАЯ БАЛКА (п. 7.2.5-7.2.6) ===
    @JsonProperty("beamHeight")
    public double beamHeight;           // Высота балки h, м

    @JsonProperty("beamWidth")
    public double beamWidth;            // Ширина ребра балки b, м

    @JsonProperty("bf")
    public double bf;                   // Расчетная ширина плиты bf, м

    @JsonProperty("hf")
    public double hf;                   // Приведенная толщина плиты hf, м

    @JsonProperty("asBeamTensileArea")
    public double As_beam_tensile;      // Площадь растянутой арматуры балки, м²

    @JsonProperty("asBeamCompressiveArea")
    public double As_beam_compressive;  // Площадь сжатой арматуры балки, м²

    @JsonProperty("asBeamTensile")
    public double as_beam_tensile;      // Расстояние до центра растянутой арматуры, м

    @JsonProperty("asBeamCompressive")
    public double as_beam_compressive;  // Расстояние до центра сжатой арматуры, м

    // Результаты расчета балки
    @JsonProperty("M_pred_beam")
    public double M_pred_beam;          // Предельный момент балки, кН·м

    @JsonProperty("Mp_beam")
    public double Mp_beam;              // Момент от пост. нагрузок, кН·м

    @JsonProperty("Omega_M")
    public Double Omega_M;              // Площадь линии влияния момента, м²

    @JsonProperty("kBeamMoment")
    public double k_beam_moment;        // Допускаемая нагрузка по моменту, кН/м

    @JsonProperty("KBeamMoment")
    public double K_beam_moment;        // Класс балки по моменту

    @JsonProperty("kcBeam")
    public double kc_beam;              // Эталонная нагрузка для балки, кН/м

    // === ГЛАВНАЯ БАЛКА: ПОПЕРЕЧНАЯ СИЛА (п. 7.2.7-7.2.8) ===
    @JsonProperty("Asw")
    public double Asw;            // Площадь поперечного сечения одной ветви хомутов, м²

    @JsonProperty("sStirrups")
    public double s_stirrups;     // Шаг хомутов s, м

    @JsonProperty("sumAsi")
    public double sum_Asi;        // Сумма площадей отогнутых стержней, м²

    @JsonProperty("alphaBent")
    public double alpha_bent;     // Угол наклона отогнутых стержней, град

    @JsonProperty("Omega_k")
    public Double Omega_k;        // Площадь линии влияния поперечной силы (временная), м²

    @JsonProperty("Omega_p")
    public double Omega_p;        // Площадь линии влияния поперечной силы (постоянная), м²

    // Результаты расчета
    @JsonProperty("Q_ultimate")
    public double Q_ultimate;     // Предельная поперечная сила, кН

    @JsonProperty("Q_p_shear")
    public double Q_p_shear;      // Поперечная сила от постоянных нагрузок, кН

    @JsonProperty("kBeamShear")
    public double k_beam_shear;   // Допускаемая нагрузка по поперечной силе, кН/м

    @JsonProperty("KBeamShear")
    public double K_beam_shear;   // Класс балки по поперечной силе

    // === ВЫНОСЛИВОСТЬ ПЛИТЫ (п. 7.3.1) ===
    @JsonProperty("x_prime_slab")
    public double x_prime_slab;           // Высота сжатой зоны для выносливости, м

    @JsonProperty("I_red_slab")
    public double I_red_slab;             // Приведенный момент инерции, м⁴

    @JsonProperty("rho_b_slab")
    public double rho_b_slab;             // Асимметрия цикла для бетона

    @JsonProperty("rho_s_slab")
    public double rho_s_slab;             // Асимметрия цикла для арматуры

    @JsonProperty("Rbf")
    public double Rbf;                    // Расчетное сопротивление бетона на выносливость, МПа

    @JsonProperty("Rsf")
    public double Rsf;                    // Расчетное сопротивление арматуры на выносливость, МПа

    @JsonProperty("Theta_slab")
    public double Theta_slab;             // Коэффициент уменьшения динамики для плиты

    @JsonProperty("kFatigueConcrete")     // <-- КРИТИЧЕСКИ ВАЖНО: маппинг с фронтенда
    public double k_fatigue_slab_concrete; // Допускаемая нагрузка по выносливости бетона, кН/м

    @JsonProperty("KFatigueConcrete")     // <-- КРИТИЧЕСКИ ВАЖНО: маппинг с фронтенда
    public double K_fatigue_slab_concrete; // Класс по выносливости бетона

    @JsonProperty("kFatigueRebar")        // <-- КРИТИЧЕСКИ ВАЖНО: маппинг с фронтенда
    public double k_fatigue_slab_rebar;    // Допускаемая нагрузка по выносливости арматуры, кН/м

    @JsonProperty("KFatigueRebar")        // <-- КРИТИЧЕСКИ ВАЖНО: маппинг с фронтенда
    public double K_fatigue_slab_rebar;    // Класс по выносливости арматуры

    // === ДОБАВЛЕНО: Длина загружения (из раздела 6.4) ===
    @JsonProperty("lambda")
    public Double lambda;

    // === ВЫНОСЛИВОСТЬ ГЛАВНОЙ БАЛКИ (п. 7.3.2) ===
    @JsonProperty("x_prime_beam")
    public double x_prime_beam;           // Высота сжатой зоны для выносливости, м

    @JsonProperty("I_red_beam")
    public double I_red_beam;             // Приведенный момент инерции, м

    @JsonProperty("rho_b_beam")
    public double rho_b_beam;             // Асимметрия цикла для бетона

    @JsonProperty("rho_s_beam")
    public double rho_s_beam;             // Асимметрия цикла для арматуры

    @JsonProperty("Rbf_beam")
    public double Rbf_beam;               // Расчетное сопротивление бетона на выносливость, МПа

    @JsonProperty("Rsf_beam")
    public double Rsf_beam;               // Расчетное сопротивление арматуры на выносливость, МПа

    @JsonProperty("Theta_beam")
    public double Theta_beam;             // Коэффициент уменьшения динамики для балки

    @JsonProperty("k_fatigue_beam_concrete")
    public double k_fatigue_beam_concrete; // Допускаемая нагрузка по выносливости бетона, кН/м

    @JsonProperty("K_fatigue_beam_concrete")
    public double K_fatigue_beam_concrete; // Класс по выносливости бетона

    @JsonProperty("k_fatigue_beam_rebar")
    public double k_fatigue_beam_rebar;    // Допускаемая нагрузка по выносливости арматуры, кН/м

    @JsonProperty("K_fatigue_beam_rebar")
    public double K_fatigue_beam_rebar;    // Класс по выносливости арматуры
}