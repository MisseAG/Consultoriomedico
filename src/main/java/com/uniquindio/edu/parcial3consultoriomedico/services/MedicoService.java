package com.uniquindio.edu.parcial3consultoriomedico.services;

import com.uniquindio.edu.parcial3consultoriomedico.models.Medico;
import com.uniquindio.edu.parcial3consultoriomedico.repositories.Database;

import java.util.ArrayList;

public class MedicoService {

    private static MedicoService instance;
    private final Database database;

    private MedicoService() {
        this.database = Database.getInstance();
    }


    public static synchronized MedicoService getInstance() {
        if (instance == null) {
            instance = new MedicoService();
        }
        return instance;
    }

    public Medico crearMedico(String id, String name, String phone, String correo, String especialidad) {
        Medico medico = Medico.builder()
                .id(id)
                .name(name)
                .phone(phone)
                .correo(correo)
                .especialidad(especialidad)
                .build();
        return medico;

    }

    public void addMedico(Medico medico){
        database.getMedicos().add(medico);
    }

    public void actualizarMedico(String id, String name, String phone, String correo, String especialidad) {
        Medico medico = database.buscarMedicoById(id);

        medico.setName(name);
        medico.setPhone(phone);
        medico.setCorreo(correo);
        medico.setEspecialidad(especialidad);
    }

    public ArrayList<Medico> getAllMedicos() {
        return database.getMedicos();
    }

    public void eliminarMedico(String id) {
        database.getMedicos().removeIf(m -> m.getId().equals(id));
    }

    public boolean existeMedico(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return database.getMedicos().stream()
                .anyMatch(p -> id.equals(p.getId()));
    }

    public int contarMedicos() {
        return database.getMedicos().size();
    }
}