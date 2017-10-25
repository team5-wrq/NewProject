package serialPort;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;



public class SerialListener implements SerialPortEventListener {
	public SerialTool tool;
    public SerialPort serialPort;
    public SerialListener(SerialTool tool,SerialPort serialPort)
    {
    	this.tool=tool;
        this.serialPort=serialPort;
    }
    
    public void serialEvent(SerialPortEvent serialPortEvent) {
        
        switch (serialPortEvent.getEventType()) {

            case SerialPortEvent.BI: // 10 ͨѶ�ж�
                System.out.println("�봮���豸ͨѶ�ж�");
                break;

            case SerialPortEvent.OE: // 7 ��λ�����������

            case SerialPortEvent.FE: // 9 ֡����

            case SerialPortEvent.PE: // 8 ��żУ�����

            case SerialPortEvent.CD: // 6 �ز����

            case SerialPortEvent.CTS: // 3 �������������

            case SerialPortEvent.DSR: // 4 ����������׼������

            case SerialPortEvent.RI: // 5 ����ָʾ

            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 ��������������
                break;
            
            case SerialPortEvent.DATA_AVAILABLE: // 1 ���ڴ��ڿ�������
                
                //System.out.println("found data");
                byte[] data = null;
                
                try {
                    if (serialPort == null) {
                        System.out.println("���ڶ���Ϊ�գ�����ʧ��");
                    }
                    else {
                        data = tool.readFromPort(serialPort);    //��ȡ���ݣ������ֽ�����
                        System.out.println(new String(data,"UTF-8"));
                        //System.out.println(new String(data));
                        //JOptionPane.showInputDialog(new String(data));
                        //String dataOriginal = new String(data);    //���ֽ���������ת��λΪ������ԭʼ���ݵ��ַ���
                    }                        
                    
                } catch (Exception e) {
                    System.exit(0);
                }    
                
                break;

        }

    }

}
