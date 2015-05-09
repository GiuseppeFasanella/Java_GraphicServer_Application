import java.text.*;

class Vector {

    static int CARTESIAN =     0;
    static int POLAR     =     1;
    static int PLAIN     = 32767;

    double _x;
    double _y;
    double _z;
    
    Vector() {
	_x = 0;
	_y = 0;
	_z = 0;
    }

    Vector(double x, double y, double z) {
	_x = x;
	_y = y;
	_z = z;
    }
    
    public void set(Vector x) {
	_x = x.x();
	_y = x.y();
	_z = x.z();
    }

    public void set(double x, double y, double z) {
	_x = x;
	_y = y;
	_z = z;
    }

    public Vector plus(Vector x) {
	Vector v = new Vector(_x + x.x(), _y + x.y(), 
			      _z + x.z());
	return v;
    }

    public Vector minus(Vector x) {
	Vector v = new Vector(_x - x.x(), _y - x.y(), 
			      _z - x.z());
	return v;
    }

    public Vector times(double x) {
	Vector v = new Vector(_x * x, _y * x, _z * x);
	return v;
    }

    public String toString() {
	String ret = "(";
	/*DecimalFormat formatter = new DecimalFormat("#.##E0");
	ret += formatter.format(x()) + ", " +
	    formatter.format(y()) + ", " +
	    formatter.format(z()) + ")";*/
	ret += x() + ", " + y() + ", " + z() + ")";
	return ret;
    }

    public String toString(int format) {
	String ret = null;
	if (format == CARTESIAN) {
	    ret = toString();
	} else if (format == POLAR) {
	    ret = "(R=";
	    DecimalFormat formatter = new DecimalFormat("#.##E0");
	    ret += formatter.format(r()) + ", Theta=" +
		formatter.format(theta()) + ", Phi=" +
		formatter.format(phi()) + ")";
	} else if (format == PLAIN) {
	    DecimalFormat formatter = new DecimalFormat("#.##E0");
	    ret =x() + " " + y() + " " + z();
	}
	return ret;
    }

    double x() {
	return _x;
    }

    double y() {
	return _y;
    }

    double z() {
	return _z;
    }

    double r() {
	return Math.sqrt(_x*_x + _y*_y + _z*_z);
    }

    double theta() {
	return Math.acos(_z/r());
    }

    double phi() {
	return Math.atan2(_y, _x);
    }

}