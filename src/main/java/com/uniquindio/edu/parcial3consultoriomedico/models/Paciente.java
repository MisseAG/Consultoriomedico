package com.uniquindio.edu.parcial3consultoriomedico.models;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Person {
    private HistorialMedico historialMedico;
    private List<Cita> citasProgramadas;
    private List<Notificacion> notificacionesInternas;
    private boolean citaAsignada;


    private Paciente(Builder builder) {
        super(builder);
        this.historialMedico = new HistorialMedico();
        this.citasProgramadas = new ArrayList<>();
        this.notificacionesInternas = new ArrayList<>();
    }
    public static Builder builder() {
        return new Builder();
    }

    public HistorialMedico getHistorialMedico() {
        return historialMedico;
    }

    public boolean isCitaAsignada() {
        return citaAsignada;
    }

    public void setCitaAsignada(boolean citaAsignada) {
        this.citaAsignada = citaAsignada;
    }


    public static class Builder extends Person.PersonBuilder<Builder> {

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Paciente build() {
            return new Paciente(this);
        }
    }
}
