package logica;


import gui.EntidadGraficaReloj;


public class Reloj {
	
	private int s,m,h;
	private EntidadGraficaReloj entidad; 

	public Reloj() {
		s=0;m=0;h=0;
		entidad=new EntidadGraficaReloj();
	}
	
	public void sumar() {
		
		/*Simula el paso de los segundos como un reloj*/
		s++;
	    if(s==60) {
	    	m++;
	    	s=0;
	    }
	    		        
	    if(m==60) {
	    	h++;
	    	m=0;
	    }
	    if(h==24) {
	    	s=0;
	    	m=0;
	    	h=0;
	    }
	}
	
	public EntidadGraficaReloj getEntidad() {
		return entidad;
	} 
	public int getSegundos() {
		return s;
	} 
	public int getMinutos() {
		return m;
	} 
	public int getHoras() {
		return h;
	} 
	
	
}
