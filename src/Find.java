import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;

/**
 *  <code>Find</code> - class implementing the Find and Replace feature of ScribblePad
 *  CIS 551 Modern Programming in Java
 *  Project
 * @author kannankuttalam
 * @author divyaradhakrishnanprabhakaran
 */

@SuppressWarnings({ "unused", "serial" })
public class Find extends JFrame implements ActionListener{

	// fields -------------------------------------------------
    /** Text Fields */
	JTextField findfield , replacefield;
	/** Labels */
	JLabel findl , replacel;
	/** Buttons */
	JButton findb, replaeb, replaceallb, cancel;
	/** Text Pane */
	JTextPane text;
	/** Panel */
	JPanel fp;
	int offsetreplacenext,startindexreplacement;
	
	// constructors
		/** Constructs the Find and Replace GUI 
		 * @param txt Text for finding
	     */
	public Find(JTextPane txt)
	{
		super(" Find And Replace ");
		text = txt;
		setSize(450,150);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		fp = new JPanel();
		
		findl = new JLabel( " Find ");
		findfield = new JTextField(30);
		replacefield = new JTextField(30);
		replacel = new JLabel(" Replace With ");
		
		findb = new JButton(" Find ");
		findb.addActionListener(this);
		replaeb = new JButton( " Replace ");
		replaeb.addActionListener(this);
		replaceallb = new JButton( " ReplaceAll ");
		replaceallb.addActionListener(this);
		cancel = new JButton ( " Cancel ");
		cancel.addActionListener(this);
		GridLayout gl = new GridLayout(2,2,5,5);
		//BoxLayout bx = new BoxLayout();
		fp.setLayout(gl);
		fp.add(findl);
		fp.add(findfield);
		fp.add(replacel);
		fp.add(replacefield);
		fp.add(findb);
		fp.add(replaeb);
		fp.add(replaceallb);
		fp.add(cancel);
		
		
		add(fp);
		
	    setVisible(true);
		
		
		
	}
	
	/** Event Handler
	 *  @param e event
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == findb)
		{
			
				FindWord();
			} 
		
		
		if( e.getSource() == replaceallb)
		{
			replaceAll();
		}
		
		if( e.getSource() == replaeb)
		{
			replacenext();
		}
		
		if( e.getSource() == cancel)
		{
			this.setVisible(false);
		}
		
		}
	
	/** Finds the specified word
     */
public void FindWord() 	{
		
		Document doc = text.getDocument();
		int count = 0;
	try
	 {
		String word = findfield.getText();
		int length = word.length();
		int startindex = 0;
		
		while(startindex + length < doc.getLength())
		{
			String found = doc.getText(startindex, length);
			if(word.equals(found))
			{
				count++;
				text.getHighlighter().addHighlight(startindex, startindex+length , new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN));
			}
			startindex++;
		}
		}
			catch (BadLocationException e) {
					e.printStackTrace();
				}
		JOptionPane.showMessageDialog(null, "The total number of occurences : "+count);	
	}
	
	/** Replaces all the occurrences of the specified word
     */
public void replaceAll()
{
	try
	{
	String fword = findfield.getText();
	int length = fword.length();
	String rword = replacefield.getText();
	Document doc = text.getDocument();
	int startindex = 0;
	while(startindex + length < doc.getLength())
	{
		String found = doc.getText(startindex, length);
		if(fword.equals(found))
		{
			text.select(startindex, startindex+length);
			text.replaceSelection(rword);
		}
		startindex++;
	}
	}
	catch(BadLocationException e) {
		e.printStackTrace();
	}
	}
	
	/** Replaces the next occurrence of the specified word
     */
public void replacenext()
{
	try
	{
	String fword = findfield.getText();
	int length = fword.length();
	String rword = replacefield.getText();
	Document doc = text.getDocument();
	startindexreplacement = 0;
	while(startindexreplacement + length < doc.getLength())
	{
		String found = doc.getText(startindexreplacement, length);
		if(fword.equals(found))
		{
			text.select(startindexreplacement, startindexreplacement+length);
			text.replaceSelection(rword);
			startindexreplacement++;
			break;
		}
		startindexreplacement++;
	}
	}
	catch(BadLocationException e) {
		e.printStackTrace();
	}
}
}