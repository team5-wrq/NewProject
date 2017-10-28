package serialPort;

import java.io.Externalizable;

import tool.MyTree;
import main.Main;
import dataprocess.Analyse;
import dataprocess.ReceivedMessage;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;



public class SerialListener implements SerialPortEventListener {
	public SerialTool tool;
    public SerialPort serialPort;
    public static ReceivedMessage rm;
   
    public SerialListener(SerialTool tool,SerialPort serialPort)
    {
    	this.tool=tool;
        this.serialPort=serialPort;
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
                        char[] chardata=(new String(data,"UTF-8")).toCharArray();
                        if(chardata[0]=='t'||chardata[0]=='T'){
                        	 Analyse analyse=new Analyse(data);
                             Main.resultList=analyse.toAnalyse();
                             rm=new ReceivedMessage(Main.resultList);
                             //rm.update();
                             rm.setVisible(true);
                             //Main.panel.updateUI();
                             //MyTree tree=new MyTree(Main.frmCantoolapp,Main.resultList);
                             //Main.toTree();
                             System.out.println(new String(data,"UTF-8"));
                        }else{
                        	System.out.println(new String(data,"UTF-8"));
                        	Main.textArea.setText(new String(data,"UTF-8"));
                        }
                       
                        
                       
                        //System.out.println(new String(data));
                        //JOptionPane.showInputDialog(new String(data));
                        //String dataOriginal = new String(data);    //���ֽ��������ת��λΪ������ԭʼ��ݵ��ַ�
                    }                        
                    
                } catch (Exception e) {
                    System.exit(0);
                }    
                
                break;

        }

    }
    public byte[] dataReceive(){
    	return data;
    }

}
