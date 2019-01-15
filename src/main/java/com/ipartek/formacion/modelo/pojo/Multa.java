package com.ipartek.formacion.modelo.pojo;

import java.sql.Date;


public class Multa {
	private Long id;
	private int importe;
	private String concepto;
	private Date fecha;
	private Date fecha_baja;
	
	Coche c = new Coche();
	
	
	public Multa() {
		super();
	}

	public Multa(Long id, int importe, String concepto, Date fecha, Date fecha_baja) {
		this();
		setId(id);
		setImporte(importe);
		setConcepto(concepto);
		setFecha(fecha);
		setFecha_baja(fecha_baja);
		setCoche(c);
	
	}

	public Date getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
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

	@Override
	public String toString() {
		return "Multa [id=" + id + ", importe=" + importe + ", concepto=" + concepto + ", fecha=" + fecha
				+ ", fecha_baja=" + fecha_baja + ", c=" + c + "]";
	}



	

}
