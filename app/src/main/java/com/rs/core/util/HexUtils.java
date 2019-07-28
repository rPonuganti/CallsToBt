package com.rs.core.util;

import java.math.BigInteger;


/**
 * byte hex Related tools
 * @author dragons-Lyx
 *
 */
public class HexUtils {
    private final static byte[] charToByte = new byte[256];
    static final char[] byteToChar = new char[16];
    static
    {
        for (char c = 0; c < charToByte.length; ++c)
        {
            if (c >= '0' && c <= '9')
                charToByte[c] = (byte)(c - '0');
            else if (c >= 'A' && c <= 'F')
                charToByte[c] = (byte)(c - 'A' + 10);
            else if (c >= 'a' && c <= 'f')
                charToByte[c] = (byte)(c - 'a' + 10);
            else
                charToByte[c] = (byte)-1;
        }

        for (int i = 0; i < 16; ++i)
        {
            byteToChar[i] = Integer.toHexString(i).charAt(0);
        }
    }
    /**
     * Convert hex to  bytes
     * @param str
     * @return
     */
    public static byte[] hexToBytes(String str)
    {
        if (str.length() % 2 == 1)
            str = "0" + str;
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < bytes.length; i++)
        {
            byte halfByte1 = charToByte[str.charAt(i * 2)];
            byte halfByte2 = charToByte[str.charAt(i * 2 + 1)];
            if (halfByte1 == -1 || halfByte2 == -1)
                throw new NumberFormatException("Non-hex characters in " + str);
            bytes[i] = (byte)((halfByte1 << 4) | halfByte2);
        }
        return bytes;
    }
    /**
     * Convert bytes to hex
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte... bytes)
    {
        if(bytes == null)return "";
        char[] c = new char[bytes.length * 2];
        for (int i = 0,len = bytes.length; i < len; i++)
        {
            int bint = bytes[i];
            c[i * 2] = byteToChar[(bint & 0xf0) >> 4];
            c[1 + i * 2] = byteToChar[bint & 0x0f];
        }
        return new String(c);
    }

    public static void main(String[] args) {
                System.out.println(HexUtils.bytesToHex(null));
                System.out.println(HexUtils.bytesToInt((byte)0x70));
                byte[] bytes = hexToBytes("2e280a00f0f0f0f0f0f0f0f0004d");
//              Radar radar_ = new Radar(VoCodes.radar);
//              radar_.status = 1;
//              radar_.headLeft = HexUtils.byteToInt(bytes[4]);
//              radar_.headML = HexUtils.byteToInt(bytes[5]);
//              radar_.headMR = HexUtils.byteToInt(bytes[6]);
//              radar_.headRight = HexUtils.byteToInt(bytes[7]);
//
//              radar_.tailLeft = HexUtils.byteToInt(bytes[8]);
//              radar_.tailML = HexUtils.byteToInt(bytes[9]);
//              radar_.tailMR = HexUtils.byteToInt(bytes[10]);
//              radar_.tailRight = HexUtils.byteToInt(bytes[11]);
//              System.out.println(radar_.toJson());
        }
    /**
     * Change byte to int
     * @param source
     * @return
     */
    public static int bytesToInt(byte... bytes){
        return new BigInteger(bytes).intValue();
    }
    public static int byteToInt(byte byte_value){
        int value = HexUtils.bytesToInt(byte_value);
        if(value <0){
                return value+256;
        }
        return value;
    }
    /**
     * Get the value on the first bit
     * @param value
     * @param position
     * @return
     */
    public static int getBit(byte source,int position)
    {
       return (source >> position) & 1;
    }
    /**
     * How many bits of the byte are in the value
     * e.g  00101000   Values of the 3rd, 4th, and 5th digits  getBits((byte)0x28,3,4,5) = 5 (101)
     * e.g  00111000   Values of the 3rd, 4th, and 5th digits  getBits((byte)0x38,3,4,5) = 7 (111)
     * @param source
     * @param positions
     * @return
     */
    public static int getBits(byte source,int... positions){
        int value = 0;
                for (int i = 0,len = positions.length; i < len; i++)
            {
                        if(HexUtils.getBit(source, positions[i]) ==1){
                                value += Math.pow(2, i);
                        }
            }
                return value;
    }
}
