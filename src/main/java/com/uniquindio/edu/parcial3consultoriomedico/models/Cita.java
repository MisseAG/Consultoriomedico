package com.uniquindio.edu.parcial3consultoriomedico.models;

import com.uniquindio.edu.parcial3consultoriomedico.models.EstadoCita.EstadoCita;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Cita {
    private final String id;
    private final LocalDate fecha;
    private final LocalTime hora;
    private final String motivo;
    private final Paciente paciente;
    private final Medico medico;
    private final String especialidad;
    private EstadoCita estado;

    // Constructor privado - solo accesible via Builder
    private Cita(Builder builder) {
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.hora = builder.hora;
        this.motivo = builder.motivo;
        this.paciente = builder.paciente;
        this.medico = builder.medico;
        this.especialidad = builder.especialidad;
        this.estado = builder.estado;
    }

    public String getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getMotivo() { return motivo; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public String getEspecialidad() { return especialidad; }
    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }

    public HistorialMedico getHistorialMedicoDelPaciente() {
        return paciente != null ? paciente.getHistorialMedico() : null;
    }

    // Método estático para obtener el Builder
    public static Builder builder() {
        return new Builder();
    }

    // Clase Builder estática
    public static class Builder {
        private String id;
        private LocalDate fecha;
        private LocalTime hora;
        private String motivo;
        private Paciente paciente;
        private Medico medico;
        private String especialidad;
        private EstadoCita estado;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder hora(LocalTime hora) {
            this.hora = hora;
            return this;
        }

        public Builder motivo(String motivo) {
            this.motivo = motivo;
            return this;
        }

        public Builder paciente(Paciente paciente) {
            this.paciente = paciente;
            return this;
        }

        public Builder medico(Medico medico) {
            this.medico = medico;
            return this;
        }

        public Builder especialidad(String especialidad) {
            this.especialidad = especialidad;
            return this;
        }

        public Builder estado(EstadoCita estado) {
            this.estado = estado;
            return this;
        }

        public Cita build() {
            validar();
            return new Cita(this);
        }

        private void validar() {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalStateException("ID de cita es requerido");
            }
            if (fecha == null) {
                throw new IllegalStateException("Fecha es requerida");
            }
            if (paciente == null) {
                throw new IllegalStateException("Paciente es requerido");
            }
            if (medico == null) {
                throw new IllegalStateException("Médico es requerido");
            }
        }
    }



    @Override
    public String toString() {
        return "Cita: " + id + " - " + fecha + " " + hora +
                " con " + (medico != null ? medico.getName() : "N/A") +
                " para " + (paciente != null ? paciente.getName() : "N/A") +
                " (" + estado + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return Objects.equals(id, cita.id);
    }


}