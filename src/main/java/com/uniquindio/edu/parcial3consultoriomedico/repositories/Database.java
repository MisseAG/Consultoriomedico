package com.uniquindio.edu.parcial3consultoriomedico.repositories;

import com.uniquindio.edu.parcial3consultoriomedico.models.Cita;
import com.uniquindio.edu.parcial3consultoriomedico.models.Medico;
import com.uniquindio.edu.parcial3consultoriomedico.models.Paciente;


import java.util.ArrayList;

public class Database {

    private static Database instance;
    private ArrayList<Cita> citas;
    private ArrayList<Medico> medicos;
    private ArrayList<Paciente> pacientes;

    private Database(){
        this.medicos = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.pacientes = new ArrayList<>();

        // -------- DATOS DE PRUEBA --------
        Medico m1 = Medico.builder()
                .id("M001")
                .name("Carlos Gómez")
                .phone("3001234567")
                .correo("carlos@clinic.com")
                .especialidad("Cardiología")
                .build();
        medicos.add(m1);

        Paciente p1 = Paciente.builder()
                .id("P001")
                .name("Ana Torres")
                .phone("3128889999")
                .correo("ana@mail.com")
                .build();
        pacientes.add(p1);

    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }


    public Paciente buscarPacienteById(String id) {
        return pacientes.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public Medico buscarMedicoById(String id) {
        return medicos.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}