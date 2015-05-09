import java.io.*;
import java.awt.*;
import java.awt.event.*;

class RunGraphicServer extends Frame implements WindowListener {
    static GraphicServer _s;//This is the attribute of RunGraphicServer

    public static void main(String args[]) {
	RunGraphicServer r = new RunGraphicServer("Graphic Server");
	r.addWindowListener(r);//r has himself as WindowListener
	_s.run();
	System.exit(0);
    }

    public RunGraphicServer(String title) {
	// the constructor of a Frame has a title as a parameter
	super(title);
	_s = new GraphicServer(this, 9997);
	setSize(800, 800);
	setBackground(Color.BLACK);
	setVisible(true);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
	_s.paint(g);
    }


    /// Dummy methods
    //Those dummy methods are used in order to avoid compiling problems
    // RunGraphicServer is not abstract and does not override abstract method windowOpened(java.awt.event.WindowEvent)
    public void windowActivated ( WindowEvent e )  {
    }

    public void windowDeactivated ( WindowEvent e )  {
    }

    public void windowIconified ( WindowEvent e )  {
    }

    public void windowDeiconified ( WindowEvent e )  {
    }

    public void windowClosing(WindowEvent e) {
        setVisible(false);
        dispose();
        System.exit(0);
    }

    public void windowOpened ( WindowEvent e )  {
    }

    public void windowClosed ( WindowEvent e )  {
    }



}