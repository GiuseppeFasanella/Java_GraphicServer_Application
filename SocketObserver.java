import java.util.*;
import java.net.*;
import java.io.*;

public class SocketObserver implements Observer {
   
    DataOutputStream _outDataStream;//Attribute of SocketObserver

    public SocketObserver() {
	System.out.println("");
	System.out.println("I am creating a SocketObserver");
	try {
	    InetAddress localhost = InetAddress.getByName("localhost");
	    System.out.println("Host: " + localhost);
	    Socket connection = new Socket(localhost, 9997);
	    System.out.println("Socket: " + connection);
	    OutputStream outStream = connection.getOutputStream ();
	    _outDataStream = new DataOutputStream ( outStream );
	    System.out.println("Connection ok");
	}
	catch (Exception e) {
	    System.out.println("I couldn't set a connection");
	    System.out.println(e.toString());
	}
    }

    public void notify(List<Planet> p) {
	//It creates a message, which has to be handled by the process method of the GraphicServer
	Iterator i = p.iterator();
	String msg = "";
	while (i.hasNext()) {
	    Planet planet = (Planet)i.next();
	    msg += planet.name() + " " + planet.mass() + " " + 
		planet.x().toString(Vector.PLAIN) + " ";
	}
	try {
	    if (_outDataStream != null) {
		_outDataStream.writeChars(msg + "\n");
	    }
	}
	catch (Exception e) {
	    System.out.println(e.toString());
	}
    }

    protected void finalize() throws Throwable {
	try {
	    _outDataStream.writeChars("quit\n");
	} finally {
	    super.finalize();
	}
    }
}