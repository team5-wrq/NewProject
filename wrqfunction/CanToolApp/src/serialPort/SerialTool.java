package serialPort;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.sound.sampled.AudioFormat.Encoding;

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
import serialException.SendDataToSerialPortFailure;
import serialException.SerialPortInputStreamCloseFailure;
import serialException.SerialPortOutputStreamCloseFailure;
import serialException.SerialPortParameterFailure;
import serialException.TooManyListeners;

public class SerialTool {
	private  static SerialTool serialTool;
	public SerialPort serialPort;
	public static String receive_data=null;
	public SerialTool(SerialPort serialPort){
		this.serialPort=serialPort;
	}
	
	//private static Enumeration<CommPortIdentifier> portList=null;
	static{
		//������ʱ��ʼ��һ������
		if(serialTool == null){
			serialTool = new SerialTool();
		}
	}
	//˽�л�SerialTool��Ĺ��췽��������������������SerialTool����
	SerialTool(){
		
	}
	
	//��ȡSerialTool����
	public static SerialTool getSerialTool(){
		if (serialTool == null) {
			serialTool = new SerialTool();
		}
		return serialTool;
	}
	
	//�������п��ö˿ڣ����ؿ��ö˿������б�
	public static final Enumeration<CommPortIdentifier> findPort(){
		//��õ�ǰ���õĴ���
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();	
        
        return portList;
	}
	
	//�򿪴���
	public final SerialPort openPort(String portName,int baudrate) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse{
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
                
                System.out.println("Open " + portName + " sucessfully !");
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
	
	 public static void sendToPort(SerialPort serialPort, byte[] order) throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {

	    	OutputStream out = null;
	    	System.out.println("send...");
	    	
	        try {
	        	
	            out = serialPort.getOutputStream();
	            out.write(order);
	            out.flush();
	            
	        } catch (IOException e) {
	        	throw new SendDataToSerialPortFailure();
	        } finally {
	        	try {
	        		if (out != null) {
	        			out.close();
	        			out = null;
	        		}				
				} catch (IOException e) {
					throw new SerialPortOutputStreamCloseFailure();
				}
	        }
	        
	    }
	
 
	//�Ӷ˿ڶ����� 
	public byte[] readFromPort(SerialPort serialPort) throws ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {
		System.out.println("reading...");
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
        //char[] charData=Encoding.ASCII.GetChars(byteData);   
        //char c = (char) (((bytes[0] & 0xFF) << 8) | (bytes[1] & 0xFF)); 

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
