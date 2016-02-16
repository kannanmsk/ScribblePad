
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.UndoManager;

import org.apache.axis.utils.*;
import org.apache.commons.validator.routines.UrlValidator;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton.*;

import org.apache.axis.utils.*;
import org.apache.commons.validator.routines.UrlValidator;

import com.twilio.sdk.TwilioRestException;

/**
 *  <code>UserInterface</code> - class implementing the GUI of ScribblePad
 *  CIS 551 Modern Programming in Java
 *  Project
 * @author kannankuttalam
 * @author divyaradhakrishnanprabhakaran
 */
@SuppressWarnings({ "serial", "unused"})
public class UserInterface extends JFrame implements ActionListener {
    JPanel p = new JPanel();
    JPanel statuspanel = new JPanel();
    JMenuBar mb ;
    JMenu filemenu, editmenu, formatmenu, thememenu, toolsmenu,helpmenu;
    JLabel statuslabel, wordcountlabel, linecountlabel;
    JButton newfile, open, save, find, cut, copy, paste, selectall,  fontcolour,bold,undo, redo, italics;
    JMenuItem newfilemenu, openfilemenu, savefilemenu, frmenu, cutmenu, copymenu, pastemenu, selectallmenu, filecomparemenu,aboutmenu, undomenu, redomenu, cancelmenu,mailmenu,sendsmsmenu,fontcolourmenu;
    JToolBar tb = new JToolBar();
    JTextPane ta = new JTextPane() ;
    JScrollPane sp = new JScrollPane();
  
    
    Icon inew  = new ImageIcon("src/new.png");
    Icon iopen = new ImageIcon("src/open.png");
    Icon isave = new ImageIcon("src/save.png");
    Icon ifind = new ImageIcon("src/search.png");
    Icon icut  = new ImageIcon("src/Cut-24.png");
    Icon icopy = new ImageIcon("src/Copy-24.png");
    Icon ipaste = new ImageIcon("src/Paste-24.png");
    Icon iundo = new ImageIcon("src/Undo-24.png");
    Icon iredo = new ImageIcon("src/Redo-24.png");
    Icon ifont = new ImageIcon("src/Text Color-24.png");
    Icon ibold = new ImageIcon("src/Bold-24.png");
    Icon iitalic = new ImageIcon("src/Italic-24.png");
    String textcopy;
    JPopupMenu pop = new JPopupMenu();
    JMenuItem copyi , cuti, pastei,mailto,sendsms, system,metal,nimbus, selectalli,filecompare,bolditalic, boldmenu, italicmenu;
    UndoManager um = new UndoManager();
    JTextField wordfield, linefield;
    String text, countint, linecountint;
    int count = 0, linecount =0;
    UIManager.LookAndFeelInfo info[] = UIManager.getInstalledLookAndFeels();
    @SuppressWarnings("rawtypes")
	JComboBox fontbox,fontsize;
   
    
 // constructors
 		/** Constructs the GUI for ScribblePad 
 	     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserInterface()
	{
		
		super(" ScribblePad");
		setSize(700,700);
		
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new BorderLayout());
		
		mb= new JMenuBar();
		filemenu = new JMenu("File");
		newfilemenu = new JMenuItem("New");
		openfilemenu = new JMenuItem("Open");
		savefilemenu = new JMenuItem("Save");
		cancelmenu = new JMenuItem("Close");
		newfilemenu.addActionListener(this);
		filemenu.add(newfilemenu);
		openfilemenu.addActionListener(this);
		filemenu.add(openfilemenu);
		savefilemenu.addActionListener(this);
		filemenu.add(savefilemenu);
		cancelmenu.addActionListener(this);
		filemenu.add(cancelmenu);
		
		
		
		editmenu = new JMenu("Edit");
		cutmenu = new JMenuItem("Cut");
		copymenu = new JMenuItem("Copy");
		pastemenu = new JMenuItem("Paste");
		selectallmenu = new JMenuItem("SelectAll");
		frmenu = new JMenuItem ("FindAndReplace");
		undomenu = new JMenuItem(" Undo");
		redomenu = new JMenuItem(" Redo");
		cutmenu.addActionListener(this);
		editmenu.add(cutmenu);
		copymenu.addActionListener(this);
		editmenu.add(copymenu);
		pastemenu.addActionListener(this);
		editmenu.add(pastemenu);
		selectallmenu.addActionListener(this);
		editmenu.add(selectallmenu);
		frmenu.addActionListener(this);
		editmenu.add(frmenu);
		undomenu.addActionListener(this);
		editmenu.add(undomenu);
		redomenu.addActionListener(this);
		editmenu.add(redomenu);
		
		formatmenu = new JMenu("Format");
		thememenu = new JMenu("Theme");
		system = new JMenuItem("System");
		system.addActionListener(this);
		nimbus = new JMenuItem("Nimbus");
		nimbus.addActionListener(this);
		metal = new JMenuItem("Metal");
		metal.addActionListener(this);
		formatmenu.add(thememenu);
		thememenu.add(metal);
		thememenu.add(nimbus);
		thememenu.add(system);
		boldmenu = new JMenuItem("Bold");
		boldmenu.addActionListener(this);
		formatmenu.add(boldmenu);
		italicmenu = new JMenuItem("Italic");
		italicmenu.addActionListener(this);
		formatmenu.add(italicmenu);
		fontcolourmenu = new JMenuItem("Font Color");
		fontcolourmenu.addActionListener(this);
		formatmenu.add(fontcolourmenu);
       
		toolsmenu = new JMenu("Tools");
		filecompare = new JMenuItem("File Compare");
		filecompare.addActionListener(this);
		mailmenu = new JMenuItem("Send Mail");
		mailmenu.addActionListener(this);
		sendsmsmenu = new JMenuItem("Send SMS");
		sendsmsmenu.addActionListener(this);
		toolsmenu.add(filecompare);
		toolsmenu.add(mailmenu);
		toolsmenu.add(sendsmsmenu);
		
		helpmenu = new JMenu("Help");
		aboutmenu = new JMenuItem("About");
		aboutmenu.addActionListener(this);
		helpmenu.add(aboutmenu);
		
		mb.add(filemenu);
		mb.add(editmenu);
		mb.add(formatmenu);
		mb.add(toolsmenu);
		mb.add(helpmenu);		
		this.setJMenuBar(mb);
		
		
		
		copyi = new JMenuItem( " Copy ");
		copyi.addActionListener(this);
		pop.add(copyi);
		cuti = new JMenuItem( " Cut ");
		cuti.addActionListener(this);
		pop.add(cuti);
		pastei = new JMenuItem(" Paste ");
		pastei.addActionListener(this);
		pop.add(pastei);
		selectalli =new JMenuItem(" SelectAll");
		selectalli.addActionListener(this);
		pop.add(selectalli);
		mailto = new JMenuItem( " Mail ");
		mailto.addActionListener(this);
		pop.add(mailto);
		sendsms = new JMenuItem( "Send SMS");
		sendsms.addActionListener(this);
		pop.add(sendsms);
		ta.add(pop);
		ta.addMouseListener(new popupTriggerListener(pop,ta));

		
		newfile = new JButton(inew);
		newfile.addActionListener(this);
		tb.add(newfile);
		open = new JButton(iopen);
		open.addActionListener(this);
		tb.add(open);
		save = new JButton(isave);
		save.addActionListener(this);
		tb.add(save);
		find = new JButton(ifind);
		find.addActionListener(this);
		tb.add(find);
		cut = new JButton (icut);
		cut.addActionListener(this);
		tb.add(cut);
		copy = new JButton(icopy);
		copy.addActionListener(this);
		tb.add(copy);
		paste = new JButton(ipaste);
		paste.addActionListener(this);
		tb.add(paste);
		fontcolour = new JButton(ifont);
		fontcolour.addActionListener(this);
		tb.add(fontcolour);
		bold = new JButton(ibold);
		//bold.setAction(new StyledEditorKit.BoldAction());
		bold.addActionListener(this);
		tb.add(bold);
		italics = new JButton(iitalic);
		//italics.setAction(new StyledEditorKit.ItalicAction());
		italics.addActionListener(this);
		tb.add(italics);
		undo = new JButton(iundo);
		undo.addActionListener(this);
		tb.add(undo);
		redo = new JButton(iredo);
		redo.addActionListener(this);
		tb.add(redo);
		
		
		ta.getDocument().addUndoableEditListener(new UndoableEditListener() {
		       public void undoableEditHappened(UndoableEditEvent evt) {
		           um.addEdit(evt.getEdit());
		       }
		   });
		fontbox = new JComboBox();
		fontbox.setEditable(true);
		fontbox.addItem("Arial");
		fontbox.addItem("Verdana");
		fontbox.addItem("Seriff");
		fontbox.addItem("Times New Roman");
		fontbox.addItem("Calibri");
		fontbox.addItem("Cambria");
		fontbox.addItem("Chiller");
		fontsize = new JComboBox();
		fontsize.addItem(10);
		fontsize.addItem(12);
		fontsize.addItem(14);
		fontsize.addItem(16);
		fontsize.addItem(18);
		fontsize.addItem(22);
		fontsize.addItem(24);
		fontsize.setSelectedItem(12);
		
		
		fontbox.addActionListener(this);
		tb.add(fontbox);
		fontsize.addActionListener(this);
		tb.add(fontsize);
		
		statuslabel = new JLabel(" Status :");
		wordcountlabel = new JLabel (" Character count ");
		wordfield = new JTextField(3);
		/*linecountlabel = new JLabel(" Line count");
		linefield = new JTextField(3); */
	    statuspanel = new JPanel();
	    statuspanel.add(statuslabel);
	    statuspanel.add(wordcountlabel);
	    statuspanel.add(wordfield);
	
	    
		sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p.add(tb, BorderLayout.NORTH);
		p.add(sp, BorderLayout.CENTER);
		p.add(statuspanel, BorderLayout.SOUTH);
		
