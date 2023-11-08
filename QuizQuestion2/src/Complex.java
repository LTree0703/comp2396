public class Complex {
    private int real;
    private int imaginary;

    Complex(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public void add(Complex c) {
        real += c.real;
        imaginary += c.imaginary;
    }

    public void subtract(Complex c) {
        real -= c.real;
        imaginary -= c.imaginary;
    }

    public String toString() {
        if (imaginary < 0) {
            return real + " - " + -imaginary + "i";
        }
        else {
            return real + " + " + imaginary + "i";
        }
        
    }
}
