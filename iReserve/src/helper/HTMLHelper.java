package helper;

public class HTMLHelper {

	public static String makeTable(String[] names, String[][] data, int rows) {
		StringBuffer result = new StringBuffer();
		result.append("<table border=\"1\">");
		result.append("<tr>");
		// make the coloum titles
		for (int i = 0; i < names.length; i++) {
			result.append("<th>");
			result.append(names[i]);
			result.append("</th>");
		}
		result.append("</tr>");

		// all the data
		// rows
		for (int i = 0; i < rows; i++) {
			result.append("<tr>");
			// coloums
			for (int j = 0; j < names.length; j++) {
				result.append("<th>");
				result.append(data[i][j]);
				result.append("</th>");
			}
			result.append("</tr>");
		}
		result.append("</table>");

		return result.toString();
	}

	public static String makeOption(String[] entrys, String name, String selected) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<select name=\"" + name + "\" >");
		for (int i = 0; i < entrys.length; i++) {
			if (entrys[i].equals(selected)) {
				buffer.append("<option selected>");
			} else {
				buffer.append("<option>");
			}
			buffer.append(entrys[i]);
		}
		buffer.append("</select>");
		System.out.println(buffer.toString());
		return buffer.toString();
	}

	public static String makeOption(String[] entrys, String name) {
		return makeOption(entrys, name, null);
	}

}
