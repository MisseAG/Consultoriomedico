package com.uniquindio.edu.parcial3consultoriomedico.models;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Person {
    private String especialidad;
    private List<Cita> citasAsignadas;

    private Medico(Builder builder) {
        super(builder);
        this.especialidad = builder.especialidad;
        this.citasAsignadas = new ArrayList<>();

    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }


    public static class Builder extends Person.PersonBuilder<Builder> {
        private String especialidad;

        public Builder especialidad(String especialidad) {
            this.especialidad = especialidad;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Medico build() {
            return new Medico(this);
        }

    }
}
