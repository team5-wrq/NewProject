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
		//被加载时初始化一个对象
		if(serialTool == null){
			serialTool = new SerialTool();
		}
	}
	//私有化SerialTool类的构造方法，不允许其他类生成SerialTool对象
	SerialTool(){
		
	}
	
	//获取SerialTool对象
	public static SerialTool getSerialTool(){
		if (serialTool == null) {
			serialTool = new SerialTool();
		}
		return serialTool;
	}
	
	//查找所有可用端口，返回可用端口名称列表
	public static final Enumeration<CommPortIdentifier> findPort(){
		//获得当前可用的串口
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();	
        
        return portList;
	}
	
	//打开串口
	public final SerialPort openPort(String portName,int baudrate) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse{
		try {

            //通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

            //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(portName, 2000);

            //判断是不是串口
            if (commPort instanceof SerialPort) {
            	
                SerialPort serialPort = (SerialPort) commPort;
                
                try {                    	
                    //设置一下串口的波特率等参数
                    serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);                              
                } catch (UnsupportedCommOperationException e) {  
                	throw new SerialPortParameterFailure();
                }
                
                System.out.println("Open " + portName + " sucessfully !");
                return serialPort;
            
            }        
            else {
            	//不是串口
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
	
 
	//从端口读数据 
	public byte[] readFromPort(SerialPort serialPort) throws ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {
		System.out.println("reading...");
    	InputStream in = null;
        byte[] bytes = null;

        try {
        	
        	in = serialPort.getInputStream();
        	int bufflenth = in.available();		//获取buffer里的数据长度
            
        	while (bufflenth != 0) {                             
                bytes = new byte[bufflenth];	//初始化byte数组为buffer中数据的长度
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
        	
            //给串口添加监听器
            port.addEventListener(listener);
            //设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
          //设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);

        } catch (TooManyListenersException e) {
        	throw new TooManyListeners();
        }
    }	
}
