package com.uniquindio.edu.parcial3consultoriomedico.models;

public abstract class Person {
    private String id;
    private String name;
    private String phone;
    private String correo;
    protected Person() {
    }
    protected Person(PersonBuilder<?> builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.phone = builder.phone;
        this.correo = builder.correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public abstract static class PersonBuilder<T extends PersonBuilder<T>> {
        private String id;
        private String name;
        private String phone;
        private String correo;

        public T id(String id) {
            this.id = id;
            return self();
        }

        public T name(String name) {
            this.name = name;
            return self();
        }

        public T phone(String phone) {
            this.phone = phone;
            return self();
        }

        public T correo(String correo) {
            this.correo = correo;
            return self();
        }


        protected abstract T self();


        public abstract Person build();
    }
}