package com.uniquindio.edu.parcial3consultoriomedico.services;


import com.uniquindio.edu.parcial3consultoriomedico.models.Cita;
import com.uniquindio.edu.parcial3consultoriomedico.models.EstadoCita.Asignada;
import com.uniquindio.edu.parcial3consultoriomedico.models.EstadoCita.EstadoCita;
import com.uniquindio.edu.parcial3consultoriomedico.models.Medico;
import com.uniquindio.edu.parcial3consultoriomedico.models.Paciente;
import com.uniquindio.edu.parcial3consultoriomedico.models.util.GenerateidUtil;
import com.uniquindio.edu.parcial3consultoriomedico.repositories.Database;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CitaService {

    private static CitaService instance;
    private Database database;

    private CitaService() {
        this.database = Database.getInstance();
    }

    public static CitaService getInstance() {
        if (instance == null) {
            instance = new CitaService();
        }
        return instance;
    }

    public void addCita(Cita cita) {
        database.getCitas().add(cita);
    }

    public Cita createCita(LocalDate fecha, LocalTime hora, String motivo, String pacienteId, String medicoId, String especialidad) {
        String id = GenerateidUtil.generateIdCita();

        Paciente paciente = database.buscarPacienteById(pacienteId);
        Medico medico = database.buscarMedicoById(medicoId);

        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no encontrado con ID: " + pacienteId);
        }
        if (medico == null) {
            throw new IllegalArgumentException("Médico no encontrado con ID: " + medicoId);
        }

        Cita cita = new Cita.Builder()
                .id(id)
                .fecha(fecha)
                .hora(hora)
                .motivo(motivo)
                .paciente(paciente)
                .medico(medico)
                .especialidad(especialidad)
                .estado(new Asignada())
                .build();

        return cita;
    }

    public boolean eliminarCita(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return database.getCitas().removeIf(p -> id.equals(p.getId()));
    }

    public ArrayList<Cita> getAllCitas() {
        return database.getCitas();
    }

    public Cita getCitaById(String id) {
        return database.getCitas().stream()
                .filter(cita -> cita.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
