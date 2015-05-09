import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import java.io.*;
import java.net.*;

class GraphicServer implements Runnable {

    ServerSocket _socket;
    BufferedReader _is;

    static Color[] _colors = { Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE };
    java.util.List<Ellipse2D> _planets;
    java.util.List<String> _names;
    Frame _frame;

    //// Create a GraphicServer and define the port you want to connect with //////////

    GraphicServer(Frame f, int port) {
	init(port);
	_frame = f;
	_planets = new ArrayList<Ellipse2D>();
	_names = new ArrayList<String>();
    }

    private void init(int port) {
	try {
	    _socket = new ServerSocket(port);
	}
	catch (IOException e) {
	    System.out.println(e.toString());
	}
    }
    ////////////////////////////////////////////////////////////////////////////////
    ///////// Connect the Graphic Server with the port and listen to the client////

    public void run() {
	try {
	    Socket clientSocket = _socket.accept();
	    accept(clientSocket);
	    boolean go = true;
	    while (go) {
		String line = _is.readLine();
		if (line != null) {
		    go = process(line);
		} 
	    }
	    clientSocket.close();
	} 
	catch (IOException e) {
	    System.out.println(e.toString());
	}
    }

    public void accept(Socket clientSocket){
        try {
            _is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (IOException e) {
	    System.out.println(e.toString());
        }
    }
    ////////////Connections accepted/////////////////////////////////////////////////////////
    
    ///////////If you receive a message from the client, process it and draw the planets/////
    static float SCALE = (float)3e9;
    static float MASSSCALE = 5;

    public boolean process(String line) {
        boolean ret = true;
        if (line.equals("quit")) {
            ret = false;
	} else {
	    line = line.replaceAll("[^a-z-A-Z0-9\\\\.E -]", "");
	    draw(line);
	}
        return ret;
    }

    public void draw(String line) {
        String[] components = line.split(" +");
        int n = components.length;
        int i = 0;
	int p = n/5;//???
        if (_planets.size() == 0) {
            for (int j = 0; j < p; j++) {
                _planets.add(new Ellipse2D.Double(400, 400, 1, 1));
		String s = new String();
		s = components[j*5];
		_names.add(s);
            }
        }
        int k = 0;
        while (i < n) {
	    String name = components[i];
	    double size = Math.log(Float.valueOf(components[i + 1]).floatValue())/MASSSCALE;
	    double x = Float.valueOf(components[i + 2]).floatValue()/SCALE;
            double y = Float.valueOf(components[i + 3]).floatValue()/SCALE;
            double z = Float.valueOf(components[i + 4]).floatValue()/SCALE;

	    //x, y, taglia in x del pianeta, taglia in y del pianeta
	    //(400,400) is the center of the frame 
            _planets.get(k).setFrame(x + 400, y + 400, size, size);
            i += 5;
            k++;
        }
        _frame.repaint();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Ellipse2D e = null;
	String name = null;
        for (int i = 0; i < _planets.size(); i++) {
            e = (Ellipse2D)_planets.get(i);
	    g2.setColor(_colors[i % _colors.length]);
	    name = (String)_names.get(i);
	    g2.drawString(name, (int)e.getMaxX(), (int)e.getMaxY());
            g2.draw(e);
	    g2.fill(e);
        }
    }


}
