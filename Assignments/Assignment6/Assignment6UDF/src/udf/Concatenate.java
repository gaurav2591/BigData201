package udf;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.BagFactory;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.Tuple;

public class Concatenate extends EvalFunc<DataBag>{

	@Override
	public DataBag exec(Tuple record) throws IOException {
		if(record == null || record.size() == 0){
			return null;
		}
		DataBag newBag = BagFactory.getInstance().newDefaultBag();
		for(Tuple t : (DataBag)record.get(0)){
			String firstField = (String) t.get(0);
			String thirdField = (String) t.get(3);
			t.append(firstField+thirdField);
			newBag.add(t);
		}
		return newBag;
		
	}
}
