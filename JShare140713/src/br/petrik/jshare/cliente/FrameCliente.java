package br.petrik.jshare.cliente;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.petrik.jshare.comum.interfaces.Cliente;
import br.petrik.jshare.comum.interfaces.IServer;
import br.petrik.jshare.comum.pojos.Arquivo;
import br.petrik.jshare.comum.pojos.Diretorio;

public class FrameCliente extends JFrame implements IServer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCliente frame = new FrameCliente();
					frame.setVisible(true);
					frame.configurar();
					frame.setBounds(0, 350, 800, 300);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 48, 162, 34, 65, 82, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblCliente = new JLabel("JShare");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.gridwidth = 5;
		gbc_lblCliente.insets = new Insets(0, 0, 5, 0);
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 0;
		contentPane.add(lblCliente, gbc_lblCliente);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		contentPane.add(lblNome, gbc_lblNome);

		txtNome = new JTextField();
		txtNome.setText("Juan Pablo");
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 1;
		contentPane.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JLabel lblIp = new JLabel("IP Servidor");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 2;
		contentPane.add(lblIp, gbc_lblIp);

		txtIP = new JTextField();
		txtIP.setText("127.0.0.1");
		GridBagConstraints gbc_txtIP = new GridBagConstraints();
		gbc_txtIP.insets = new Insets(0, 0, 5, 5);
		gbc_txtIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIP.gridx = 1;
		gbc_txtIP.gridy = 2;
		contentPane.add(txtIP, gbc_txtIP);
		txtIP.setColumns(10);

		JLabel lblPorta = new JLabel("Porta");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.anchor = GridBagConstraints.EAST;
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 2;
		gbc_lblPorta.gridy = 2;
		contentPane.add(lblPorta, gbc_lblPorta);

		txtPorta = new JTextField();
		txtPorta.setText("1818");
		txtPorta.setColumns(10);
		GridBagConstraints gbc_txtPorta = new GridBagConstraints();
		gbc_txtPorta.insets = new Insets(0, 0, 5, 5);
		gbc_txtPorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPorta.gridx = 3;
		gbc_txtPorta.gridy = 2;
		contentPane.add(txtPorta, gbc_txtPorta);

		lblSeuIp = new JLabel("Seu IP");
		GridBagConstraints gbc_lblSeuIp = new GridBagConstraints();
		gbc_lblSeuIp.anchor = GridBagConstraints.EAST;
		gbc_lblSeuIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeuIp.gridx = 0;
		gbc_lblSeuIp.gridy = 3;
		contentPane.add(lblSeuIp, gbc_lblSeuIp);

		txtMyIP = new JTextField();
		txtMyIP.setText("192.168.65.1");
		txtMyIP.setColumns(10);
		GridBagConstraints gbc_txtMyIP = new GridBagConstraints();
		gbc_txtMyIP.insets = new Insets(0, 0, 5, 5);
		gbc_txtMyIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMyIP.gridx = 1;
		gbc_txtMyIP.gridy = 3;
		contentPane.add(txtMyIP, gbc_txtMyIP);

		label_1 = new JLabel("Porta");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 3;
		contentPane.add(label_1, gbc_label_1);

		txtMyPort = new JTextField();
		txtMyPort.setText("1819");
		txtMyPort.setColumns(10);
		GridBagConstraints gbc_txtMyPort = new GridBagConstraints();
		gbc_txtMyPort.insets = new Insets(0, 0, 5, 5);
		gbc_txtMyPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMyPort.gridx = 3;
		gbc_txtMyPort.gridy = 3;
		contentPane.add(txtMyPort, gbc_txtMyPort);
		
		lblNomeDoArquivo = new JLabel("Nome do Arquivo");
		GridBagConstraints gbc_lblNomeDoArquivo = new GridBagConstraints();
		gbc_lblNomeDoArquivo.anchor = GridBagConstraints.EAST;
		gbc_lblNomeDoArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeDoArquivo.gridx = 0;
		gbc_lblNomeDoArquivo.gridy = 5;
		contentPane.add(lblNomeDoArquivo, gbc_lblNomeDoArquivo);
		
		txtNomeArquivo = new JTextField();
		txtNomeArquivo.setColumns(10);
		GridBagConstraints gbc_txtNomeArquivo = new GridBagConstraints();
		gbc_txtNomeArquivo.gridwidth = 3;
		gbc_txtNomeArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_txtNomeArquivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNomeArquivo.gridx = 1;
		gbc_txtNomeArquivo.gridy = 5;
		contentPane.add(txtNomeArquivo, gbc_txtNomeArquivo);
		
		btnPesquisar = new JButton("Pesquisar");
		GridBagConstraints gbc_btnPesquisar = new GridBagConstraints();
		gbc_btnPesquisar.insets = new Insets(0, 0, 5, 0);
		gbc_btnPesquisar.gridx = 4;
		gbc_btnPesquisar.gridy = 5;
		contentPane.add(btnPesquisar, gbc_btnPesquisar);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setColumnHeaderView(table);

		btnConectar = new JButton("Conectar");
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.anchor = GridBagConstraints.EAST;
		gbc_btnConectar.insets = new Insets(0, 0, 0, 5);
		gbc_btnConectar.gridx = 3;
		gbc_btnConectar.gridy = 7;
		contentPane.add(btnConectar, gbc_btnConectar);

		btnDesconectar = new JButton("Desconectar");
		GridBagConstraints gbc_btnDesconectar = new GridBagConstraints();
		gbc_btnDesconectar.gridx = 4;
		gbc_btnDesconectar.gridy = 7;
		contentPane.add(btnDesconectar, gbc_btnDesconectar);
	}

	private IServer servidor;
	private Cliente cliente;
	private Registry registry;
	private String meunome;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JTextField txtNome;
	private JTextField txtIP;
	private JTextField txtPorta;
	private JLabel lblSeuIp;
	private JLabel label_1;
	private JTextField txtMyIP;
	private JTextField txtMyPort;
	private JLabel lblNomeDoArquivo;
	private JButton btnPesquisar;
	private JTextField txtNomeArquivo;
	
	private ModelArquivo modelo;

	public void configurar() {

		btnConectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});

		btnDesconectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desconectar();
			}
		});

		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				consultarArquivos();
			}
		});
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				desconectar();
			}
		});
	}

	protected void consultarArquivos() {
		try {
			Map<Cliente, List<Arquivo>> ArquivosListados = servidor.procurarArquivo(txtNomeArquivo.getText());
			
			modelo = new ModelArquivo(ArquivosListados);
			
			table.setModel(modelo);
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Conecta no servidor.
	 */
	protected void conectar() {

		meunome = txtNome.getText().trim();
		if (meunome.length() == 0) {
			JOptionPane.showMessageDialog(this, "Você precisa digitar um nome!");
			return;
		}

		// Endereço IP
		String host = txtIP.getText().trim();
		if (!host.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			JOptionPane.showMessageDialog(this, "O endereço ip parece inválido!");
			return;
		}

		// Porta
		String strPorta = txtPorta.getText().trim();
		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no máximo 5 dígitos!");
			return;
		}
		int intPorta = Integer.parseInt(strPorta);

		// Iniciando objetos para conexão.
		try {
			registry = LocateRegistry.getRegistry(host, intPorta);

			servidor = (IServer) registry.lookup(IServer.NOME_SERVICO);
			// cliente = (Cliente) UnicastRemoteObject.exportObject(this, 0);

			cliente = new Cliente();
			cliente.setIp(txtMyIP.getText());
			cliente.setPorta(Integer.parseInt(txtMyPort.getText()));
			cliente.setNome(txtNome.getText());

			// Avisando o servidor que está entrando no Chat.
			servidor.registrarCliente(cliente);

			// Publicar minha lista de arquivos...
			List<Arquivo> lista = getMyListArchives();

			servidor.publicarListaArquivos(cliente, lista);
			
			btnDesconectar.setEnabled(true);
			btnConectar.setEnabled(false);
			txtNome.setEnabled(false);
			txtIP.setEnabled(false);
			txtPorta.setEnabled(false);
			txtMyIP.setEnabled(false);
			txtMyPort.setEnabled(false);

			btnConectar.setEnabled(false);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	private List<Arquivo> getMyListArchives() {
		File dirStart = new File("C:\\JShare\\Uploads\\");

		List<Arquivo> listaArquivos = new ArrayList<>();

		for (File file : dirStart.listFiles()) {
			if (file.isFile()) {
				Arquivo arquivo = new Arquivo();
				arquivo.setNome(file.getName());
				arquivo.setTamanho(file.length());
				listaArquivos.add(arquivo);
			}
		}

		/*
		 * System.out.println("Arquivos"); for (Arquivo arq : listaArquivos) {
		 * System.out.println("\t" + arq.getTamanho() + "\t" + arq.getNome()); }
		 */

		return listaArquivos;
	}

	/**
	 * Desconecta do servidor.
	 */
	protected void desconectar() {
		try {

			if (servidor != null) {
				servidor.desconectar(cliente);
				// UnicastRemoteObject.unexportObject(this, true); Por algum
				// motivo nao ta funfando..

				servidor = null;
			}

			// mostrar("Você saiu do chat.");

			btnDesconectar.setEnabled(false);

			btnConectar.setEnabled(true);
			txtNome.setEnabled(true);
			txtIP.setEnabled(true);
			txtPorta.setEnabled(true);
			txtMyIP.setEnabled(true);
			txtMyPort.setEnabled(true);

			btnConectar.setEnabled(true);

			registry = null;
			servidor = null;

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub

	}
}
