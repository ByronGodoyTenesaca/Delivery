package com.example.delivery;

public class Car
{
    private String producto;
    private int cantidad;
    private double costo;

    public Car(String producto, int cantidad, double costo) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public Car() {
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "producto='" + producto +
                ", cantidad=" + cantidad +
                ", costo=" + costo;
    }
}
