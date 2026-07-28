package org.example.dto.materials;

public record MaterialResponse(
        Double Rb,
        Double Rbt,
        Double Eb,
        Double Rs,
        Double Es,
        Double nPrime,
        String detailedReport
) {}