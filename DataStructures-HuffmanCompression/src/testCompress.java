import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Scanner;


public class testCompress {
	static Hashtable<Character, String> huffEncodeTable;
	HuffmanCompressor compressor;
	HuffmanDecompress decompressor;
	String input;
	Hashtable<Character, String> encodingTable;
	String binary;
	public static void main(String[] args) throws IOException {
		HuffmanCompressor hC = new HuffmanCompressor();
		String inputData = "";
		//reads the file in and converts it into a String
		File file = new File("resources/gettys.txt");
		FileReader reader = new FileReader(file);
		Scanner scanner = new Scanner(reader);
		while (scanner.hasNextLine()) {
			inputData = inputData + (scanner.nextLine());
		}
		scanner.close();
		
		//inputData = "Hello World!";
		huffEncodeTable = new Hashtable<Character,String>();
		hC.compress(inputData, huffEncodeTable);
		
/*		for (byte b : hC.binaryCompressedData) {
			System.out.println(b);
		}
	
	*/
		//System.out.println(hC.binaryCompressedData.toString());
		
		HuffmanDecompress hD = new HuffmanDecompress();
		
		hD.decode(hC.binaryCompressedData, hC.encodingTable, inputData.length());

		
		String binary = "";
		binary = new BigInteger(inputData.getBytes()).toString(2);
		
		if (inputData.length() > 100 && hC.compressedData.length() > 100) {
			System.out.println("Original string:------------------------------------------->" + hC.inputData.substring(0, 100));
			System.out.println("Original string as bits:----------------------------------->" + binary.substring(0, 100));	
			System.out.println(binary.length());
			System.out.println("Compressed Data as Bits:----------------------------------->" + hC.compressedData.substring(0, 100));
			
			System.out.println("Decoded Data: (Orig string compressed, then decompressed)-->" + hD.decodedData.substring(0, 100));
	
			System.out.println("Uncompressed Size:----------------------------------------->" + inputData.length() * 8
					
					+ " bits \nCompressed Size:------------------------------------------->" + hC.compressedData.length() + " bits");
			
			try {
	            File newTextFile = new File("compressed.txt");
	
	            FileWriter fw = new FileWriter(newTextFile);
	            fw.write(hC.compressedData);
	            fw.close();
	            
	            File uncompressed = new File("uncompressed.txt");
	
	            FileWriter fw2 = new FileWriter(uncompressed);
	            fw2.write(binary);
	            fw2.close();
	
	
	        } catch (IOException iox) {
	            //do stuff with exception
	            iox.printStackTrace();
	        }
		}
		
		HuffmanCompressionGUI gui = new HuffmanCompressionGUI();
		gui.setVisible(true);
		gui.setInputText(inputData);
		/*
		gui.setUncompressedText(binary);
		gui.setCompressedText(hC.compressedData);
		*/
	}
}
