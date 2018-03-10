import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        ArrayList<Integer> ahs = new ArrayList<Integer>();
        for (int a0 = 0; a0 < t; a0++) {
            HashSet<Integer> hs = new HashSet<Integer>();

            int n = in.nextInt();
            int k = in.nextInt();

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if (i < j) {
                        int v = i & j;
                        if (v < k) {
                            hs.add(v);
                        }
                    }
                }
            }
            ArrayList<Integer> sortedList = new ArrayList<Integer>(hs);
            Collections.sort(sortedList, Collections.reverseOrder());
            ahs.add(sortedList.get(0));
        }

        for (int i = 0; i < ahs.size(); i++) {
            System.out.println(ahs.get(i));
        }
    }
}