import java.awt.EventQueue;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
 
public class J_Sudoku extends JFrame {
 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> dificultad;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					J_Sudoku frame = new J_Sudoku();
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
	public J_Sudoku() {
		setSize(new Dimension(1160, 710));
		setTitle("MENÚ PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 780);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(800, 800));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
 
		setContentPane(contentPane);
		JButton bStart = new JButton("Comenzar Partida");
		bStart.setFocusable(false);
		bStart.setForeground(new Color(255, 255, 255));
		bStart.setBounds(79, 369, 449, 150);
		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Juego juego = new Juego(J_Sudoku.this, true);
				int dificultad_juego = juego.getDificultad();
				String dificultad_seleccionada = (String) dificultad.getSelectedItem();
				if(dificultad_seleccionada.equals("FÁCIL"))
					dificultad_juego = 1;
				if(dificultad_seleccionada.equals("MEDIO"))
					dificultad_juego = 2;
				if(dificultad_seleccionada.equals("DIFÍCIL"))
					dificultad_juego = 3;
				juego.setDificultad(dificultad_juego);
				juego.iniciarJuego();
			}
		});
		contentPane.setLayout(null);
		// Configuración del botón de comienzo
		bStart.setBackground(Color.BLUE); 
		bStart.setFont(new Font("Monospaced", Font.BOLD, 40));
		contentPane.add(bStart);

		// Configuración del JLabel para la dificultad
		JLabel lMode = new JLabel("Dificultad:");
		lMode.setBounds(108, 181, 163, 60);
		lMode.setFont(new Font("Monospaced", Font.BOLD, 20));
		contentPane.add(lMode);

		// Configuración del ComboBox para establecer la dificultad
		dificultad = new JComboBox<String>();
		dificultad.setFocusable(false);
		dificultad.setBounds(281, 198, 138, 43);
		dificultad.setModel(new DefaultComboBoxModel<String>(new String[] {"FÁCIL", "MEDIO", "DIFÍCIL"}));
		contentPane.add(dificultad);
		
		//JLabel
		lblNewLabel = new JLabel("SUDOKU");
		lblNewLabel.setBounds(450, 10, 244, 82);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 40));
		lblNewLabel.setForeground(Color.white);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("REGLAS");
		lblNewLabel_1.setBounds(735, 152, 100, 82);
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 25));
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("1.-		Hay que completar las casillas vacías con un solo número del 1 al 9.\r\n");
		lblNewLabel_2.setBounds(572, 223, 522, 82);
		lblNewLabel_2.setFont(new Font("Monospaced", Font.BOLD, 12));
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("2.-		En una misma fila no puede haber números repetidos.\r\n");
		lblNewLabel_3.setBounds(572, 280, 534, 108);
		lblNewLabel_3.setFont(new Font("Monospaced", Font.BOLD, 12));
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("3.-		En una misma columna no puede haber números repetidos.\r\n");
		lblNewLabel_4.setBounds(572, 339, 534, 108);
		lblNewLabel_4.setFont(new Font("Monospaced", Font.BOLD, 12));
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("4.-		En un mismo recuadro de 3x3 no puede haber números repetidos.");
		lblNewLabel_5.setBounds(572, 399, 534, 108);
		lblNewLabel_5.setFont(new Font("Monospaced", Font.BOLD, 12));
		contentPane.add(lblNewLabel_5);
		
		panel_1 = new JPanel();
		panel_1.setBounds(40, 82, 1039, 501);
		contentPane.setBackground(new Color(20, 20, 20));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
	}
}