import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class HuffmanDecompress {
	Hashtable<String, Character> decodingTable = new Hashtable<String, Character>();
	String stringDecodedData;
	String decodedData = ""; //final result
	Hashtable<Byte, String> decodingBitMap = new Hashtable<Byte, String>();
	ArrayList<Byte> binaryCompressedData;
	
	Hashtable<Character, String> encodingTable;
	int inputStringLength;
	
	//begin methods
	String decode(ArrayList<Byte>binaryCompressedData, Hashtable<Character, String> encodingTable, int inputStringLength) {
		this.binaryCompressedData = binaryCompressedData;
		this.encodingTable = encodingTable;
		this.inputStringLength = inputStringLength;
		
		buildDecodingBitMap();
		decodeBitsAsString();
		buildDecodingTable();
		decodeStringBitstoChars();
		
		decodedData = decodedData.substring(0, inputStringLength);
		
		return decodedData;
		//return decodedData.substring(0, inputStringLength);
	}


	private void decodeStringBitstoChars() {
		StringBuffer output = new StringBuffer();
		StringBuffer temp = new StringBuffer();
		
		for (int i = 0; i < stringDecodedData.length(); i++) {
			temp.append(stringDecodedData.charAt(i));
			if (decodingTable.containsKey(temp.toString())) {
				output.append(decodingTable.get(temp.toString()));
				temp = new StringBuffer();
			}
		}
		
		decodedData = output.toString();
	}


	private void buildDecodingTable() {
		Enumeration<Character> e = encodingTable.keys();
		while (e.hasMoreElements()) {
			Character nextKey = e.nextElement();
			String nextString = encodingTable.get(nextKey);
			decodingTable.put(nextString, nextKey);
		}
	}


	private void decodeBitsAsString() {
		StringBuffer temp = new StringBuffer();
		
		for (Byte b : binaryCompressedData) {
			byte whole = b;
			temp.append(decodingBitMap.get(whole));
		}
		stringDecodedData = temp.toString();
	}


	private void buildDecodingBitMap() {
		for (int i = 0; i <= 255; i++) {
			StringBuffer temp = new StringBuffer();
			if ((i & 128) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 64) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 32) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 16) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 8) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 4) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 2) > 0)
				temp.append("1");
			else
				temp.append("0");
			
			if ((i & 1) > 0)
				temp.append("1");
			else
				temp.append("0");
			decodingBitMap.put((byte)i, temp.toString());
		}
	}
}