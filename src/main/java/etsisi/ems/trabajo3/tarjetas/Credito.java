package etsisi.ems.trabajo3.tarjetas;

import java.util.Date;
import java.util.Vector;

import etsisi.ems.trabajo3.banco.EntidadEmisora;
import etsisi.ems.trabajo3.banco.EntidadEmisoraTipo;
import etsisi.ems.trabajo3.banco.Movimiento;

import java.time.LocalDate;
import java.time.ZoneId;

public class Credito extends Tarjeta{
	protected double mCredito;
	protected Vector<Movimiento> mMovimientos;
	private EntidadEmisora mEntidadEmisora;
	private int mTipo;


	public Credito(Tarjeta tarjeta, double credito, EntidadEmisora entidadEmisora) {
		super(tarjeta.getmNumero(), tarjeta.getmTitular(), tarjeta.getmFechaDeCaducidad());
		mCredito = credito;
		mMovimientos = new Vector<Movimiento>();
		this.mEntidadEmisora = entidadEmisora;
	}
	
	public Credito(Tarjeta tarjeta, EntidadEmisora entidadEmisora, int tipo) {
		super(tarjeta.getmNumero(), tarjeta.getmTitular(), tarjeta.getmFechaDeCaducidad());
		mTipo = tipo;
		mCredito = calcularCredito(mTipo);
		mMovimientos = new Vector<Movimiento>();
		this.mEntidadEmisora = entidadEmisora;
	}
	
	public double calcularCredito(int tipo) {
		double credito;
		switch (tipo) {
			case 1: //oro 	
				credito = 1000;
				break;		
			case 2: //platino
				credito =  800;
				break;		
			case 3: //clasica
				credito =  600;
				break;		
			default:
				credito =  600;
				break;		
		}
		return credito;
	}

	public void retirar(double x) throws Exception {	
		EntidadEmisoraTipo entidadEmisora = EntidadEmisoraTipo.getByTipo(this.mEntidadEmisora.getmMarcaInternacional());
		double comisionTarifa = entidadEmisora.getmComision();
		
		// Añadimos una comisión de un 5% o 3% o 2%, mínimo de 3 euros.
		double comision = (x * comisionTarifa < 3.0 ? 3 : x * comisionTarifa); 		
		if (x > getCreditoDisponible())
			throw new Exception("Crédito insuficiente");
		
		this.addNuevoMovimiento("Retirada en cuenta asociada (cajero automático)", x + comision);
	}

	//traspaso tarjeta a cuenta
	public void ingresar(double x) throws Exception {
		double comision = (x * 0.05 < 3.0 ? 3 : x * 0.05); // Añadimos una comisión de un 5%, mínimo de 3 euros.		
		if (x > getCreditoDisponible())
			throw new Exception("Crédito insuficiente");
		
		this.addNuevoMovimiento("Traspaso desde tarjeta a cuenta", x);
		
		super.getmCuentaAsociada().ingresar("Traspaso desde tarjeta a cuenta", x);
		super.getmCuentaAsociada().retirar("Comision Traspaso desde tarjeta a cuenta", comision);
	}

	public void pagoEnEstablecimiento(String datos, double x) throws Exception {
		this.addNuevoMovimiento("Compra a crédito en: " + datos, x);
	}

	public double getCreditoDisponible() {
		return mCredito - super.getmCuentaAsociada().getSaldo(this.mMovimientos);
	}

	public void liquidar(int mes, int anyo) throws Exception {
		
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) {
			Movimiento m = (Movimiento) mMovimientos.elementAt(i);
			if (m.getFecha().getMonthValue() == mes && m.getFecha().getYear() == anyo && !m.isLiquidado())
				r += m.getImporte();
				m.setLiquidado(true);
		}
		
		if (r != 0) {
			Date date = new Date();
			LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			super.getmCuentaAsociada().addMovimiento(new Movimiento("Liquidación de operaciones tarj. crédito, " + (mes) + " de " + (anyo), -r, fecha));			
		}
	}
	
	public void addNuevoMovimiento(String concepto, double importe) {
		Date date = new Date();
		LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		mMovimientos.addElement(new Movimiento(concepto, importe, fecha));
	}
	
	//liquidación parcial sobre el total de los gastos realizados con esa tarjeta durante el mes/año  de liquidación que consiste en lo siguiente: 
	//los gastos totales, incluida una comisión de 12%, se dividen en 3 cuotas a pagar en los 3 meses siguientes 
	public void liquidarPlazos (int mes, int anyo) throws Exception {
		//TODO
	}
}