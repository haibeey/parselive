package parsehtml;

import java.io.File;
import java.util.HashMap;


public class parseHtml implements parselive.onHandleElement{
	
	/*

	parseHtml(){
		File f=new File("C:\\Users\\haibeey\\eclipse-workspace\\parsehtml\\src\\parsehtml\\test.html");
		parselive test1=new parselive(f);
		test1.setTagListerner(this);
		test1.feed(0, false);
	}

	*/

	public static void main(String[] args) {
		parseHtml p=new parseHtml();
		
	}
	
	@Override
	public void startTag(String element) {
		// TODO Auto-generated method stub
		System.out.println("start "+element);
		
	}

	@Override
	public void startTagNameAndAttr(String name, HashMap attributes) {
		// TODO Auto-generated method stub
        System.out.println(name);
        System.out.println(attributes);
		
	}

	@Override
	public void endTag(String element) {
		// TODO Auto-generated method stub
        System.out.println("end "+element);
		
	}

	@Override
	public void commentTag(String element) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void normalText(String element) {
        System.out.println("text "+element);
    }

}
