// Short program to test whether the expressions generated using the file
// sentence2.txt are legal.  They can often be very long.  You should include
// the expression as indicated below and you should be able to compile the
// program.  It might throw an exception when run, but it should compile.

import static java.lang.Math.*;

public class TestE {
    public static void main(String[] args) {
        double x = 3;
        double y = 4;
        double z = 0 + 1 + max ( 1 * min ( 92 , - 0 / - 1 ) * - 0 - 0 % min ( - pow ( x , 92 % y ) , 1 ) , min ( 1 + x + 1 , 0 ) / 1 * 0 );
    }
}
