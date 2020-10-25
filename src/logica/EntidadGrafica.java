package logica;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EntidadGrafica {
	private ImageIcon grafico;
	private String[] imagenes;
	private JLabel cartel;
	
	public EntidadGrafica() {
		this.grafico = new ImageIcon();
		cartel = new JLabel();
		this.imagenes = new String[]{"/img/uno.png", "/img/dos.png", "/img/tres.png", "/img/cuatro.png","/img/cinco.png","/img/seis.png","/img/siete.png","/img/ocho.png","/img/nueve.png"};
	}
	
	public void actualizar(int indice) {
		if (indice < this.imagenes.length) {
			
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	public JLabel getLabel() {
		return this.cartel;
	}
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
	
}
