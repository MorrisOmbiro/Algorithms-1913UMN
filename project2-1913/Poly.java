/**
 * MORRIS OMBIRO
 * Project 2 (CSCI 1913 - Intro. to Alg.)
 * Professor James B. Moen
 * Created by misfitdev on 11/26/2017.
 */
class Poly {
    private class Term {
        private int coef, expo;
        private Term next;
        private Term(int coef, int expo, Term next) {
            this.coef = coef;
            this.expo = expo;
            this.next = next;
        }
    } // end Term
    private Term head, left, right;
    public Poly() {
        head = new Term(0, 0, null); // dummy node !
        left = head;
        right = head.next;
    }

    public Poly term(int coef, int expo) {
        left = head;
        right = head.next;
        if (expo < 0)
            throw new IllegalArgumentException("No negative exponent");
        while (right != null) {
            if (right.expo < expo) {
                left.next = new Term(coef, expo, right);
                return this;
            } else if (right.expo > expo) {
            } // skip
            else
                throw new IllegalArgumentException("Already exists");
            left = right;
            right = right.next;
        }
        left.next = new Term(coef, expo, right);
        return this;
    }

    public Poly plus(Poly that) {
        Poly this1 = new Poly();
        left = head;
        right = head.next;
        if(right == null) {
            return that;
        }
        while (right != null) { // search through this
            try {
                this1.term(right.coef, right.expo);
                left = right;
                right = right.next;
            } catch (IllegalArgumentException e) {
                left = right;
                right = right.next;
                break;
            }
        }
        that.left = that.head;
        that.right = that.head.next;
        while (that.right != null) {
            this1.add(that.right.coef, that.right.expo);
            that.left = that.right;
            that.right = that.right.next;
        }
        return this1;
    }

    public void add(int coef, int expo) {
        left = head;
        right = head.next;
        if (expo < 0)
            throw new IllegalArgumentException("No negative exponents");
        while (right != null) {
            if (right.expo < expo) {
                left.next = new Term(coef, expo, right);
                return;
            } else if (right.expo > expo) {}
            else {
                if ((right.coef + coef) == 0)
                    left.next = right.next;
                else
                    left.next = new Term(right.coef + coef, expo, right.next);
                return;
            }
            left = right;
            right = right.next;
        }
    }

    public Poly minus() {
        Poly x = new Poly();
        left = head;
        right = head.next;
        while (right != null) {
            try {
                x.term(-right.coef, right.expo);
                left = right;
                right = right.next;
            } catch (IllegalArgumentException e) {
                left = right;
                right = right.next;
                break;
            }
        }
        return x;
    }

    @Override
    public String toString() {
        Term right = head.next;
        StringBuilder s = new StringBuilder();
        if (right == null)
            return "0";
        s.append(right.coef + "x");
        appendExponent(s, right.expo);
        Term x = right.next;
        while(x != null) {
            if(x.coef < 0) {
                s.append(" - " + -x.coef + "x");
                appendExponent(s, x.expo);
                x= x.next;
            }else {

                s.append(" + " + x.coef + "x");
                appendExponent(s, x.expo);
                x = x.next;
            }
        }
        return s.toString();
    }
    private void appendExponent(StringBuilder builder, int expo)
    {
        if (expo < 0)
        {
            throw new IllegalStateException("Bad exponent " + expo + ".");
        }
        else if (expo == 0)
        {
            builder.append('⁰');
        }
        else
        {
            appendingExponent(builder, expo);
        }
    }

//  APPENDING EXPONENT. Do all the work for APPEND EXPONENT when EXPO is not 0.

    private void appendingExponent(StringBuilder builder, int expo)
    {
        if (expo > 0)
        {
            appendingExponent(builder, expo / 10);
            builder.append("⁰¹²³⁴⁵⁶⁷⁸⁹".charAt(expo % 10));
        }
    }
}

class PollyEsther
{
    public static void main(String[] args) {

        Poly p0 = new Poly();
        Poly p1 = new Poly().term(1, 3).term(1, 1).term(1, 2);
        Poly p2 = new Poly().term(2, 1).term(3, 2);
        Poly p3 = p2.minus();

        Poly p4a = new Poly().term(1,4).term(1,3).term(1,5);
        Poly p4b = new Poly().term(1,4).term(1,3).term(1,5);

        System.out.println("\t p0\t:" + p0);           //  0
        System.out.println("\t p1\t:" + p1);           //  1x3 + 1x2 + 1x1
        System.out.println("\t p2\t:" + p2);           //  3x2 + 2x1
        System.out.println("\t p3\t:" + p3);           //  −3x2 − 2x1
        System.out.println("p0 + p2\t:" + p0.plus(p2));
        System.out.println("p1 + p2\t:" + p1.plus(p2));
        System.out.println("p2 + p1\t:" + p2.plus(p1));
        System.out.println("p1 + p3\t:" + p1.plus(p3));
        System.out.println("p3 + p1\t:" + p3.plus(p1));
        System.out.println("-p1 +-p2:" + p1.minus().plus(p2.minus()));
        System.out.println("-p1 + -p3:" + p1.minus().plus(p3.minus()));
        System.out.println("p4a+p4b :" + p4a.minus().plus(p4b));
    }
}
/* Output results
	 p0	:0
	 p1	:1x³ + 1x² + 1x¹
	 p2	:3x² + 2x¹
	 p3	:-3x² - 2x¹
p0 + p2	:3x² + 2x¹
p1 + p2	:1x³ + 4x² + 3x¹
p2 + p1	:1x³ + 4x² + 3x¹
p1 + p3	:1x³ - 2x² - 1x¹
p3 + p1	:1x³ - 2x² - 1x¹
-p1 +-p2:-1x³ - 4x² - 3x¹
-p1 + -p3:-1x³ + 2x² + 1x¹
p4a+p4b :0
 */