package etsisi.ems.trabajo3.banco;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

public class Cuenta {
	protected String mNumero;
	protected String mTitular;
	protected Vector<Movimiento> mMovimientos;

	public Cuenta(String numero, String titular) {
		mNumero = numero;
		mTitular = titular;
		mMovimientos = new Vector<Movimiento>();
	}

	public void nuevoMovimiento(String concepto, double importe) {
		Movimiento m = new Movimiento();
		m.setConcepto(concepto);
		m.setImporte(importe);
		Date date = new Date();
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		m.setFecha(fecha);
		this.mMovimientos.addElement(m);
	}

	public void ingresar(double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede ingresar una cantidad negativa");
		
		this.nuevoMovimiento("Ingreso en efectivo", x);
	}

	public void retirar(double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)
			throw new Exception("Saldo insuficiente");
		
		this.nuevoMovimiento("Retirada de efectivo", x * -1);
	}
	
	public void ingresar(String concepto, double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede ingresar una cantidad negativa");

		this.nuevoMovimiento(concepto, x);
	}

	public void retirar(String concepto, double x) throws Exception {
		if (x <= 0)
			throw new Exception("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)
			throw new Exception("Saldo insuficiente");
		
		this.nuevoMovimiento(concepto, x * -1);
	}

	public double getSaldo() {
		return this.getSaldo(this.mMovimientos);
	}
	
	public double getSaldo(Vector<Movimiento> movimientos) {
		double r = 0.0;
		for (int i = 0; i < movimientos.size(); i++) {
			Movimiento m = (Movimiento) movimientos.elementAt(i);
			r += m.getImporte();			
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {
		mMovimientos.addElement(m);
	}
}