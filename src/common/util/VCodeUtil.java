package common.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Random;

public class VCodeUtil {
    public VCodeUtil() {
    }

    public static String verifyCode(int n) {
        StringBuilder strB = new StringBuilder();
        Random rand = new Random();

        for(int i = 0; i < n; ++i) {
            int r1 = rand.nextInt(3);
            int r2 = 0;
            switch(r1) {
                case 0:
                    r2 = rand.nextInt(10) + 48;
                    break;
                case 1:
                    r2 = rand.nextInt(26) + 65;
                    break;
                case 2:
                    r2 = rand.nextInt(26) + 97;
            }

            strB.append((char)r2);
        }

        return strB.toString();
    }
}
