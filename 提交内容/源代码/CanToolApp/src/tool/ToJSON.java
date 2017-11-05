package tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import dataprocess.Result;

public class ToJSON {
	public Result res;
	public ToJSON(Result res){
		this.res=res;
	}
	
	public void toJSON(){
		File file=new File("CANJSON.json");
		FileWriter fw;
		try {
			fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			Gson gson = new Gson();
	        String json = gson.toJson(res);
	        
	        bw.write(json);
	        bw.close();
	        fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
