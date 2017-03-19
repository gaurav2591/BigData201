package udf;

import java.io.IOException;

import org.apache.pig.FilterFunc;
import org.apache.pig.data.Tuple;

public class Filt extends FilterFunc{

	/*@Override
	public Boolean exec(Tuple row) throws IOException {
		boolean flag = false;
		
		for(Tuple t : (DataBag)row.get(0)){
			String secField = (String) t.get(1);
			String thirdField = (String) t.get(2);
			String regex = "[0-9]+";
			if(secField.matches(regex) && thirdField.matches(regex)){
				flag = true;
			}
		}
		return flag;
	}*/
	@Override
	public Boolean exec(Tuple row) throws IOException {
		boolean flag = false;
			String secField = (String) row.get(0);
			//String thirdField = (String) row.get(2);
			String regex = "[0-9]+";
			if(secField.matches(regex)/* && thirdField.matches(regex)*/){
				flag = true;
			}
		return flag;
	}
}
