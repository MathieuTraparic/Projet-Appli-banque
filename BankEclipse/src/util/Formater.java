/**
 *  @author Mathieu Traparic
 * 	Project : Bank
 * 	Creation date : 2017-04-11
 */
package util;

/**
 * contains static methods to uniform inputs to be inserted into the DB
 *
 */
public class Formater {

	/**
	 * private constructor because it shouldn't have to be instanciated
	 */
	private Formater() {
		super();
	}

	private static final String[] USUAL_SEP = new String[] { " ", "-", ",", "." };

	/**
	 * @return the list of usual separators used by {@link util.Formater#removeUsualSeparators(java.lang.String)}.
	 */
	public static String[] getUsualSep() {
		return USUAL_SEP;
	}

	/**
	 * calls a removeSeparators with specific arguments
	 * @param content
	 * @return the input string without the static variable USUAL_SEP content
	 */
	public static String removeUsualSeparators(String content) {
		StringBuilder bl = new StringBuilder();
		bl.append("(" );
		for(int i=0;i<USUAL_SEP.length-1;++i){
			
			bl.append( USUAL_SEP[i]+"|");
		}
		bl.append( USUAL_SEP.length-1);
		bl.append(")+");

		return removeSeparators(content, bl.toString());
	}

	/**
	 * @param content
	 * @param regex
	 * @return the input string with the matching regex removed
	 */
	public static String removeSeparators(String content, String regex) {

		return content.replaceAll(regex, "");
	}

	/**
	 * @param name
	 *            : the string to be converted
	 * @return the name with title case
	 * 
	 */
	public static String formatNameCase(String name) {
		if (name.isEmpty()) {
			return name;
		}
		StringBuilder bl = new StringBuilder(name.toLowerCase());
		bl.setCharAt(0, Character.toTitleCase(bl.charAt(0)));

		return bl.toString();
	}
}
