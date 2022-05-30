import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Find {
    private String fname;
    private QItem s;
    private char[][] g;
    private final boolean[][] visited;
    private final ArrayList<QItem> Queue = new ArrayList<>();
    private boolean pfounds = false;

    public Find(char[][] grid, String fileName) {
        this.g = grid;
        this.fname = fileName;
        this.visited = new boolean[grid.length][grid.length];
    }

    public void findSource() {
        firstLoop:
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                // Finding source
                if (g[i][j] == 'S') {
                    s = new QItem(i, j);
//                    System.out.println("Start : " + (source.getColumnNumber()+1) +","+(source.getRowNumber()+1));
                    break firstLoop;
                }
            }
        }
    }

    public boolean isValid(int row, int column) {
        if (row >= 0 && column >= 0
                && row < g.length && column < g[0].length
                && g[row][column] != '0'
                && !visited[row][column]) {
            return true;
        }
        return false;
    }

    public void slide(QItem item, int x, int y, String direction) {
        int row = item.getRowNumber();
        int column = item.getColumnNumber();

        while(true) {
            row += y;
            column += x;

            if (!isValid(row, column)) {
                break;
            }

            if (g[row][column] == 'F') {
                QItem neighbourItem = new QItem(row, column);
                neighbourItem.setPrevious(item);
                neighbourItem.setMove(direction);

                Queue.add(0, neighbourItem);
                visited[row][column] = true;
                break;
            }

            int nextRow  = row + y;
            int nextColumn = column + x;

            if ((nextRow < 0 || nextColumn < 0) || (nextRow >= g.length || nextColumn >= g.length) || (g[nextRow][nextColumn] == '0')) {
                QItem neighbourItem = new QItem(row, column);
                neighbourItem.setPrevious(item);
                neighbourItem.setMove(direction);

                Queue.add(neighbourItem);
                visited[row][column] = true;
                break;
            }
        }
    }
    
    public void writeShortestPath(QItem item, long elapsedTime) throws IOException {
        ArrayList<String> path = new ArrayList<>();

        while (item.getPrevious() != null) {
            String step = "Move " + item.getMove() + " to " + "(" + (item.getColumnNumber() + 1) + ", " + (item.getRowNumber() + 1) + ")";
            path.add(step);
            item = item.getPrevious();
        }

        path.add("Start at " + "(" + (item.getColumnNumber() + 1) + ", " + (item.getRowNumber() + 1) + ")");

        FileWriter myWriter = new FileWriter("results/" + fname + "_results.txt");

        int count = 1;

        Collections.reverse(path);

        String output = "";

        for (String step : path) {
//            System.out.println(count + ". " + step);
            output += count + ". " + step + "\n";
            count += 1;
        }
//        System.out.println(count + ". Done!");
        output += count + ". Done!\n\n";

        output += "Time elapsed : " + elapsedTime;

        myWriter.write(output);

        myWriter.close();
    }

    public void shortestPath() throws IOException {
        long start = System.currentTimeMillis();
        long end = 0;
        findSource();

        Queue.add(s);
        visited[s.getRowNumber()][s.getColumnNumber()] = true;
        QItem itemVisited = null;

        while (!Queue.isEmpty()) {
            itemVisited = Queue.remove(0);
            int rowNumberVisited = itemVisited.getRowNumber();
            int columnNumberVisited = itemVisited.getColumnNumber();

            if (g[rowNumberVisited][columnNumberVisited] == 'F') {
                end = System.currentTimeMillis();
                pfounds = true;
                break;
            }
            slide(itemVisited, -1, 0, "left");
            slide(itemVisited, 1, 0, "right");
            slide(itemVisited, 0, 1, "bottom");
            slide(itemVisited, 0, -1, "up");
        }

        if (pfounds) {
            System.out.println("Path Found");
            writeShortestPath(itemVisited, end - start);
        } else {
            FileWriter myWriter = new FileWriter("results/" + fname + "_results.txt");

            myWriter.write("No Path Found");

            myWriter.close();
            System.out.println("No Path Found");
        }
    }
}
