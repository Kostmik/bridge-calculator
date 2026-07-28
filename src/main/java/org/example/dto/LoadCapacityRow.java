package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadCapacityRow {
    private String calculationType;
    private double lambda;
    private double alpha;
    private double k;
    private double ks;

    @JsonProperty("kClass")
    private double kClass;

    private double loadClassII;
    private double loadClass27;
    private double loadClass25;

    // Конструктор
    public LoadCapacityRow() {}

    // Геттеры и сеттеры (ПРАВИЛЬНЫЕ имена!)
    public String getCalculationType() { return calculationType; }
    public void setCalculationType(String calculationType) { this.calculationType = calculationType; }

    public double getLambda() { return lambda; }
    public void setLambda(double lambda) { this.lambda = lambda; }

    public double getAlpha() { return alpha; }
    public void setAlpha(double alpha) { this.alpha = alpha; }

    public double getK() { return k; }
    public void setK(double k) { this.k = k; }

    public double getKs() { return ks; }
    public void setKs(double ks) { this.ks = ks; }

    public double getKClass() { return kClass; }  // <-- БОЛЬШАЯ K ПОСЛЕ get!
    public void setKClass(double kClass) { this.kClass = kClass; }

    public double getLoadClassII() { return loadClassII; }
    public void setLoadClassII(double loadClassII) { this.loadClassII = loadClassII; }

    public double getLoadClass27() { return loadClass27; }
    public void setLoadClass27(double loadClass27) { this.loadClass27 = loadClass27; }

    public double getLoadClass25() { return loadClass25; }
    public void setLoadClass25(double loadClass25) { this.loadClass25 = loadClass25; }
}