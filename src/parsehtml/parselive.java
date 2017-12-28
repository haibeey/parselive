package parsehtml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parselive {
	
	private String html; //The holds the html document 

	private int len=0;
	
	private onHandleElement tagHandler;
		
	public parselive(String html) {
		this.html=html;
		len=html.length();
	}
	
	public parselive(File htmlFile) {
		if(!htmlFile.exists()) {
			//throws Exception;
			throw new IllegalStateException();
		}else {
			StringBuilder builder=new StringBuilder();
			//read the file and stores the string content on html
			try {
				@SuppressWarnings("resource")
				FileReader reader=new FileReader(htmlFile);
				int content;
				while((content=reader.read())!=-1) {
					builder.append((char)content);
				}
				html=builder.toString();
				len=html.length();
			} catch ( IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
	}
	
	public void setTagListerner(Object object) {
		tagHandler=(onHandleElement) object;
	}
	
	public void feed(int start,boolean extractNode) {
		Matcher matcher=staticClass.general.matcher(html.substring(0,len));

		while(matcher.find()) {
			String currentlyMatched=html.substring(matcher.start(),matcher.end());

			if(currentlyMatched.matches(staticClass.startTag)) {
                {
					int startCur=matcher.start();
					int endCur=matcher.end();
				
					String newElement=html.substring(startCur+1,endCur-1);
					String[] list=newElement.split("\\s");
					String name=list[0];
					HashMap<String,String> theAttributes=getAttributes(list);
					//send data to onHandleStartTag
					onHandleStartTag(name,theAttributes);
					
					if(tagHandler!=null) {
						
						tagHandler.startTag(html.substring(startCur,endCur));
						tagHandler.startTagNameAndAttr(name,theAttributes);
					}
				}
			}else if(currentlyMatched.matches(staticClass.endTag)) {
				
				onHandleEndTag(currentlyMatched);
				if(tagHandler!=null) {
					tagHandler.endTag(currentlyMatched);
				}
			}else if(currentlyMatched.matches(staticClass.comment)) {
				String matched=findMatching(html.substring(matcher.end(),len),matcher.start());
				
				onHandleCommentTag(matched); 
				if(tagHandler!=null) {
					tagHandler.commentTag(matched);
				}
			}else if(currentlyMatched.matches(staticClass.normalText)){
				onHandleNormalText(currentlyMatched);
				if(tagHandler!=null) {
					tagHandler.normalText(currentlyMatched);
				}
			}
		}
	}
	
	private HashMap<String,String> getAttributes(String[] element) {
		HashMap<String,String> attributes=new HashMap<String,String>();
		
		
		for(String attr:element) {
			if(attr.contains("=")) {
				String key=attr.split("=")[0];
				String value=attr.split("=")[1];
			
				attributes.put(key, value);
			}
		}
		
		return attributes;
	}
	
	public void onHandleStartTag(String name,HashMap<String,String> attributes) {
		//user class should override this method or implement interface
		
	}
	
	public void onHandleEndTag(String element) {
		//user class should override this method or implement interface
	}
	public void onHandleCommentTag(String element) {
		//user class should override this method or implement interface
	}

	public void onHandleNormalText(String element) {
		//user class should override this method or implement interface
	}
	
	private String findMatching(String string,int start) {
		int end=1;
		int stop=0;
		Pattern matchTag=staticClass.general;
		Matcher matcherTag=matchTag.matcher(string);
		while(end!=0 && matcherTag.find()) {
			String currentlyMatchedTag=html.substring(matcherTag.start(),matcherTag.end());
			
			if(currentlyMatchedTag.matches(staticClass.startTag)) {
				end=end+1;
			}else if(currentlyMatchedTag.matches(staticClass.endTag)) {
				end=end-1;
			}
			stop=matcherTag.end();
		}
		return html.substring(start,stop);
	}
	
	//interface for tags
	public interface onHandleElement{
		
		public void startTag(String element);
		public void startTagNameAndAttr(String name,HashMap attributes);
		public void endTag(String element);
		public void commentTag(String element);
		public void normalText(String element);
		
	}
}
