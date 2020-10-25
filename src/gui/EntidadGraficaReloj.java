package gui;

import javax.swing.ImageIcon;

public class EntidadGraficaReloj {
		private ImageIcon grafico;
		private String[] imagenes;
		
		public EntidadGraficaReloj() {
			this.grafico = new ImageIcon();
			this.imagenes = new String[]{"/img/cero.png","/img/uno.png", "/img/dos.png", "/img/tres.png", "/img/cuatro.png","/img/cinco.png","/img/seis.png","/img/siete.png","/img/ocho.png","/img/nueve.png"};
		}
		
		public ImageIcon actualizar(int indice) {
			ImageIcon imageIcon=null;
			if (indice < this.imagenes.length) {
				
				imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
				
				this.grafico.setImage(imageIcon.getImage());
			}
			return imageIcon;
		}
		
		
}
