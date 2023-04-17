package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.hotel.controller.HuespedesController;
import com.hotel.controller.ReservaController;
import com.hotel.modelo.Huesped;
import com.hotel.modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private HuespedesController huespedController;
	private ReservaController reservaController;
	private List<Huesped> huesped;
	private List<Reserva> reservas;
	private int eliminado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedesController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);

		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("ID de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();		
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				if(tbReservas.isShowing()) {
					int rows = modelo.getRowCount() - 1;					
					if (rows > 0) {
						for(int i = rows;i >=rows;i--) {
							modelo.removeRow(i);
						}
					}					
						
					ListarReservas();
				}else if (tbHuespedes.isShowing()) {
					int rows = modeloHuesped.getRowCount() - 1;
					if (rows > 0) {
						for(int i = rows;i >=rows;i--) {
							modelo.removeRow(i);
						}
					}					
					Listar();
				}
			}
		});
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");		
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tbReservas.isShowing()) {
					eliminado = ModificarReservas();
				}else if (tbHuespedes.isShowing()) {				
					eliminado = ModificarHuespedes();					
				}				
				if (eliminado > 1) {
					Mensaje("!<b>"+eliminado+"</b> Huespedes modificados exitosamente!", "Cambio Huespedes");
				}else if(eliminado == 1) {
					Mensaje("!<b>" + eliminado + "</b> Huesped modificado exitosamente", "Cambio Huespedes");
				}
				
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				if(tbReservas.isShowing()) {
					eliminado = EliminarReservas();
				}else if (tbHuespedes.isShowing()) {				
					eliminado = Eliminar();					
				}
				if (eliminado > 1) {
					Mensaje("¡<b>"+ eliminado + "</b> registros eliminados exitosamente!", "Eliminando registros");
				}else if (eliminado == 1) {
					Mensaje("¡<b>"+ eliminado + "</b> registro eliminado exitosamente!", "Eliminando registros");
				}
				
			}
		});
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	protected int ModificarHuespedes() {
		Huesped huespedes = new Huesped(
				modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString(),
				modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString(),
				modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString(),
				modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString(),
				modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString(),
				modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString(),
				Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString())						
				);
		
		
		return huespedController.modificar(huespedes);
	}

	protected int ModificarReservas() {
		Reserva reserva = new Reserva(
				Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString()),
				modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString(),
				modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString(),
				Float.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString()),
				modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString()									
				);
		return reservaController.Modificar(reserva);
	}

	protected int Eliminar() {
		String id =  modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString();
		eliminado = huespedController.Eliminar(id);
		modeloHuesped.removeRow(tbHuespedes.getSelectedRow()); 
		
		return eliminado;
	}

	protected int EliminarReservas() {
		String id =  modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString();
		eliminado = reservaController.Eliminar(id);
		modelo.removeRow(tbReservas.getSelectedRow()); 
		
		return eliminado;
		
	}

	protected void Mensaje(String msg, String titulo) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "<html>"+msg+"</html",titulo, WIDTH );
	}

	protected void ListarReservas() {
		var buscar = txtBuscar.getText();
		int id;		
		reservas = new ArrayList<>();			
		if(buscar.isEmpty()) {
			reservas = reservaController.Listar();
			Listado(reservas);			
		}else {
			try {
				id = Integer.parseInt(buscar);
				reservas = reservaController.ListaPersonalizada(id);
				if (reservas.isEmpty()) {
					Mensaje("No hay coincidencia con niguna reserva", "¡Sin Coincidencias!");
				}else {
				Listado(reservas);
				}
			}catch(NumberFormatException e){
				Mensaje("Las reservas se Listan por ID","¡Error en el ingreso!");					
			}
		}		
	}

	private void ListarHuespedes(List<Huesped> huesped) {
		
		huesped.forEach(huespedes -> modeloHuesped.addRow(new Object
				[] {huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(),
						huespedes.getFechaNacimiento(), huespedes.getNacionalidad(),
						huespedes.getTelefono(), huespedes.getIdReserva()					
				}));
	}
	
	protected void Listado(List<Reserva> reserva) {		
		reserva.forEach(reservas -> modelo.addRow(new Object [] {
				reservas.getId(), reservas.getFechaEntrada(), reservas.getFechaSalida(),
				reservas.getValor(), reservas.getFormaDePago()
		}));
	}
	
	protected void Listar() {
		String buscar = txtBuscar.getText();		
		if (buscar.isEmpty()) {
			huesped  = new ArrayList<>();
			huesped = huespedController.Listar();
			
			ListarHuespedes(huesped);
			
		}else {
			huesped  = new ArrayList<>();
			huesped = huespedController.listaPersonalizada(buscar);
			if (huesped.isEmpty()) {
				Mensaje("No hay coincidencia con nigun huesped", "¡Sin Coincidencias!");
			}else {
			ListarHuespedes(huesped);
			}
		}
	}

	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
