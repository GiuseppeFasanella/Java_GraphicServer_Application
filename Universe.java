import java.util.*;

public class Universe {
    List<Planet> _planets;
    List<Observer> _observers;

    Universe() {
	_planets = new ArrayList<Planet>();
	_observers = new ArrayList<Observer>();
    }

    public void attach(Observer o) {
	_observers.add(o);
    }

    public void add(Planet p) {
	_planets.add(p);
    }

    public List<Planet> planets() {
	return _planets;
    }

    public void evolve(int n, double dt) {
	for (int i = 0; i < n; i++) {
	    evolve(dt);
	}
    }

    public void evolve(double dt) {
	Iterator i = _planets.iterator();
	while (i.hasNext()) {
	    // loop on all planets
	    Planet p = (Planet)i.next();
	    Vector f = new Vector(0., 0., 0.);
	    Iterator j = _planets.iterator();
	    while (j.hasNext()) {
		// compute the force
		Planet p2 = (Planet)j.next();
		f = f.plus(p.force(p2));
	    }
	    p.evolve(f, dt);
	}
	// notify observers
	Iterator o = _observers.iterator();
	while (o.hasNext()) {
	    Observer obs = (Observer)o.next();
	    obs.notify(_planets);
	}
    }
}