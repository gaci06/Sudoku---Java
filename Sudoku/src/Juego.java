

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Random;
 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
 
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
 
public class Juego extends JDialog {
	int dificultad = 0;
	boolean victoria = false;
 
	int[][] sudokuResuelto = 
	{ 
	{ 5, 3, 4, 6, 7, 8, 9, 1, 2 },
	{ 6, 7, 2, 1, 9, 5, 3, 4, 8 },
	{ 1, 9, 8, 3, 4, 2, 5, 6, 7 },
	{ 8, 5, 9, 7, 6, 1, 4, 2, 3 },
	{ 4, 2, 6, 8, 5, 3, 7, 9, 1 },
	{ 7, 1, 3, 9, 2, 4, 8, 5, 6 },
	{ 9, 6, 1, 5, 3, 7, 2, 8, 4 },
	{ 2, 8, 7, 4, 1, 9, 6, 3, 5 },
	{ 3, 4, 5, 2, 8, 6, 1, 7, 9 }
	};
 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
 
	JTextField[][] tablero = new JTextField[9][9];
	private JLabel lblNewLabel;
	
	public Juego(JFrame ventana, boolean blockWindow) {
		super(ventana, "SUDOKU", blockWindow);
		setResizable(false);
	}
 
	/**
	 * Create the frame.
	 */
	public void iniciarJuego() {
		Random random = new Random();
		setBackground(new Color(191, 195, 188));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1157, 747);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(137, 255, 116));
		contentPane.setBackground(new Color(187,187,187));
		contentPane.setBorder(new LineBorder(new Color(110,110,110)));
 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("SUDOKU");
		lblNewLabel.setBounds(500, 8, 244, 82);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 40));
		contentPane.add(lblNewLabel);
 
		JPanel panelJuego = new JPanel();
		panelJuego.setBackground(new Color(145,42,42));
		panelJuego.setBorder(new LineBorder(new Color(145,42,42), 2)); //borde de fuera
		panelJuego.setBounds(250, 65, 600, 545);
		contentPane.add(panelJuego);
		panelJuego.setLayout(new GridLayout(9, 9, 0, 0));

		HashSet<Integer> casillasVacias = new HashSet<>();
		int huecosVacios = numeroHuecos();
		while (casillasVacias.size() < huecosVacios) {	
	        int posicion = random.nextInt(81);
	        casillasVacias.add(posicion); 
	    }
 
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				JTextField casilla = new JTextField();
				casilla.setHorizontalAlignment(SwingConstants.CENTER);
				casilla.setForeground(Color.WHITE);
				casilla.setFont(new Font("Monospaced", Font.BOLD, 23));
				casilla.setBackground(Color.lightGray);

				// Calcular la posición lineal actual
	            int posicionActual = fila * 9 + columna;
 
	         // Verificar si la posición actual está en el conjunto de posiciones para vaciar
	            if (casillasVacias.contains(posicionActual)) {
	                casilla.setText("");
	            } else {
	            	 // Convertir el valor de sudokuResuelto a String y asignarlo
	                casilla.setText(String.valueOf(sudokuResuelto[fila][columna]));
	                casilla.setEditable(false); // Hacer la casilla no editable
	            }
 
				// Función para el borde de 3x3
				int top = (fila % 3 == 0) ? 2 : 0;
				int left = (columna % 3 == 0) ? 2 : 0;	
				int bottom = (fila == 8) ? 2 : 0;
				int right = (columna == 8) ? 2 : 0;
 
				// Borde Celda
				LineBorder bordeCelda = new LineBorder(new Color(83, 189, 184), 3);
 
				// Borde para el 3x3
				MatteBorder bordeCuadriculaPequeña = BorderFactory.createMatteBorder(top, left, bottom, right, Color.YELLOW);
 
				// Combinar bordes
				CompoundBorder bordeCuadricula = new CompoundBorder(bordeCuadriculaPequeña, bordeCelda);
 
				// Asignamos el borde
				casilla.setBorder(bordeCuadricula);
				
				// Asumiendo que "casilla" es un JTextField
				casilla.getDocument().addDocumentListener(new DocumentListener() {
				    public void insertUpdate(DocumentEvent e) {
				        verificarSudoku();
				    }
				    private void verificarSudoku() {
				        boolean victoria = comprobacionSudoku();
				        if (victoria) {
				            JOptionPane.showMessageDialog(contentPane, "¡ENHORABUENA! Has finalizado el Sudoku correctamente.", "¡SUDOKU COMPLETADO CORRECTAMENTE!", JOptionPane.INFORMATION_MESSAGE);
				        }
				    }

					public void removeUpdate(DocumentEvent e) {	
					}
					public void changedUpdate(DocumentEvent e) {
					}
				});
				panelJuego.add(casilla);
				tablero[fila][columna] = casilla;
			}
		}
 
		this.setVisible(true);
	}
	
	//Huecos que dejan depende de la dificultad
 
	private int numeroHuecos() {
		if(dificultad == 1)
			return 1;
		if(dificultad == 2) 
			return 40;
		if(dificultad == 3)
			return 50;
		else
			return 0;
	}
	private boolean comprobacionSudoku() {
	    for (int i = 0; i < 9; i++) {
	        HashSet<Integer> fila = new HashSet<>();
	        HashSet<Integer> columna = new HashSet<>();
	        for (int j = 0; j < 9; j++) {
	          
	        	  // Verificar filas
	            String valorFila = tablero[i][j].getText().trim();
	            if (!valorFila.isEmpty()) {
	                int numFila = Integer.parseInt(valorFila);
	                if (numFila < 1 || numFila > 9 || !fila.add(numFila)) return false;
	            } else {
	                return false;// Casilla vacía
	            }
	            
	         // Verificar columnas
	            String valorColumna = tablero[j][i].getText().trim();
	            if (!valorColumna.isEmpty()) {
	                int numColumna = Integer.parseInt(valorColumna);
	                if (numColumna < 1 || numColumna > 9 || !columna.add(numColumna)) return false;
	            } else {
	                return false;  // Casilla vacía
	            }
	        }
	    }
	    // Verificación de bloques 3x3
	    for (int bloque = 0; bloque < 9; bloque++) {
	        HashSet<Integer> cuadro = new HashSet<>();
	        for (int i = 0; i < 9; i++) {
	            int fila = 3 * (bloque / 3) + (i / 3);
	            int columna = 3 * (bloque % 3) + (i % 3);
	            String valor = tablero[fila][columna].getText().trim();
	            if (!valor.isEmpty()) {
	                int num = Integer.parseInt(valor);
	                if (num < 1 || num > 9 || !cuadro.add(num)) return false;
	            } else {
	                return false; // Casilla vacía
	            }
	        }
	    }
	    return true;
	}
 
 
 
	public int getDificultad() {
		return dificultad;
	}
 
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}
}