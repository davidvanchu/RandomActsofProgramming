import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigInteger;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class HuffmanCompressionGUI extends javax.swing.JFrame implements ActionListener, KeyListener {
	HuffmanCompressor compressor;
	HuffmanDecompress decompressor;
	String input;
	Hashtable<Character, String> encodingTable;
	String binary;	
	
	private JLabel inputLabel;
	private JLabel uncompressedLabel;
	private JLabel compressedLabel;
	private JLabel uncompressedSizeLabel;
	private JLabel compressedSizeLabel;
	private JLabel encodeTableLabel;
	
	private JPanel inputPanel;
	private JPanel inputAndUncompressedPanel;
	private JPanel compressedPanel;
	private JPanel uncompressedPanel;
	private JPanel compAndEncodeTablePanel;
	private JPanel encodeTablePanel;
	
	private JButton compressButton;
	private JPanel compressButtonPanel;
	
	private JTextArea inputTextArea;
	private JTextArea uncompressedTextArea;
	private JTextArea compressedTextArea;
	private JTextArea encodeTableArea;
	
	private JScrollPane uncompScroll;
	private JScrollPane inputScroll;
	private JScrollPane compScroll;
	private JScrollPane encodeScroll;
	
	
	public HuffmanCompressionGUI() {
		initComponents();
		compressor = new HuffmanCompressor();
		decompressor = new HuffmanDecompress();
		encodingTable = new Hashtable<Character, String>();
	}

	private void initComponents() {
		inputLabel = new JLabel("Input Text");
		uncompressedLabel = new JLabel("Uncompressed Text");
		compressedLabel = new JLabel("Compressed Text");
		uncompressedSizeLabel = new JLabel("Uncompressed Size 0 bits");
		compressedSizeLabel = new JLabel("Compressed Size 0 bits");
		encodeTableLabel = new JLabel("Encoding Table");
		
		compressButton = new JButton("Press 2 Compress");
		compressButton.setPreferredSize(new Dimension(150, 44));
		
		compressButton.setActionCommand("compress");
		compressButton.addActionListener(this);
		
		inputTextArea = new JTextArea(15,25);
		//inputTextArea.setPreferredSize(new Dimension(300, 350));
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);
		
		inputTextArea.addKeyListener(this);
		
		KeyStroke remove = KeyStroke.getKeyStroke("ENTER");
		InputMap im = inputTextArea.getInputMap();
		im.put(remove, "none");
		
		inputScroll = new JScrollPane(inputTextArea);
		inputScroll.setBounds(0,0,100,100);
		inputScroll.setSize(100, 100);
		inputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		uncompressedTextArea = new JTextArea(10,25);
		//uncompressedTextArea.setPreferredSize(new Dimension(300, 350));
		uncompressedTextArea.setLineWrap(true);
		uncompressedTextArea.setWrapStyleWord(true);
		
		uncompScroll = new JScrollPane(uncompressedTextArea);
		uncompScroll.setBounds(0,0,100,100);
		uncompScroll.setSize(100, 100);
		uncompScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		compressedTextArea = new JTextArea(10,25);
		//compressedTextArea.setPreferredSize(new Dimension(300, 200));
		compressedTextArea.setLineWrap(true);
		compressedTextArea.setWrapStyleWord(true);
		
		compScroll = new JScrollPane(compressedTextArea);
		compScroll.setBounds(0,0,100,100);
		compScroll.setSize(100, 100);
		compScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		encodeTableArea = new JTextArea(10,25);
		//encodeTableArea.setPreferredSize(new Dimension(300, 200));
		encodeTableArea.setLineWrap(true);
		encodeTableArea.setWrapStyleWord(true);
		
		encodeScroll = new JScrollPane(encodeTableArea);
		encodeScroll.setBounds(0,0,100,100);
		encodeScroll.setSize(100, 100);
		encodeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		uncompressedPanel = new JPanel();
		uncompressedPanel.setLayout(new BorderLayout(10, 10));
		uncompressedPanel.setSize(320, 300);
		uncompressedPanel.add(uncompressedLabel, BorderLayout.NORTH);
		uncompressedPanel.add(uncompScroll, BorderLayout.WEST);
		
		inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout(10, 10));
		inputPanel.setSize(320, 300);
		inputPanel.add(inputLabel, BorderLayout.NORTH);
		inputPanel.add(inputScroll, BorderLayout.WEST);
		
		inputAndUncompressedPanel = new JPanel();
		inputAndUncompressedPanel.setLayout(new BorderLayout(10,10));
		inputAndUncompressedPanel.setSize(320, 300);
		inputAndUncompressedPanel.add(inputPanel, BorderLayout.WEST);
		inputAndUncompressedPanel.add(uncompressedPanel, BorderLayout.EAST);
		
		compressedPanel = new JPanel();
		compressedPanel.setLayout(new BorderLayout(10, 10));
		compressedPanel.setSize(300, 100);
		compressedPanel.add(compScroll, BorderLayout.WEST);
		compressedPanel.add(compressedLabel, BorderLayout.NORTH);
		
		encodeTablePanel = new JPanel();
		encodeTablePanel.setLayout(new BorderLayout(10, 10));
		encodeTablePanel.setSize(300, 100);
		encodeTablePanel.add(encodeScroll, BorderLayout.WEST);
		encodeTablePanel.add(encodeTableLabel, BorderLayout.NORTH);
		
		compAndEncodeTablePanel = new JPanel();
		compAndEncodeTablePanel.setLayout(new BorderLayout(10, 10));
		compAndEncodeTablePanel.setSize(320, 200);
		compAndEncodeTablePanel.add(encodeTablePanel, BorderLayout.WEST);
		compAndEncodeTablePanel.add(compressedPanel, BorderLayout.EAST);
		
		compressButtonPanel = new JPanel();
		compressButtonPanel.setLayout(new BorderLayout(50, 50));
		compressButtonPanel.setSize(150, 44);
		compressButtonPanel.add(uncompressedSizeLabel, BorderLayout.WEST);
		compressButtonPanel.add(compressButton, BorderLayout.CENTER);
		compressButtonPanel.add(compressedSizeLabel, BorderLayout.EAST);
		
		this.setLayout(new BorderLayout(44, 44));
		this.setTitle("Press2Compress Huffman Compression");
		this.setSize(640, 800);
		
		this.add(inputAndUncompressedPanel, BorderLayout.NORTH);
		//this.add(uncompressedPanel, BorderLayout.WEST);
		this.add(compAndEncodeTablePanel, BorderLayout.CENTER);
		this.add(compressButtonPanel, BorderLayout.SOUTH);
		}
	
	public void setInputText(String s) {
		inputTextArea.setText(s);
	}
	public void setCompressedText(String s) {
		compressedTextArea.setText(s);
	}
	public void setUncompressedText(String s) {
		uncompressedTextArea.setText(s);
	}

	public void actionPerformed(ActionEvent e) {
		if (inputTextArea.getText().length() > 1 && e.getActionCommand().equals("compress")){
			input = inputTextArea.getText();
			compressor.compress(input, encodingTable);
			decompressor.decode(compressor.binaryCompressedData, encodingTable, input.length());
			
			binary = new BigInteger(input.getBytes()).toString(2);
			
			compressedTextArea.setText(compressor.compressedData);
			uncompressedTextArea.setText(binary);
			
			encodeTableArea.setText("" + encodingTable);
			
			uncompressedSizeLabel.setText("Uncompressed Size: " + input.length() * 8 +  " bits");
			compressedSizeLabel.setText("Compressed Size: " + compressor.compressedData.length() + " bits");
			
			compressor = new HuffmanCompressor();
			decompressor = new HuffmanDecompress();
			encodingTable = new Hashtable<Character, String>();
			input = "";
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == 10) {
			actionPerformed(new ActionEvent(compressButton, 1, "compress"));
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
