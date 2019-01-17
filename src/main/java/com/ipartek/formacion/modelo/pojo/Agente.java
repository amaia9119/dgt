package com.ipartek.formacion.modelo.pojo;

import java.util.ArrayList;

public class Agente {
	
	private Long id;
	private String nombre;
	private Long placa;
	ArrayList<Multa> multas = new ArrayList<Multa>();
	private Double importeAnual;
	private Double importeMensual;
	
	public Agente() {
		super();
		this.id = (long) -1;
		this.nombre = "";
		this.placa = (long) -1;
	}

	public Agente(Long id, String nombre, Long placa) {
		this();
		setId(id);
		setNombre(nombre);
		setPlaca(placa);
		setMulta(multas);
		setImporteAnual(importeAnual);
		setImporteMensual(importeMensual);
	}

	public Double getImporteAnual() {
		return importeAnual;
	}

	public void setImporteAnual(Double importeAnual) {
		this.importeAnual = importeAnual;
	}

	public Double getImporteMensual() {
		return importeMensual;
	}

	public void setImporteMensual(Double importeMensual) {
		this.importeMensual = importeMensual;
	}

	public ArrayList<Multa> getMultas() {
		return multas;
	}

	public void setMulta(ArrayList<Multa> multas) {
		this.multas = multas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getPlaca() {
		return placa;
	}

	public void setPlaca(Long placa) {
		this.placa = placa;
	}

	@Override
	public String toString() {
		return "Agente [id=" + id + ", nombre=" + nombre + ", placa=" + placa + ", multas=" + multas + ", importeAnual="
				+ importeAnual + ", importeMensual=" + importeMensual + "]";
	}

}