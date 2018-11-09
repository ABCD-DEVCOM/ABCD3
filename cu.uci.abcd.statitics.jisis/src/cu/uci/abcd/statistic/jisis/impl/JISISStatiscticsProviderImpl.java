package cu.uci.abcd.statistic.jisis.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.unesco.jisis.corelib.client.ClientDbProxy;
import org.unesco.jisis.corelib.common.FieldSelectionTable;
import org.unesco.jisis.corelib.common.Global;
import org.unesco.jisis.corelib.exceptions.DbException;
import org.unesco.jisis.corelib.record.Record;

import cu.uci.abcd.statistic.jisis.JISISStatisticsProvider;
import cu.uci.abcd.statistic.jisis.domain.StatisticParam;
import cu.uci.abcd.statistic.jisis.domain.TabularStatistic;
import cu.uci.abos.jisis.conection.JISISConection;

public class JISISStatiscticsProviderImpl implements JISISStatisticsProvider {

	private JISISConection connectionManager;
	private ClientDbProxy proxy;

	public void bind(JISISConection connectionManager, Map<?, ?> properties) {
		this.connectionManager = connectionManager;
	}

	@Override
	public FieldSelectionTable getFieldSelectionTable(StatisticParam statisticParam) throws Exception {
		return getProxy(statisticParam).getFieldSelectionTable();
	}

	@Override
	public TabularStatistic getValuesFromLucene(StatisticParam statisticParam, TabularStatistic tab) throws Exception {
		checkIndexTags(statisticParam);

		// se obtiene la lista de Records correspondiente a la lista de
		// opciones.
		List<Record> records = findRecordsByOptions(statisticParam);
		List<String> rows = new ArrayList<String>();
		List<String> columns = new ArrayList<String>();

		// se obtiene de la lista de Records los nombres de las filas y las
		// columnas
		setHeaders(statisticParam, records, rows, columns);

		// para mejorar la visivilidad de la tabla hacemos que el indicador de
		// filas sea el de mayor tamaño.
		RowsColumsChanged(rows, columns, statisticParam);

		// se devuelve la tabla con todos los valores de la estadística.
		return buildValues(statisticParam, tab, rows, columns, records);
	}

	@Override
	public TabularStatistic getValuesFromMfn(StatisticParam statisticParam, TabularStatistic tab) throws Exception {
		List<String> rows = new ArrayList<String>();
		List<String> columns = new ArrayList<String>();

		// se verifica que las variables esten indexadas.
		checkIndexTags(statisticParam);

		// se obtiene de la base de datos de JISIS el primero y el ultimo MFN.
		int lastMFN = (int) getProxy(statisticParam).getLastMfn();
		int firstMFN = (int) getProxy(statisticParam).getFirst().getMfn();

		// se validan el rango MFN de la busqueda para que se encuntre entre el
		// primero
		// y el ultimo MFN de la base de datos.
		if (statisticParam.getEnd() < lastMFN) {
			lastMFN = statisticParam.getEnd();
		}
		if (statisticParam.getBegin() > firstMFN) {
			firstMFN = statisticParam.getBegin();
		}

		// se obtiene la lista de Records correspondiente al rango MFN deseado.
		List<Record> records = getRecords(statisticParam, firstMFN, lastMFN);

		// se obtiene de la lista de Records los nombres de las filas y las
		// columnas
		setHeaders(statisticParam, records, rows, columns);

		// para mejorar la visivilidad de la tabla hacemos que el indicador de
		// filas sea el de mayor tamaño.
		RowsColumsChanged(rows, columns, statisticParam);

		// se devuelve la tabla con todos los valores de la estadística.
		return buildValues(statisticParam, tab, rows, columns, records);
	}

	private long[] findMfnByOptions(StatisticParam statisticParam) throws Exception {
		// debuelve un arreglo con los MFN correspondientes a los resultados
		// arrojados por la busqueda.
		return getProxy(statisticParam).searchLucene(buildLuceneQuery(statisticParam));
	}

