package cu.uci.abcd.opac;

import java.util.List;
   
import org.unesco.jisis.corelib.common.FieldSelectionTable;
import org.unesco.jisis.corelib.common.FormattedRecord;
import org.unesco.jisis.corelib.common.WorksheetDef;
import org.unesco.jisis.corelib.record.Record;

import cu.uci.abcd.dataprovider.jisis.exception.JisisDatabaseException;
import cu.uci.abcd.dataprovider.jisis.impl.search.option.Option;
import cu.uci.abcd.domain.management.library.Library;
    
/**
 * RF_OP1 Buscar Registros de Ejemplares por criterios básicos, RF_OP2 Buscar Registros de Ejemplares en otras bases de datos, 
 * RF_OP2.1 Realizar metabúsquedas en BD no ISIS, RF_OP3 Buscar por palabras concatenadas con AND, 
 * RF_OP4 Buscar por palabras concatenadas con OR, RF_OP5 Limitar búsqueda, 
 * RF_OP6 Listar los resultados de la búsqueda de materiales, RF_OP7 Realizar búsqueda externa de registro bibliográfico,
 * RF_OP8 Visualizar los detalles de un resultado de búsqueda, RF_OP8.1 Visualizar los detalles de un resultado de búsqueda en una Vista MARC,
 * RF_OP8.1.1 Visualizar los detalles de un resultado de búsqueda en una Vista ISBD, RF_OP8.3 Mostrar cantidad de resultados coincidentes
 * @author dailien
 *
 */
public interface IOpacDataBaseManager {
	//FIXME FALTAN COMENTARIOS DE INTERFACE
      
	public List<RecordIsis> findByOptions(List<Option> options, String databaseName, String libraryIsisDatabasesHomeFolder, Library library) throws JisisDatabaseException;

	public List<RecordIsis> find(String term, String databaseName, String libraryIsisDatabasesHomeFolder, Library library, List<Option> options) throws JisisDatabaseException;

	public FieldSelectionTable getFieldSelectionTable(String databaseName, String libraryIsisDatabasesHomeFolder) throws JisisDatabaseException;

	public List<String> getWorksheetNames(String databaseName, String libraryIsisDatabasesHomeFolder) throws JisisDatabaseException;

	public WorksheetDef getWorksheet(String databaseName, String libraryIsisDatabasesHomeFolder) throws JisisDatabaseException;

	public List<String> getDatabaseNames(String libraryIsisDatabasesHomeFolder) throws JisisDatabaseException;
   
    public List<RecordIsis> findRecordByControlNumber(List<String> controlNumber, String databaseName, String libraryIsisDatabasesHomeFolder) throws JisisDatabaseException;

    public List<String> getDatabaseFormats(String databaseName, String libraryIsisDatabasesHomeFolder) throws JisisDatabaseException;

    public FormattedRecord getFormattedRecord(String databaseName, Record record, String formatName, String libraryIsisDatabasesHomeFolder) throws JisisDatabaseException;

}
