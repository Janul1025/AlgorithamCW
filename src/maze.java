import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class maze {
    public static char[][] parser(String fileName) {
        try {
            int height = 0;
            String li;
            int rw = 0;

            FileReader fr = new FileReader("tests/" + fileName + ".txt");
            BufferedReader br = new BufferedReader(fr);
            br.mark(1);

            int l = br.readLine().length();
            br.reset();
            char[][] twoArr = new char[l][l];

            while ((li = br.readLine()) != null) {
                height++;
                char[] columns = li.toCharArray();
                twoArr[rw] = columns;
                rw++;
            }

            for (int i = 0; i < twoArr.length; i++) {
//                System.out.println(twoArr[i].getClass());
                for (int j = 0; j < twoArr[i].length; j++) {
//                    System.out.println(twoArr[i].length);
                    System.out.print(twoArr[i][j]);
                }
                System.out.println();
            }

            return twoArr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new char[0][];
    }
    public static void main(String args[]) throws IOException {
        String fileName = "test_1";

        char[][] grid = parser(fileName);
//        System.out.println(minDistance(grid));

        Find finder = new Find(grid, fileName);
        finder.shortestPath();
    }
}









