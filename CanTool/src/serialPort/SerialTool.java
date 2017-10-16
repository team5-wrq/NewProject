package serialPort;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import serialException.NoSuchPort;
import serialException.NotASerialPort;
import serialException.PortInUse;
import serialException.ReadDataFromSerialPortFailure;
import serialException.SerialPortInputStreamCloseFailure;
import serialException.SerialPortParameterFailure;
import serialException.TooManyListeners;

public class SerialTool {
	private static SerialTool serialTool = null;
	static{
		//������ʱ��ʼ��һ������
		if(serialTool == null){
			serialTool = new SerialTool();
		}
	}
	//˽�л�SerialTool��Ĺ��췽��������������������SerialTool����
	private SerialTool(){
		
	}
	
	//��ȡSerialTool����
	public static SerialTool getSerialTool(){
		if (serialTool == null) {
			serialTool = new SerialTool();
		}
		return serialTool;
	}
	
	//�������п��ö˿ڣ����ؿ��ö˿������б�
	public static final ArrayList<String> findPort(){
		//��õ�ǰ���õĴ���
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		ArrayList<String> portNameList = new ArrayList<>();
		//�����õĴ�������ӵ�List,�����ظ�List
		while(portList.hasMoreElements()){
			String portName = portList.nextElement().getName();
			portNameList.add(portName);
		}
		return portNameList;
	}
	
	//�򿪴���
	public static final SerialPort openPort(String portName,int baudrate) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse{
		try {

            //ͨ���˿���ʶ��˿�
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            //�򿪶˿ڣ������˿����ֺ�һ��timeout���򿪲����ĳ�ʱʱ�䣩
            CommPort commPort = portIdentifier.open(portName, 2000);

            //�ж��ǲ��Ǵ���
            if (commPort instanceof SerialPort) {
            	
                SerialPort serialPort = (SerialPort) commPort;
                
                try {                    	
                    //����һ�´��ڵĲ����ʵȲ���
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);                              
                } catch (UnsupportedCommOperationException e) {  
                	throw new SerialPortParameterFailure();
                }
                
                //System.out.println("Open " + portName + " sucessfully !");
                return serialPort;
            
            }        
            else {
            	//���Ǵ���
            	throw new NotASerialPort();
            }
        } catch (NoSuchPortException e1) {
          throw new NoSuchPort();
        } catch (PortInUseException e2) {
        	throw new PortInUse();
        }
    }
	
	public static byte[] readFromPort(SerialPort serialPort) throws ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {

    	InputStream in = null;
        byte[] bytes = null;

        try {
        	
        	in = serialPort.getInputStream();
        	int bufflenth = in.available();		//��ȡbuffer������ݳ���
            
        	while (bufflenth != 0) {                             
                bytes = new byte[bufflenth];	//��ʼ��byte����Ϊbuffer�����ݵĳ���
                in.read(bytes);
                bufflenth = in.available();
        	} 
        } catch (IOException e) {
        	throw new ReadDataFromSerialPortFailure();
        } finally {
        	try {
            	if (in != null) {
            		in.close();
            		in = null;
            	}
        	} catch(IOException e) {
        		throw new SerialPortInputStreamCloseFailure();
        	}

        }

        return bytes;

    }
	
	public static void addListener(SerialPort port, SerialPortEventListener listener) throws TooManyListeners {

        try {
        	
            //��������Ӽ�����
            port.addEventListener(listener);
            //���õ������ݵ���ʱ���Ѽ��������߳�
            port.notifyOnDataAvailable(true);
          //���õ�ͨ���ж�ʱ�����ж��߳�
            port.notifyOnBreakInterrupt(true);

        } catch (TooManyListenersException e) {
        	throw new TooManyListeners();
        }
    }
    
	
	
	
	
	

}
