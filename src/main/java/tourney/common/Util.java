package tourney.common;


public class Util {
	public static String formatJson(String json) {
		StringBuffer jsonFormated = new StringBuffer();
		int position = 0, nTab = 0;
		boolean quoted = false, scaped = false;
		
		while(position < json.length() ) {
			char c = json.charAt(position++);
			switch( c ) {
			case '{' :
			case '[' :
				jsonFormated.append(c);
				jsonFormated.append("\n");
				jsonFormated.append(String.format("%" + (++nTab * 5) + "s", " "));
				break;
			case '}' :
			case ']' :
				jsonFormated.append("\n");
				jsonFormated.append(--nTab != 0 ? String.format("%" + (nTab * 5) + "s", " ") : "");
				jsonFormated.append(c);
				break;
			case ',' :
				jsonFormated.append(c);
				jsonFormated.append("\n");
				jsonFormated.append(String.format("%" + (nTab * 5) + "s", " "));
				break;
			case ':' :
				jsonFormated.append(" " + c + " ");
				break;
			case ' ' :
				if( quoted )
					jsonFormated.append(c);
				break;
			default:
				if( c == '\\' && quoted )
					scaped = true;
				else{
					if( c == '"' ) {
						if( !quoted )
							quoted = true;
						else if( !scaped )
							quoted = false;
					}
					if( scaped )
						scaped = false;
				}
				jsonFormated.append(c);
			}
		}
		
		return jsonFormated.toString();
	}
}
