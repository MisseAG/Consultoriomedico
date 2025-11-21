package com.uniquindio.edu.parcial3consultoriomedico.services;

import com.uniquindio.edu.parcial3consultoriomedico.models.Cita;
import com.uniquindio.edu.parcial3consultoriomedico.models.Paciente;
import com.uniquindio.edu.parcial3consultoriomedico.repositories.Database;

import java.util.ArrayList;

public class PacienteService {

    private static PacienteService instance;
    private final Database database;

    private PacienteService() {
        this.database = Database.getInstance();
    }

    public static synchronized PacienteService getInstance() {
        if (instance == null) {
            instance = new PacienteService();
        }
        return instance;
    }

    public void addPaciente(Paciente p) {
        if (p != null && !existePaciente(p.getId())) {
            database.getPacientes().add(p);
        }
    }

    public Paciente createPaciente(String id, String name, String phone, String correo) {
        Paciente paciente = Paciente.builder()
                .id(id)
                .name(name)
                .phone(phone)
                .correo(correo)
                .build();
        return paciente;
    }

    public void actualizarPaciente(String id, String name, String phone, String correo) {
        Paciente paciente = database.buscarPacienteById(id);

        paciente.setName(name);
        paciente.setPhone(phone);
        paciente.setCorreo(correo);
    }

    public boolean eliminarPaciente(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return database.getPacientes().removeIf(p -> id.equals(p.getId()));
    }

    public boolean existePaciente(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return database.getPacientes().stream()
                .anyMatch(p -> id.equals(p.getId()));
    }

    public int contarPacientes() {
        return database.getPacientes().size();
    }

    public boolean isCitaAsignada(String pacienteId) {
        Paciente paciente = database.buscarPacienteById(pacienteId);
        return paciente != null && paciente.isCitaAsignada();
    }

    public ArrayList<Paciente> getPacientes() {
        return database.getPacientes();
    }

    public Paciente buscarPacienteById(String id) {
        if (id == null || id.trim().isEmpty()) return null;
        return database.getPacientes().stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);
    }

}