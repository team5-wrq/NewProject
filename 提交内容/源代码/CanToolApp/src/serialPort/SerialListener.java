package serialPort;

import java.io.Externalizable;

//import tool.MyTree;
import tool.ToCSV;
import tool.ToJSON;
import main.Main;
import dataprocess.Analyse;
import dataprocess.ReceivedMessage;
import dataprocess.Result;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;



public class SerialListener implements SerialPortEventListener {
	public SerialTool tool;
    public SerialPort serialPort;
    public static ReceivedMessage rm;
    public static String buff;
   
    public SerialListener(SerialTool tool,SerialPort serialPort)
    {
    	this.tool=tool;
        this.serialPort=serialPort;
        buff = "";
    }
    public  static byte[] data = null;
    public void serialEvent(SerialPortEvent serialPortEvent) {
        
        switch (serialPortEvent.getEventType()) {

            case SerialPortEvent.BI: // 10 ͨѶ�ж�
                System.out.println("�봮���豸ͨѶ�ж�");
                break;

            case SerialPortEvent.OE: // 7 ��λ�����������

            case SerialPortEvent.FE: // 9 ֡����

            case SerialPortEvent.PE: // 8 ��żУ�����

            case SerialPortEvent.CD: // 6 �ز����

            case SerialPortEvent.CTS: // 3 ���������

            case SerialPortEvent.DSR: // 4 �������׼������

            case SerialPortEvent.RI: // 5 ����ָʾ

            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 ��������������
                break;
            
            case SerialPortEvent.DATA_AVAILABLE: // 1 ���ڴ��ڿ������
                
                //System.out.println("found data");
                //byte[] data = null;
                
                try {
                    if (serialPort == null) {
                        System.out.println("���ڶ���Ϊ�գ�����ʧ��");
                    }
                    else {
                    	
                        data = tool.readFromPort(serialPort);    //��ȡ��ݣ������ֽ�����
                        String dataString = new String(data);
                        System.out.println(dataString);
                        String temp =  "\r" + (char)7;
                        buff =  buff + dataString;
                        String ele[] =  dataString.split(temp);
                        int len = ele.length;
                        if(buff.charAt(buff.length()-1)!='\r'&&buff.charAt(buff.length()-1)!=(char)7)
                        	len--;
                        for(int i=0;i<len;i++)
                        {
                        	if(ele[i].charAt(0)=='t'||ele[i].charAt(0)=='T'){
                                System.out.println(ele[i]);

                           	 	Analyse analyse=new Analyse(ele[i].getBytes());
                           	 	Result res = analyse.toAnalyse();
                    
                                Main.resultList.add(res);
                                
                                //存为JSON
                           	 	ToJSON toj = new ToJSON(res);
                           	 	toj.toJSON();
                           	 	
                           	 	//存为CSV
                           	 	ToCSV toc = new ToCSV();
                           	 	toc.toCSV();
                           	 	
                                //Main.update(res);
                                if(rm ==null)
                                {
                                	rm=new ReceivedMessage(Main.resultList);
                                	rm.setVisible(true);
                                }
                                else
                                {
                                	rm.update(res);
                                }
                                //rm.update();
                                //rm.setVisible(true);
                                //Main.panel.updateUI();
                                //MyTree tree=new MyTree(Main.frmCantoolapp,Main.resultList);
                                //Main.toTree();
                           }else{
                        	   if(ele[i].charAt(ele[i].length()-1)=='\r'){
                        		   Main.textStr=Main.textStr+"\r\n"+new String(ele[i]);
                        		   Main.textArea.setText(Main.textStr);
                        	   }
                           	//Main.textStr=Main.textStr+new String(ele[i]);
                           	//System.out.println(new String(data,"UTF-8"));
                           	//if(ele[i].charAt(ele[i].length()-1)=='\r')
                           		//System.out.println(new String(data)+"OK");
                           //	else
                           		//System.out.println("ERROR");
                           	//Main.textArea.setText(Main.textStr);
                           }
                        }
                        //System.out.println(new String(data,"UTF-8"));
                        //char[] chardata=(new String(data)).toCharArray();
                        
                       
                        
                       
                        //System.out.println(new String(data));
                        //JOptionPane.showInputDialog(new String(data));
                        //String dataOriginal = new String(data);    //���ֽ��������ת��λΪ������ԭʼ��ݵ��ַ�
                    }                        
                    
                } catch (Exception e) {
                    System.out.println(e);
                }    
                
                break;

        }

    }
    public byte[] dataReceive(){
    	return data;
    }

}
