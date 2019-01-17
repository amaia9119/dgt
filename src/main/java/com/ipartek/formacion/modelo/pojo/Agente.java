package com.ipartek.formacion.modelo.pojo;

import java.util.ArrayList;

public class Agente {
	
	private Long id;
	private String nombre;
	private Long placa;
	private String pass;
	ArrayList<Multa> multas = new ArrayList<Multa>();
	
	public Agente() {
		super();
		this.id = (long) -1;
		this.nombre = "";
		this.placa = (long) -1;
		this.pass = "";
	}

	public Agente(Long id, String nombre, Long placa) {
		this();
		setId(id);
		setNombre(nombre);
		setPlaca(placa);
		setMultas(multas);
		setPass(pass);
	}

	public ArrayList<Multa> getMultas() {
		return multas;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setMultas(ArrayList<Multa> multas) {
		this.multas = multas;
	}

	@Override
	public String toString() {
		return "Agente [id=" + id + ", nombre=" + nombre + ", placa=" + placa + ", pass=" + pass + ", multas=" + multas
				+ "]";
	}

	
	
}