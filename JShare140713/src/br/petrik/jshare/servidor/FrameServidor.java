package br.petrik.jshare.servidor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.petrik.jshare.comum.interfaces.Cliente;
import br.petrik.jshare.comum.interfaces.IServer;
import br.petrik.jshare.comum.pojos.Arquivo;

public class FrameServidor extends JFrame implements IServer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameServidor frame = new FrameServidor();
					frame.setVisible(true);
					frame.configurar();
					
					frame.setBounds(0, 0, 800, 300);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameServidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel label = new JLabel("IP");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		contentPane.add(label, gbc_label);
		
		comboIP = new JComboBox();
		comboIP.setPreferredSize(new Dimension(200, 24));
		comboIP.setMinimumSize(new Dimension(200, 24));
		GridBagConstraints gbc_comboIP = new GridBagConstraints();
		gbc_comboIP.anchor = GridBagConstraints.WEST;
		gbc_comboIP.insets = new Insets(0, 0, 5, 5);
		gbc_comboIP.gridx = 1;
		gbc_comboIP.gridy = 0;
		contentPane.add(comboIP, gbc_comboIP);
		
		JLabel label_1 = new JLabel("Porta");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		contentPane.add(label_1, gbc_label_1);
		
		txtPorta = new JTextField();
		txtPorta.setText("1818");
		txtPorta.setPreferredSize(new Dimension(200, 24));
		txtPorta.setMinimumSize(new Dimension(200, 24));
		GridBagConstraints gbc_txtPorta = new GridBagConstraints();
		gbc_txtPorta.anchor = GridBagConstraints.WEST;
		gbc_txtPorta.insets = new Insets(0, 0, 5, 5);
		gbc_txtPorta.gridx = 1;
		gbc_txtPorta.gridy = 1;
		contentPane.add(txtPorta, gbc_txtPorta);
		
		btnIniciar = new JButton("Iniciar Serviço");
		GridBagConstraints gbc_btnIniciar = new GridBagConstraints();
		gbc_btnIniciar.anchor = GridBagConstraints.EAST;
		gbc_btnIniciar.insets = new Insets(0, 0, 5, 5);
		gbc_btnIniciar.gridx = 3;
		gbc_btnIniciar.gridy = 1;
		contentPane.add(btnIniciar, gbc_btnIniciar);
		
		btnParar = new JButton("Parar Serviço");
		GridBagConstraints gbc_btnParar = new GridBagConstraints();
		gbc_btnParar.anchor = GridBagConstraints.EAST;
		gbc_btnParar.insets = new Insets(0, 0, 5, 0);
		gbc_btnParar.gridx = 4;
		gbc_btnParar.gridy = 1;
		contentPane.add(btnParar, gbc_btnParar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		txtArea = new JTextArea();
		txtArea.setFont(new Font("Monospaced", Font.BOLD, 16));
		txtArea.setForeground(Color.GREEN);
		txtArea.setEnabled(false);
		txtArea.setBackground(Color.BLACK);
		scrollPane.setViewportView(txtArea);
	}

	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");
	private IServer servidor;
	
	// Lista para armazenar os clientes que estão conectados..
	private Map<String, Cliente> mapaClientes = new HashMap<>();
	
	// Lista que vai armazenar os arquivos dos clientes..
	private Map<Arquivo, Cliente> mapaClienteArquivo = new HashMap<>();
	
	private Registry registry;
	
	
	private JButton btnIniciar;
	private JButton btnParar;
	private JComboBox comboIP;
	private JTextArea txtArea;
	private JTextField txtPorta;
	
	
	public void configurar() {

		btnParar.setEnabled(false);

		List<String> lista = getIpsDisponiveis();
		comboIP.setModel(new DefaultComboBoxModel<String>(new Vector<String>(lista)));
		comboIP.setSelectedIndex(0);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Configura o frame para avisar os clientes que está sendo fechado.
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//fecharTodosClientes();
			}
		});

		// Configura o botao iniciar servico.
		btnIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarServico();
			}
		});

		// Configura o botao parar servico.
		btnParar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pararServico();
			}
		});

	}
	
	
	protected void iniciarServico() {

		String strPorta = txtPorta.getText().trim();

		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no máximo 5 dígitos!");
			return;
		}
		int intPorta = Integer.parseInt(strPorta);
		if (intPorta < 1024 || intPorta > 65535) {
			JOptionPane.showMessageDialog(this, "A porta deve estar entre 1024 e 65535");
			return;
		}

		try {
	
			servidor = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(intPorta);

			/*if (System.getSecurityManager() == null){
				System.setSecurityManager(new SecurityManager());
				
			}*/
			
			
			registry.rebind(IServer.NOME_SERVICO, servidor);

			mostrar("Serviço iniciado.");

			comboIP.setEnabled(false);
			txtPorta.setEnabled(false);
			btnIniciar.setEnabled(false);

			btnParar.setEnabled(true);

		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro criando registro, verifique se a porta já não está sendo usada.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Avisa os clientes e encerra as atividades do servidor, mas não fecha a
	 * aplicação.
	 */
	protected void pararServico() {
		mostrar("SERVIDOR PARANDO O SERVIÇO.");

		//fecharTodosClientes();

		try {
			UnicastRemoteObject.unexportObject((Remote) this, true);
			UnicastRemoteObject.unexportObject(registry, true);

			comboIP.setEnabled(true);
			txtPorta.setEnabled(true);
			btnIniciar.setEnabled(true);

			btnParar.setEnabled(false);

			mostrar("Serviço encerrado.");

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
	
	private void mostrar(String string) {
		txtArea.append(sdf.format(new Date()));
		txtArea.append(" -> ");
		txtArea.append(string);
		txtArea.append("\n");
		
		// Faz a "rolagem" no textArea
		txtArea.setCaretPosition(txtArea.getText().length());
	}
	
	private List<String> getIpsDisponiveis() {

		List<String> addrList = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();

			while (ifaces.hasMoreElements()) {
				NetworkInterface ifc = ifaces.nextElement();
				if (ifc.isUp()) {
					Enumeration<InetAddress> addresses = ifc.getInetAddresses();
					while (addresses.hasMoreElements()) {

						InetAddress addr = addresses.nextElement();

						String ip = addr.getHostAddress();

						if (ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
							addrList.add(ip);
						}

					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

		return addrList;
	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mapaClientes.put(c.getIp(), c);
		mostrar("Cliente " + c.getNome() + " se conectou.");		
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		for (Arquivo arquivo : lista) {
			// Adiciono o arquivo como chave.. porque um cliente pode ter varios arquivos.. ou seja.. daria B.O
			mapaClienteArquivo.put(arquivo, c);
			mostrar("Adicionando arquivo " + arquivo.getNome() + " do cliente " + c.getNome());
		}		
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		
		Map<Cliente, List<Arquivo>> lista = new HashMap<>();
		List<Arquivo> arquivos = new ArrayList<Arquivo>();
		
		for (Entry<Arquivo, Cliente> arquivo : mapaClienteArquivo.entrySet()) {
			
			if (arquivo.getKey().getNome().toLowerCase().contains(nome.toLowerCase()) || nome.isEmpty()) {
				
				Cliente c = arquivo.getValue();
				
				arquivos.add(arquivo.getKey());
				
				lista.put(c, arquivos);	
			}
		}
		
		
		return lista;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		mapaClientes.remove(c.getIp());
		mostrar(c.getNome() + " se desconectou.");
	}
}
