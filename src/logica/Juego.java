package logica;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Juego {
	private Celda [][] tablero;
	private int cantFilas;
	private int cantFilaSubPanel ;
	
	public Juego() {
		this.cantFilas = 9;
		this.cantFilaSubPanel =cantFilas/3;
		tablero = new Celda[this.cantFilas][this.cantFilas];
		try {
			BufferedReader br = null; 
			FileReader fr=null;
			fr=  new FileReader("src/Archivo/texto.txt");
			br = new BufferedReader(fr); 
			String LineaActual;
			char caracter=' ';
			int aux=0;
			
			int i =0;
			
			/*Paso la solucion escrita del archivo a las celdas*/
			while ((LineaActual = br.readLine()) != null&&i<cantFilas) {
						for (int j =0; j<cantFilas; j++) {
								caracter=LineaActual.charAt(aux);
								
								if(caracter!=' '&&caracter!='\n') {	
									int value =	Character.getNumericValue(caracter);
									tablero[i][j] = new Celda(i,j);
									tablero[i][j].setValor(value-1);			
								}
								aux=aux+2;
						}
						i++;
						aux=0;		
			}
			/*Verifico que la solucion sea correcta y elimino la mitad de las celdas para que el juego pueda empezar*/
			if(Gano()) {
				eliminar();
			}
			else {
				tablero=null;
			}
			
			fr.close();
			br.close();
		}
		catch (IOException e) { 
			e.printStackTrace();
        }
		
	}
	
	public void accionar(Celda c) {
		c.actualizar();
	}
	
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
	public Celda [][] getTablero() {
		return this.tablero;
	}
	public int getCantFilas() {
		return this.cantFilas;
	}
	
	public int getCantFilaSubPanel() {
		return this.cantFilaSubPanel;
	}
	public Celda filaRepetidos(int fila,int col) {
		boolean resultado=true;
		Celda buscado=null;
		
		/*Verifica si la celda pasada por parametros se encuentra repetida en su misma fila */

		if(getCelda(fila,col).getValor()!=null) {
			for(int j=0;j<cantFilas&&resultado;j++) {
				if(j!=col&&getCelda(fila,col).getValor()==tablero[fila][j].getValor()) {
						resultado=false;buscado=tablero[fila][j];
						
				}
			}
		}
		
		return buscado;
	}
	public Celda columnaRepetidos(int fila,int col) {
		boolean resultado=true;
		Celda buscado=null;
		
		/*Verifica si la celda pasada por parametros se encuentra repetida en su misma columna */

		if(getCelda(fila,col).getValor()!=null) {
			for(int i=0;i<cantFilas&&resultado;i++) {
				if(i!=fila&&getCelda(fila,col).getValor()==tablero[i][col].getValor()) {
						resultado=false;buscado=tablero[i][col];
				}
			}
		}
		return buscado;
	}
	
	public Celda panelRepetidos(int fil,int col) {
		boolean resultado=true;
		Celda buscado=null;
		int filaPanel=(fil/cantFilaSubPanel )*cantFilaSubPanel ;
		int colPanel=(col/cantFilaSubPanel )*cantFilaSubPanel ;
		
		
		/*Verifica si la celda pasada por parametros se encuentra repetida en su mismo panel */
		if(getCelda(fil,col).getValor()!=null) {

			for(int i=filaPanel;i<filaPanel+cantFilaSubPanel &&resultado;i++) {
				for(int j=colPanel;j<colPanel+cantFilaSubPanel &&resultado;j++) {
					if(i!=fil||j!=col) {
						if(celdasIguales(fil,col,i,j)) {resultado=false;buscado=tablero[i][j];}
					}
				}
			}
		}
		return buscado;
		
		
	}
	
	
	private boolean celdasIguales(int fila1,int col1,int fila2 ,int col2) 
	{
		boolean resultado=true;
		/*Compara las celdas por valor*/
		resultado=tablero[fila1][col1].getValor()==tablero[fila2][col2].getValor();
		
		return resultado;
		
	}
	public boolean Gano() {

		boolean correcta=true;
		
		/*Verifica que se cumplan todas las reglas del juego, osea si gana o no el juego*/
		for(int i=0;i<cantFilas&&correcta;i++) {
			for(int j=0;j<cantFilas&&correcta;j++) {
				correcta=panelRepetidos(i,j)==null&&filaRepetidos(i,j)==null&&columnaRepetidos(i,j)==null&&getCelda(i,j).getValor()!=null;
			}
		}
		
		return correcta;
	}
	public void eliminar() {
		Random rand = new Random();
		int contador=0;
		
		/*Elimino celdas random, este metodo solo se usa una vez veificada la solucion pasada por archivo*/
		while(contador<45) {
			int i = rand.nextInt(9);
			int j= rand.nextInt(9);
			if(tablero[i][j].getValor()!=null) {
				tablero[i][j]=new Celda(i,j);
				tablero[i][j].setModificar(true);
				contador++;
			}
			
				
			
		}
	}
}
