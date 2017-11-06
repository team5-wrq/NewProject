package tool;

import java.io.IOException;
import java.nio.charset.Charset;

import main.Main;

import com.csvreader.CsvWriter;

import dataprocess.Result;

public class ToCSV {
	//public Result res;
	public ToCSV(){
		//this.res=res;
	}
	
	public void toCSV(){
		CsvWriter csvWriter = new CsvWriter(Main.csvString,',', Charset.forName("GBK"));
        //CsvWriter csvWriter = new CsvWriter(filePath);
		 String[] headers = {"ID","SignalName","PhyValue","Unit"};
         //String[] content = {"12365","张山","34"};
         try {
			csvWriter.writeRecord(headers);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // 写表头
        //String[] headers = {"编号","姓名","年龄"};
		for(int j=0;j<Main.resultList.size();j++){
			Result res=Main.resultList.get(j);
			for(int i=0;i<res.signalResult.length;i++){
				String[] content={String.valueOf(res.messageResult.getId()),res.signalResult[i].SignalName,String.valueOf(res.phyResult[i]),res.signalResult[i].unit};
				try {
					csvWriter.writeRecord(content);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
        //String[] content = {"12365","张山","34"};
        //csvWriter.writeRecord(headers);
        //csvWriter.writeRecord(content);
        csvWriter.close();
	}
}
