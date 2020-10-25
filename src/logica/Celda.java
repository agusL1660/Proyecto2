package logica;

public class Celda {
	private Integer valor;
	private EntidadGrafica entidadGrafica;
	private int fila;
	private int columna;
	private boolean modificar;
	
	public Celda(int fila,int col) {
		this.valor = null;
		this.entidadGrafica = new EntidadGrafica();
		this.fila=fila;this.columna=col;
		modificar=false;
	}
	
	
	public void actualizar() {
		if(modificar) {
			if (this.valor != null && this.valor + 1 < this.getCantElementos()) {
				this.valor++;
			}else {
				this.valor = 0;
			}
		}
		entidadGrafica.actualizar(this.valor);
	}
	public int getColumna() {
		return columna;
	}
	public int getFila() {
		return fila;
	}
	public boolean getModificar() {
		return modificar;
	}
	public void setModificar(boolean a) {
		modificar=a;
	}
	public int getCantElementos() {
		return this.entidadGrafica.getImagenes().length;
	}
	
	
	public Integer getValor() {
		return this.valor;
	}
	
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
		}else {
			this.valor = null;
		}
	}
	
	public EntidadGrafica getEntidadGrafica() {
		return this.entidadGrafica;
	}
	
	public void setGrafica(EntidadGrafica g) {
		this.entidadGrafica = g;
	}
}
