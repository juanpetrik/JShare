package br.petrik.jshare.cliente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import br.petrik.jshare.comum.interfaces.Cliente;
import br.petrik.jshare.comum.pojos.Arquivo;

public class ModelArquivo extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Map<Cliente, List<Arquivo>> ArquivosListados = new HashMap<>();

	private Object[][] matriz;

	private int linhas;

	public ModelArquivo(Map<Cliente, List<Arquivo>> lista) {
		this.ArquivosListados = lista;

		linhas = 0;

		for (Entry<Cliente, List<Arquivo>> e : ArquivosListados.entrySet()) {
			linhas += e.getValue().size();
		}

		matriz = new Object[linhas][5];

		int linha = 0;
		for (Entry<Cliente, List<Arquivo>> e : ArquivosListados.entrySet()) {

			for (Arquivo arq : e.getValue()) {

				matriz[linha][0] = e.getKey().getNome();
				matriz[linha][1] = e.getKey().getIp();
				matriz[linha][2] = e.getKey().getPorta();
				matriz[linha][3] = arq.getNome();
				matriz[linha][4] = arq.getTamanho();

				linha++;
			}

		}
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return linhas;
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		// nome do arquivo
		case 0:
			return matriz[row][0];

		case 1:
			return matriz[row][1];

		case 2:
			return matriz[row][2];

		case 3:
			return matriz[row][3];

		case 4:
			return matriz[row][4];

		default:
			return "";
		}
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		// nome do arquivo
		case 0:
			return "Nome do Cliente";

		case 1:
			return "Ip do Cliente";

		case 2:
			return "Porta do Cliente";

		case 3:
			return "Nome do Arquivo";

		case 4:
			return "Tamanho do Arquivo";

		default:
			return "";
		}
	}

}
