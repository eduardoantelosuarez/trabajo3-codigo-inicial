package etsisi.ems.trabajo3.banco;

public class EntidadEmisora {

	private String mNombreEntidad;
	private int mMarcaInternacional;
	private int mCCV;
	
	public EntidadEmisora(int marcaInternacional, String nombreEntidad, int ccv) {
		this.mMarcaInternacional = marcaInternacional;
		this.mNombreEntidad = nombreEntidad;
		this.mCCV = ccv;
	}

	public String getmNombreEntidad() {
		return mNombreEntidad;
	}

	public void setmNombreEntidad(String mNombreEntidad) {
		this.mNombreEntidad = mNombreEntidad;
	}

	public int getmMarcaInternacional() {
		return mMarcaInternacional;
	}

	public void setmMarcaInternacional(int mMarcaInternacional) {
		this.mMarcaInternacional = mMarcaInternacional;
	}

	public int getmCCV() {
		return mCCV;
	}

	public void setmCCV(int mCCV) {
		this.mCCV = mCCV;
	}
}
