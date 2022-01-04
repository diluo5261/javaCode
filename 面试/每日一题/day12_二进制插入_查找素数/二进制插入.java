/*
题解:左移 j位,再与 n 按位 或
*/

import java.util.*;

public class BinInsert {
    public int binInsert(int n, int m, int j, int i) {
        // write code here
        m <<= j;
        return n | m;
    }
}