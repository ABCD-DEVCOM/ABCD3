package cu.uci.abos.l10n.cataloguing;

import org.eclipse.rap.rwt.internal.util.Entities;

public class MessageUtil {

	/**
	 * Created by Basilio Puentes Rodríguez
	 */

	public static String unescape(String str) {
		return Entities.HTML40.unescape(str);
	}

	public static String escape(String str) {
		return Entities.HTML40.escape(str);
	}
}
