import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


/**
 * <code>popupTriggerListener</code> - class for listening mouse events
 * @author Admin
 * modified by kannankuttalam,divyaradhakrishnanprabhakaran
 */
@SuppressWarnings("unused")
public class popupTriggerListener implements MouseListener {

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	JPopupMenu pop;
	JTextPane ta;
	// constructors
			/** Constructs the Mouse Listener for the popup menu 
			 * @param popup popup menu
			 * @param text text in textpane
		     */
	public popupTriggerListener( JPopupMenu popup, JTextPane text)
	{
		pop = popup;
		ta = text;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	/** Mouse press Event 
	 *  @param e event
     */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.isPopupTrigger())
		{
			pop.show(ta, e.getX(), e.getY());
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	/** Mouse release Event 
	 *  @param e event
     */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.isPopupTrigger())
		{
			pop.show(ta, e.getX(), e.getY());
		}
	}

	/** Mouse click Event 
	 *  @param e event
     */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** Mouse entered Event 
	 *  @param e event
     */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/** Mouse exit Event 
	 *  @param e event
     */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}