public class Planet {
    static double G = 6.673e-11;

    double _mass;
    String _name;
    Vector _x;
    Vector _v;

    Planet(String name, double mass) {
	_name = name;
	_x = new Vector();
	_v = new Vector();
	_mass = mass;
    }

    public String name() {
	return _name;
    }

    public Vector x() {
	return _x;
    } 

    public Vector v() {
	return _v;
    } 

    public double mass() {
	return _mass;
    } 

    public void setPosition(Vector x) {
	_x = x;
    }

    public void setVelocity(Vector v) {
	_v = v;
    }

    public String toString() {
	String ret = name() + "\n";
	for (int i = 0; i < name().length(); i++) {
	    ret += "=";
	}
	ret += "\nm=" + mass() + " kg";
	ret += "\nx=" + x().toString();
	ret += "\nv=" + v().toString(); 
	return ret;
   }

    public Vector force(Planet p) {
	Vector r = x().minus(p.x());
	Vector f = new Vector();
	if (r.r() != 0) {
	    f = r.times(-G*mass()*p.mass()/Math.pow(r.r(), 3.));
	}
	return f;
    }

    public void evolve(Vector f, double dt) {
	Vector a = new Vector();
	a = f.times(1./mass());
	_x = _x.plus(_v.times(dt));
	_v = _v.plus(a.times(dt));
    }
}
