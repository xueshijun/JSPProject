package com.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
  
 
public class TxtReader {
  
    /**
     * ���ļ��ж�ȡ�ı�����, ��ȡʱʹ��ƽ̨Ĭ�ϱ�������ļ��е��ֽ�����
     * @param file Ŀ���ļ�
     * @return
     * @throws IOException
     */
    public static String loadStringFromFile(File file) throws IOException {
        return TxtReader.loadStringFromFile(file, System.getProperty("file.encoding"));
    }
  
 
    /**
     * ���ļ��ж�ȡ�ı�����
    * @param file Ŀ���ļ�
     * @param encoding Ŀ���ļ����ı������ʽ
     * @return
     * @throws IOException
     */
    public static String loadStringFromFile(File file, String encoding) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding),5*1024*1024);// ��5M�Ļ����ȡ�ı��ļ� 
            StringBuilder builder = new StringBuilder();
            char[] chars = new char[4096];
 
            int length = 0;
 
            while (0 < (length = reader.read(chars))) {
 
                builder.append(chars, 0, length);
 
            }
 
            return builder.toString();
 
        } finally {
 
            try {
 
                if (reader != null) reader.close();
 
            } catch (IOException e) {
 
                throw new RuntimeException(e);
 
            }
 
        }
 
    }
}