import java.lang.*;
import java.util.*;

// java RunGraphicServer&
// java Move
public class Move {
    public static void main(String[] args) {
	Universe u = new Universe();

	Planet s = new Planet("Sun", 2.e30); //Default position 0,0

	Vector rt = new Vector(1.49e11, 0., 0.); // 149 million km
	Vector vt = new Vector(0., 30.e3, 0.);  // 30 km/s
	Planet t = new Planet("Earth", 5.97e24);
	t.setPosition(rt);
	t.setVelocity(vt);

	Planet m = new Planet("Mars", 6.4e23);
	Vector rm = new Vector(249.e9, 0., 0.);
	Vector vm = new Vector(0., 24.e3, 0.);
	m.setPosition(rm);
	m.setVelocity(vm);

	Planet j = new Planet("Jupiter", 1.9e27);
	j.setPosition(new Vector(816.e9, 0., 0.));
	j.setVelocity(new Vector(0., 13.e3, 0.));

	u.add(s);
	u.add(t);
	u.add(m);
	u.add(j);

	List<Planet> p = u.planets();
	Iterator<Planet> i = p.iterator();

	System.out.println("********************** Initial positions and velocity");
	while (i.hasNext()) {
	    System.out.println(i.next().toString());
	}

	//TerminalObserver to = new TerminalObserver(); //Print components' evolution on terminal
	//FileObserver fo = new FileObserver("Test.dat"); //Print components' evolution on file Test.dat
	//u.attach(to);
	//u.attach(fo);

	SocketObserver so = new SocketObserver();
	u.attach(so);
	
	u.evolve(3650, 86400); //evolve(int n, double dt) //10 years evolution in step of 1 day
	//u.evolve(1, 1); //evolve(int n, double dt) // Quick test

    }//main
}