package com.ipartek.formacion.modelo.pojo;

import java.sql.Date;


public class Multa {
	private Long id;
	private int importe;
	private String concepto;
	private Date fecha;
	
	Coche c = new Coche();
	
	public Multa() {
		super();
	}

	public Multa(Long id, int importe, String concepto, Date fecha) {
		this();
		setId(id);
		setImporte(importe);
		setConcepto(concepto);
		setFecha(fecha);
		setCoche(c);
	}

	public Coche getCoche() {
		return c;
	}

	public void setCoche(Coche c) {
		this.c = c;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
