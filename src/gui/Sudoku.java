package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import logica.Celda;
import logica.Juego;
import logica.Reloj;

public class Sudoku extends JFrame {

	private JPanel contentPane;
	private JPanel[][] paneles;
	private Juego juego;
	private Timer reloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku frame = new Sudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sudoku() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 469);
		setTitle("Sudoku");
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("/img/icono.png")).getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.MAGENTA));
		
		/*Se crea la interfaz del sudoku*/
		
		juego = new Juego();
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JPanel panelReloj = new JPanel();
		panelReloj.setBackground(Color.black);

		JLabel SegundosA = new JLabel();
		JLabel SegundosB = new JLabel();
		SegundosA.setOpaque(true);
		SegundosB.setOpaque(true);
		SegundosA.setBackground(Color.white);
		SegundosB.setBackground(Color.white);
		JLabel MinutosA = new JLabel();
		JLabel MinutosB = new JLabel();
		MinutosA.setOpaque(true);
		MinutosB.setOpaque(true);
		MinutosA.setBackground(Color.gray);
		MinutosB.setBackground(Color.gray);
		
		JLabel HorasA = new JLabel();
		JLabel HorasB = new JLabel();
		HorasA.setOpaque(true);
		HorasB.setOpaque(true);
		HorasA.setBackground(Color.white);
		HorasB.setBackground(Color.white);
				
		panel.add(panelReloj);
		panelReloj.add(HorasA);
		panelReloj.add(HorasB);
		panelReloj.add(MinutosA);
		panelReloj.add(MinutosB);
		panelReloj.add(SegundosA);
		panelReloj.add(SegundosB);
		
		Reloj a=new Reloj();
		SegundosA.setIcon(a.getEntidad().actualizar(0));
		SegundosB.setIcon(a.getEntidad().actualizar(0));
		MinutosA.setIcon(a.getEntidad().actualizar(0));
		MinutosB.setIcon(a.getEntidad().actualizar(0));
		HorasA.setIcon(a.getEntidad().actualizar(0));
		HorasB.setIcon(a.getEntidad().actualizar(0));
		
		/*En el oyente del reloj se actualizan las imagenes de cada parte el reloj*/
		ActionListener accion=new ActionListener() {
		public void actionPerformed(ActionEvent u)
		    {
				a.sumar();
				int tiempo=a.getSegundos();

				if(tiempo<10) {
					SegundosA.setIcon(a.getEntidad().actualizar(0));
					SegundosB.setIcon(a.getEntidad().actualizar(tiempo));	
				}
				else {
					
					SegundosB.setIcon(a.getEntidad().actualizar(tiempo%10));
					SegundosA.setIcon(a.getEntidad().actualizar(tiempo=tiempo/10));
				}
				tiempo=a.getMinutos();
				if(tiempo<10) {
					MinutosA.setIcon(a.getEntidad().actualizar(0));
					MinutosB.setIcon(a.getEntidad().actualizar(tiempo));
					
				}
				else {
					
					MinutosB.setIcon(a.getEntidad().actualizar(tiempo%10));
					MinutosA.setIcon(a.getEntidad().actualizar(tiempo/10));
				}
				tiempo=a.getHoras();
				if(tiempo<10) {
					HorasA.setIcon(a.getEntidad().actualizar(0));
					HorasB.setIcon(a.getEntidad().actualizar(tiempo));
					
				}
				else {
					
					HorasB.setIcon(a.getEntidad().actualizar(tiempo%10));
					HorasA.setIcon(a.getEntidad().actualizar(tiempo/10));
				}
		        
		     }
		};
		
		reloj = new Timer(1000, accion);
		
		/*Se crean los paneles del sudoku */
		
		int cantFilaSubPanel = juego.getCantFilaSubPanel();
		JPanel Grilla= new JPanel();
		Grilla.setLayout(new GridLayout(cantFilaSubPanel,cantFilaSubPanel,0,0));
		contentPane.add(Grilla, BorderLayout.CENTER);
		Grilla.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.MAGENTA));

		paneles = new JPanel[cantFilaSubPanel ][cantFilaSubPanel ];
		Grilla.setVisible(false);
		for (int i =0; i< cantFilaSubPanel; i++){
			for (int j =0; j< cantFilaSubPanel; j++){
					paneles[i][j] = new JPanel();
					paneles[i][j].setLayout(new GridLayout(cantFilaSubPanel,cantFilaSubPanel,0,0));
					paneles[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.MAGENTA));
					Grilla.add(paneles[i][j]);
			}
		}
		
		/*Se crea el boton con el que arranca la ejecucion del programa y se configura*/	
		JButton BotonEmpezar = new JButton("Empezar");
		BotonEmpezar.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 11));
		BotonEmpezar.setBackground(Color.PINK);
		contentPane.add(BotonEmpezar, BorderLayout.SOUTH);
		BotonEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arranque();
				Grilla.setVisible(true);
				reloj.start();
				BotonEmpezar.setVisible(false);
				BotonEmpezar.setEnabled(false);	
			}
		});
	}
	private void arranque() {
		if(juego.getTablero()==null) {
			/*Esto pasa cuando la solucion pasada por el archivo en la Clase juego no es valida*/
			JOptionPane.showMessageDialog(null, "El juego no ha podido iniciarse");
			reloj.stop();
			System.exit(5);

		}
		else {
			int cantFilaSubPanel = juego.getCantFilaSubPanel();
			for (int i = 0; i <juego.getCantFilas(); i++) {
				int m = (int) i / cantFilaSubPanel;
				for(int j =0; j<juego.getCantFilas(); j++) {
					int n = (int) j / cantFilaSubPanel;
					Celda c = juego.getCelda(i,j);
					ImageIcon grafico = c.getEntidadGrafica().getGrafico();
					JLabel label=c.getEntidadGrafica().getLabel();
					label.setOpaque(true);
	                label.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
	                label.setBackground(Color.WHITE);
					paneles[m][n].add(label);
					
	
					label.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							reDimensionar(label, grafico);
							label.setIcon(grafico);
						}
					});
					
					label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							Celda celdaColumna=null;
							Celda celdaFila=null;
							Celda celdaPanel=null;
							boolean bandera=true;
							
							juego.accionar(c);
							reDimensionar(label,grafico);
							
							/*Recorre todas las celdas del sudoku verificando si cumplen las reglas del juego, marcandolas sino se cumplen*/
							
							for(int i=0;i<juego.getCantFilas();i++) {
								for(int j=0;j<juego.getCantFilas();j++) {
									bandera=true;
									celdaColumna=juego.columnaRepetidos(i,j);
									celdaFila=juego.filaRepetidos(i,j);
									celdaPanel=juego.panelRepetidos(i,j);
									
									if(celdaColumna!=null) {	
						                if(c.getModificar()&&juego.getCelda(i, j).getEntidadGrafica().getLabel()==label) {label.setBackground(new Color(255,0,150));}
						                celdaColumna.getEntidadGrafica().getLabel().setBackground(new Color(255,0,150));
						                bandera=false;

									}
									
						            if(celdaFila!=null) {
						                if(c.getModificar()&&juego.getCelda(i, j).getEntidadGrafica().getLabel()==label) {label.setBackground(new Color(255,0,150));}
						                celdaFila.getEntidadGrafica().getLabel().setBackground(new Color(255,0,150));
							            bandera=false;
							            
						            }
						            if(celdaPanel!=null) {
						                if(c.getModificar()&&juego.getCelda(i, j).getEntidadGrafica().getLabel()==label) {label.setBackground(new Color(255,0,150));}
						                celdaPanel.getEntidadGrafica().getLabel().setBackground(new Color(255,0,150));
								        bandera=false;
								    }
						                	
							        if(bandera) {
							            juego.getCelda(i, j).getEntidadGrafica().getLabel().setBackground(Color.WHITE);						            
							            
							        }
							        celdaColumna=null;
							        celdaFila=null;
							        celdaPanel=null;
								}
							}
							
							/*Verifica si gano para poner un cartel y terminar con el juego*/
							if(bandera&&juego.Gano()) {
								reloj.stop();
								JOptionPane.showMessageDialog(null, "Gano");
								System.exit(5);
	
							}
						}
						
					});
				}
			}
		}
	}
	
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}

}
