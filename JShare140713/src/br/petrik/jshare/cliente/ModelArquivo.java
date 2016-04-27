package br.petrik.jshare.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import br.petrik.jshare.comum.interfaces.Cliente;
import br.petrik.jshare.comum.pojos.Arquivo;

public class ModelArquivo extends AbstractTableModel {

	Map<Cliente, List<Arquivo>> ArquivosListados = new HashMap<>();

	Map<Arquivo, Cliente> listaConvertida = new HashMap<>();

	public ModelArquivo(Map<Cliente, List<Arquivo>> lista) {
		this.ArquivosListados = lista;
	}

	@Override
	public int getColumnCount() {
		// Nome arquivo, Tamanho Arquivo, Nome Cliente..
		return 3;
	}

	@Override
	public int getRowCount() {
		return ArquivosListados.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		converterLista();		
		
		switch (col) {
		// nome do arquivo
		case 0: 

			break;

		case 1:

			break;

		case 2:

			break;

		default:
			break;
		}

		return null;
	}

	private void converterLista() {
		for (Cliente key : ArquivosListados.keySet()) {

			List<Arquivo> arquivos = ArquivosListados.get(key);

			for (Arquivo arquivo : arquivos) {
				listaConvertida.put(arquivo, key);
			}
		}

	}

}