	private List<Record> findRecordsByOptions(StatisticParam statisticParam) throws Exception {
		// devuelve la lista de Records correspondientes al arreglo de MFN.
		return getRecords(statisticParam, findMfnByOptions(statisticParam));
	}

	private List<Record> getRecords(StatisticParam statisticParam, int begin, int end) throws Exception {
		// se devuelve de la base de datos los Records correspondientes a el
		// rango de MFN.
		return getProxy(statisticParam).getRecordChunck(begin - 1, end - 1);
	}

	private List<Record> getRecords(StatisticParam statisticParam, long[] mfn) throws Exception {
		// se devuelve la lista de Records si el arreglo de MFN no es vacio.
		if (mfn != null) {
			return getProxy(statisticParam).getRecordChunck(mfn);
		}
		return Collections.emptyList();
	}

	// se crea una expreción de busqueda a partir de la lista de opciones con el
	// formato _field: term
	public String buildLuceneQuery(StatisticParam statisticParam) {
		StringBuilder consulta = new StringBuilder();
		for (int i = 0; i < statisticParam.getOptions().size(); i++) {
			
				consulta.append(statisticParam.getOptions().get(i).getQuery());
			
			
		}
		return consulta.toString();
	}

	private ClientDbProxy getProxy(StatisticParam statisticParam) {
		try {
			if (proxy == null) {
				proxy = connectionManager.getProxy();
			}
			proxy.getDatabase(statisticParam.getHome(), statisticParam.getDatabase(), Global.DATABASE_UNLOCKED);
			return proxy;
		} catch (DbException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void setHeaders(StatisticParam statisticParam, List<Record> records, List<String> rows, List<String> columns) {
		// se crean dos lista Set para que los nombres de las filas y las
		// columnas no se repitan.
		Set<String> rowsTemp = new HashSet<>();
		Set<String> columnsTemp = new HashSet<>();

		// se recorre la lista de Records para llenar las Listas Set con los
		// valores de los indicadores.
		// se validan que los valores correspondientes a los indicadores no sean
		// null ni vacios.
		for (Record record : records) {
			String value;
			try {
				value = record.getField(Integer.parseInt(statisticParam.getRowTag())).getStringFieldValue();
				if (value != null && !value.isEmpty()) {
					rowsTemp.add(value);
				}
			} catch (NullPointerException | NumberFormatException | DbException e) {
				e.printStackTrace();
			}

			try {
				value = record.getField(Integer.parseInt(statisticParam.getColumTag())).getStringFieldValue();
				if (value != null && !value.isEmpty()) {
					columnsTemp.add(value);
				}
			} catch (NullPointerException | NumberFormatException | DbException e) {
				e.printStackTrace();
			}
		}

		// se crean dos iteradores para recorrer las listas Set
		Iterator<String> rowiT = rowsTemp.iterator();
		Iterator<String> coliT = columnsTemp.iterator();

		// se adiciona a las listas en la primera posicion el valor:
		// "No hay Datos".
		rows.add("No hay Datos");
		columns.add("No hay Datos");

		// se adiciona a la lista de las filas y las columnas los elementos de
		// las listas Set.
		for (int i = 0; i < rowsTemp.size(); i++)
			rows.add(rowiT.next());

		for (int i = 0; i < columnsTemp.size(); i++)
			columns.add(coliT.next());

	}

	private TabularStatistic buildValues(StatisticParam statisticParam, TabularStatistic tab, List<String> rows, List<String> columns, List<Record> records) throws DbException {
		// se pasa al objeto de TabularStatistic el valor de los nombres de las
		// filas y las columnas.
		tab.setRowNames(rows);
		tab.setColumnNames(columns);

		// se crea un arreglo bidimencional y lo inicialisamos con el tamaño de
		// las listas de
		// las filas y las columnas.
		double[][] data = tab.getData();

		// se recorre la lista de Records para obtener valores a tener en cuenta
		// en la estadistica.
		for (Record record : records) {

			// si el valor de la fila y la columna del Record en cuestion son
			// vacios
			// se adiciona en la intercepcion de No hay Datos [0][0].
			if ("".equals(record.getField(Integer.parseInt(statisticParam.getRowTag())).getStringFieldValue())
					&& "".equals(record.getField(Integer.parseInt(statisticParam.getColumTag())).getStringFieldValue())) {
				data[0][0] += 1;

				// si el valor de la columna es vacio se recorre la fila para
				// saber en que posicion sumar [i][0].
			} else if ("".equals(record.getField(Integer.parseInt(statisticParam.getColumTag())).getStringFieldValue())) {

				for (int i = 1; i < data.length; i++)
					if (rows.get(i).equals(record.getField(Integer.parseInt(statisticParam.getRowTag())).getStringFieldValue()))
						data[i][0] += 1;

				// si el valor de la fila es vacio se recorre la columna para
				// saber en que posicion sumar [0][i].
			} else if ("".equals(record.getField(Integer.parseInt(statisticParam.getRowTag())).getStringFieldValue())) {

				for (int i = 1; i < data[0].length; i++)
					if (columns.get(i).equals(record.getField(Integer.parseInt(statisticParam.getColumTag())).getStringFieldValue()))
						data[0][i] += 1;

				// si los dos valores contienen datos se recorren las
				// filas y las columnas para añadir el valor estadistico.
			} else {
				for (int i = 1; i < data.length; i++)
					for (int j = 1; j < data[i].length; j++)
						if (rows.get(i).equals(record.getField(Integer.parseInt(statisticParam.getRowTag())).getStringFieldValue())
								&& columns.get(j).equals(record.getField(Integer.parseInt(statisticParam.getColumTag())).getStringFieldValue()))
							data[i][j] += 1;
			}
		}

		tab.setData(data);
		return tab;

	}

	@Override
	public void checkIndexTags(StatisticParam statisticParam) throws Exception {
		// se obtiene el arreglo de nombres de FST de la base de datos de JISIS.
		String[] fstNames = getProxy(statisticParam).getFstNames();

		// se toma el primer FST de la base de datos.
		FieldSelectionTable fst = getProxy(statisticParam).getFst(fstNames[0]);

		// convierte a entero el número del campo de la fila y la columna.
		int rowTag = Integer.parseInt(statisticParam.getRowTag());
		int columnTag = Integer.parseInt(statisticParam.getColumTag());

		// se toma el arreglo de los números de los campos indexados en el FST.
		int tags[] = fst.getEntriesTag();

		// se crean variables boolean para saber si estan indexados los campos.
		boolean existRowTagInFst = false;
		boolean existColumnTagInFst = false;

		// se recorre el arreglo de número de campos indexados y se pregunta si
		// los campos de los indicadores estan indexados.
		for (int j = 0; j < tags.length; j++) {
			if (rowTag == tags[j] && !existRowTagInFst)
				existRowTagInFst = true;
			if (columnTag == tags[j] && !existColumnTagInFst)
				existColumnTagInFst = true;
		}

		// si el campo del indicador de la fila no esta indexado, lo indexamos.
		if (!existRowTagInFst)
			fst.addEntry(rowTag, "", 4, "v" + rowTag);

		// si el campo del indicador de la columna no esta indexado, lo
		// indexamos.
		if (!existColumnTagInFst)
			fst.addEntry(columnTag, "", 4, "v" + columnTag);

		// despues de indexar los indicadores se guarda el FST y se re-indexa la
		// base de datos.
		if (!existRowTagInFst || !existColumnTagInFst) {
			getProxy(statisticParam).saveFieldSelectionTable(fst);
			getProxy(statisticParam).buildIndex();
		}
	}

	private void RowsColumsChanged(List<String> rows, List<String> colums, StatisticParam statisticParam) {
		// si el tamaño de la lista de la fila es menor que la columna, se
		// invierten las listas y sus indicadores.
		if (rows.size() < colums.size()) {

			List<String> tempList = new ArrayList<String>();
			String tempIndicator;

			tempList.addAll(rows);
			rows.clear();
			rows.addAll(colums);
			colums.clear();
			colums.addAll(tempList);

			tempIndicator = statisticParam.getRowTag();
			statisticParam.setRowTag(statisticParam.getColumTag());
			statisticParam.setColumTag(tempIndicator);
		}
	}
}
