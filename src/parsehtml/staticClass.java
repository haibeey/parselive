package parsehtml;

import java.util.regex.Pattern;

public  final class staticClass {
	//Constant Strings
		public static final String startTag="<\\s?[!a-zA-Z]+(?:\\s[A-Za-z:/\\\\.]+=[\"''A-Za-z/\\\\.:]+)*\\s?>";
		public static final String endTag="</\\s?[!a-zA-Z]+\\s?>";
		public static final String normalText="[A-Za-z0-9\\s(?:[\\.,~!@#$%^&*(){}\\\\/\\?]{0,100})]+";
		public static final String comment="<!--[.]>";
		
		
		//Regex matcher and pattern
		public static final Pattern patternForStartTag=Pattern.compile(startTag);
		public static final Pattern patternForEndTag=Pattern.compile(endTag);
		public static final Pattern patternForNormalText=Pattern.compile(normalText);
		public static final Pattern patternForComment=Pattern.compile(comment);
		public static final Pattern general=Pattern.compile(startTag+"|"+endTag+"|"+comment+"|"+normalText);
		
}
