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
		//被加载时初始化一个对象
		if(serialTool == null){
			serialTool = new SerialTool();
		}
	}
	//私有化SerialTool类的构造方法，不允许其他类生成SerialTool对象
	private SerialTool(){
		
	}
	
	//获取SerialTool对象
	public static SerialTool getSerialTool(){
		if (serialTool == null) {
			serialTool = new SerialTool();
		}
		return serialTool;
	}
	
	//查找所有可用端口，返回可用端口名称列表
	public static final ArrayList<String> findPort(){
		//获得当前可用的串口
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		ArrayList<String> portNameList = new ArrayList<>();
		//将可用的串口名添加到List,并返回该List
		while(portList.hasMoreElements()){
			String portName = portList.nextElement().getName();
			portNameList.add(portName);
		}
		return portNameList;
	}
	
	//打开串口
	public static final SerialPort openPort(String portName,int baudrate) throws SerialPortParameterFailure, NotASerialPort, NoSuchPort, PortInUse{
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
                
                //System.out.println("Open " + portName + " sucessfully !");
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
	
	public static byte[] readFromPort(SerialPort serialPort) throws ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {

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
