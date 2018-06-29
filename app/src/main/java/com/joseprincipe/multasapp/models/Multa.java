package com.joseprincipe.multasapp.models;

public class Multa {
        private int id = -1;
        private String placa;
        private String causa;
        private String tipo;
        private String nombre;
        private String dni;
        private String monto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    private boolean fijo = false;

        public Multa() {
        }

        public Multa(String placa, String causa, String tipo, String nombre, String dni, String monto) {
            this.placa = placa;
            this.causa = causa;
            this.tipo = tipo;
            this.nombre = nombre;
            this.dni = dni;
            this.monto = monto;
        }

        public boolean isFijo() {
            return fijo;
        }

        public void setFijo(boolean fijo) {
            this.fijo = fijo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlaca() {
            return placa;
        }

        public void setPlaca(String placa) {
            this.placa = placa;
        }

        public String getCausa() {
            return causa;
        }

        public void setCausa(String causa) {
            this.causa = causa;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }



        @Override
        public String toString() {
            return placa;
        }

}
