package com.uniquindio.edu.parcial3consultoriomedico.models.util;

public class GenerateidUtil {
    private static int contadorCita = 1;

    public static String generateIdCita() {
        return String.format("Cita-%05d", contadorCita++);
    }
}