		//Document listener for character count
		ta.getDocument().addDocumentListener(new DocumentListener()
		{

			@Override
			public void changedUpdate(DocumentEvent de) {
				
				
				Document d = de.getDocument();			
				count = d.getLength();
				countint = Integer.toString(count);
				wordfield.setText(countint); 
				
			
				
			}

			@Override
			public void insertUpdate(DocumentEvent de) {
                 Document d = de.getDocument();
				
				count = d.getLength();
				countint = Integer.toString(count);
				wordfield.setText(countint);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
                Document d = de.getDocument();
				
				count = d.getLength();
				countint = Integer.toString(count);
				wordfield.setText(countint);
			}
	
		});

		
	    add(p);
	    
	    setVisible(true);
	}
	

	/** Action performed in ScribblePad
	 *  @param e Action event 
     */
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {

		 if( e.getSource() == newfile || e.getSource() == newfilemenu)
		 {
			 ta.setText("");
		 }
		
		 if( e.getSource() == open || e.getSource() == openfilemenu )
		 {
			 JFileChooser fc = new JFileChooser();
			 int chosen = fc.showOpenDialog(this);
			 if ( chosen == fc.APPROVE_OPTION)
			 {
				 File file = fc.getSelectedFile();
				 try {
					openFiles(file);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
		 }
		 
		 if (e.getSource() == save || e.getSource() == savefilemenu)
			 		 {
			 			 JFileChooser fc = new JFileChooser();
			 			 //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			 		 int chosen = fc.showSaveDialog(this);
			 			 if ( chosen == fc.APPROVE_OPTION)
			 			 {
			 			 String path=fc.getSelectedFile().getAbsolutePath();
						 //String filename=fc.getSelectedFile().getName();
			 				 try {
								saveFiles(path);
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			 			 }
			 		 }
		 
		if( e.getSource() == frmenu || e.getSource() == find)
		{
			Find f = new Find(ta);		
		}		
		
		if( e.getSource() == cancelmenu)
		{
			System.exit(0);
		}
		
		if( e.getSource() == fontcolour || e.getSource() == fontcolourmenu)
		{
			Color c = JColorChooser.showDialog(this, " Choose color ", ta.getForeground());
			
			if( c!= null)
			{
				int start = ta.getSelectionStart() ;
				int end = ta.getSelectedText().length();
			    String item = (String) fontbox.getSelectedItem();
			    Font font = new Font(item,Font.PLAIN,(int) fontsize.getSelectedItem());
			    textPaneChange(ta, font, c, start, end);
			}
		}
		
		if( e.getSource() == cut || e.getSource() == cutmenu || e.getSource()==cuti)
		{
			ta.cut();
		}
		
		if( e.getSource() == copy || e.getSource() == copymenu || e.getSource() == copyi)
		{
			ta.copy();	
		}
		
		if( e.getSource() == paste || e.getSource() == pastemenu || e.getSource() == pastei)
		{
			ta.paste();
		}
		
		
		if (e.getSource() == mailto)
		{
			String to = JOptionPane.showInputDialog("Recipient Email address : ");
			SendMail s = new SendMail(to,ta.getSelectedText());
			s.send();
		}
		
		if (e.getSource() == sendsms)
		{
			String to = JOptionPane.showInputDialog("Enter a Valid US Number : ");
			SendSMS s = new SendSMS(to,ta.getSelectedText());
			try {
				s.sendsms();
			} catch (TwilioRestException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == filecompare)
		{
			JFileChooser fc = new JFileChooser();
			 int chosen = fc.showOpenDialog(this);
			 if ( chosen == fc.APPROVE_OPTION)
			 {
				 File file = fc.getSelectedFile();
				 compareFiles(file);
			 }
		}
		
		if (e.getSource() == aboutmenu)
		{
			File htmlFile = new File("src/About.html");
			try {
				Desktop.getDesktop().browse(htmlFile.toURI());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == undo || e.getSource() == undomenu)
		{
			if(um.canUndo())
			{
				um.undo();
			} 
		}
		
		if(e.getSource() == redo || e.getSource() == redomenu)
		{
			if(um.canRedo())
			{
				um.redo();
			}
		}
		
		if( e.getSource() == bold || e.getSource() == boldmenu)
		{
			int start = ta.getSelectionStart() ;
			int end = ta.getSelectedText().length();
			String item = (String) fontbox.getSelectedItem();
			Font font = new Font(item,Font.BOLD,(int) fontsize.getSelectedItem());
			textPaneChange(ta, font, Color.black,start,end);
		}
		
		if( e.getSource() == italics || e.getSource() == italicmenu)
		{
			int start = ta.getSelectionStart() ;
			int end = ta.getSelectedText().length();
			String item = (String) fontbox.getSelectedItem();
			Font font = new Font(item,Font.ITALIC,(int) fontsize.getSelectedItem());
			textPaneChange(ta, font, Color.black,start,end);
		}

		if(e.getSource() == selectall || e.getSource() == selectallmenu || e.getSource() == selectalli)
		{
			ta.selectAll();
			ta.requestFocus();
		}
		
		if(e.getSource() == system)
		{
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(Window window : JFrame.getWindows()) {
		        SwingUtilities.updateComponentTreeUI(window);
		 }
		}
		
		if(e.getSource() == metal)
			
			{
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(Window window : JFrame.getWindows()) {
			        SwingUtilities.updateComponentTreeUI(window);
			 }
			}
		
		if( e.getSource() == nimbus)
	
			{
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(Window window : JFrame.getWindows()) {
			        SwingUtilities.updateComponentTreeUI(window);
			 
			}
			}
		
		if(e.getSource() == fontbox )
		{
			int start = ta.getSelectionStart() ;
			int end = ta.getSelectedText().length();
		    String item = (String) fontbox.getSelectedItem();
		    Font font = new Font(item,Font.PLAIN,(int) fontsize.getSelectedItem());
		    textPaneChange(ta, font, null, start, end);

		}
		
		if(e.getSource() == fontsize)
		{
			int start = ta.getSelectionStart() ;
			int end = ta.getSelectedText().length();
		    String item = (String) fontbox.getSelectedItem();
		    Font font = new Font(item,Font.PLAIN,(int) fontsize.getSelectedItem());
		    textPaneChange(ta, font, null, start, end);
		}
		
	}
	
	/** Open File feature
	 *  @param file File selected from Open Dialog box
     */
	public void openFiles( File file) throws BadLocationException 
	{
		ta.setText("");
		try {
			FileReader read  = new FileReader(file.getPath());
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(read);
			while(sc.hasNext())
			{
				String li = sc.nextLine();
				
				this.textPaneAddition(li);
				this.textPaneAddition("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Appending string in textpane
	 *  @param str String to be appended
     */
	public void textPaneAddition(String text) throws BadLocationException
	{
	     StyledDocument d = (StyledDocument) ta.getDocument();
	     d.insertString(d.getLength(), text, null);
	 }
	
	/** Set text style with provided attributes
	 *  @param jtp text pane
	 *  @param c color passed
	 *  @param from start position of the text
	 *  @param to end position of the text
     */
	public static void textPaneChange(JTextPane textpane, Font font, Color color,int start,int end) {
        MutableAttributeSet attributes = textpane.getInputAttributes();   
        StyleConstants.setFontFamily(attributes, font.getFamily());
        StyleConstants.setFontSize(attributes, font.getSize());
        StyleConstants.setItalic(attributes, (font.getStyle() & Font.ITALIC) != 0);
        StyleConstants.setBold(attributes, (font.getStyle() & Font.BOLD) != 0);
        StyleConstants.setForeground(attributes, color);
        StyledDocument document = textpane.getStyledDocument();
        document.setCharacterAttributes(start, end, attributes, true);
    }
	
	/** Saves the file 
	 *  @param path absolute path of the file
	 */
	public void saveFiles(String path) throws BadLocationException
	{
		String[] schemes = {"http","https"};
		UrlValidator urlValidator = new UrlValidator(schemes);
		try {
			//FileReader path  = new FileReader(file.getPath());
			File f = new File(path);
			if (!f.exists()) {
				f.createNewFile();
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(ta.getText());
			bw.close();
			
			
			
			if(path.substring(path.length()-4,path.length()).equals("java")) {
			
			
			String temp = ta.getText();
			ta.setText("");
			String strapp = "";
			int count=0;
			
			for (int i=0;i<temp.length();i++)
			{
				 
				 if(Character.isLetter(temp.charAt(i)))
				 {
					 strapp += temp.charAt(i);
				 }
				 else
				 {
					 if (JavaUtils.isJavaKeyword(strapp))
					 {
					     this.textPaneAddition(strapp);
					     String item = (String) fontbox.getSelectedItem();
						 Font font = new Font(item,Font.ITALIC,(int) fontsize.getSelectedItem());
					     textPaneChange(ta, font, Color.blue,i-strapp.length(),i);
					     strapp = "";
					     this.textPaneAddition(""+temp.charAt(i));
					 }
					 else
					 {
						 this.textPaneAddition(strapp);
						 String item = (String) fontbox.getSelectedItem();
						 Font font = new Font(item,Font.BOLD,(int) fontsize.getSelectedItem());
					     textPaneChange(ta, font, Color.black,i-strapp.length(),i);
					     strapp = "";
					     this.textPaneAddition(""+temp.charAt(i));
					 }
				 }
				count = i; 
			}
			if (JavaUtils.isJavaKeyword(strapp))
			 {
				 this.textPaneAddition(strapp);
				 String item = (String) fontbox.getSelectedItem();
				 Font font = new Font(item,Font.ITALIC,(int) fontsize.getSelectedItem());
			     textPaneChange(ta, font, Color.blue,count-(strapp.length()-1),count);
			     strapp = "";
			 }
			else
			 {
				 this.textPaneAddition(strapp);
				 String item = (String) fontbox.getSelectedItem();
				 Font font = new Font(item,Font.BOLD,(int) fontsize.getSelectedItem());
			     textPaneChange(ta, font, Color.black,count-(strapp.length()-1),count);
			     strapp = "";
			     
			 }			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/** File Compare 
	 *  @param f File to be compared
	 */
	public void compareFiles(File f)
	{
		try {
			Document doc = ta.getDocument();
			FileReader read  = new FileReader(f.getPath());
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(read);
			String li = "";
			while(sc.hasNext())
			{
				li += sc.nextLine() + "\n";
				
			}
			
			String cmp = doc.getText(0, doc.getLength());
			if (cmp.equals(li))
			{
				JOptionPane.showMessageDialog(null, "Both the files are equal :) ", "File Compare", 1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Both the files are not equal :( ", "File Compare", 0);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
