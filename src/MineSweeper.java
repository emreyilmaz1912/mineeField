import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber;
    int columnNumber;
    int size;
    int[][] map;
    int [][] board;
    boolean game = true;
    Random random = new Random();
    Scanner input = new Scanner(System.in);

    MineSweeper(int row, int column) {
        this.rowNumber = row;
        this.columnNumber = column;
        this.map = new int[row][column];
        this.board = new int[row][column];
        this.size = row*column;
    }
   public void run(){
        int row,col;
        int success = 0;
        prepare();
       System.out.println("Oyun Başladı!");
       while (game){
           print(board);
           System.out.print("Satırı giriniz :");
           row = input.nextInt();
           System.out.print("Sütunu Giriniz :");
           col = input.nextInt();
           if (row < 0 || row >= rowNumber ){
               System.out.println("Geçersiz koordinat girdiniz!");
               continue;
           }
           if (col < 0 || col >= columnNumber){
               System.out.println("Geçersiz koordinat girdiniz!");
           }
           if (map[row][col] != -1){
               checkMine(row,col);
               success++;
               if (success == (size - (size/4))){
                   System.out.println("Tebrikler... Kazandınız!");
               }
           }else {
               game = false;
               System.out.println("Oyun Bitti!");
           }
       }
   }
   public void checkMine(int rw, int cl) {
       if (map[rw][cl] == 0) {
           if ((cl < columnNumber-1) && (map[rw][cl + 1] == -1)) {
               board[rw][cl]++;
           }
           if ((rw < rowNumber -3) && (map[rw + 1][cl] == -1)) {
               board[rw][cl]++;
           }
           if ((rw > 0) && (map[rw - 1][cl] == -1)) {
               board[rw][cl]++;
           }
           if ((cl > 0) && (map[rw][cl - 1] == -1)) {
               board[rw][cl]++;
           }
           if (board[rw][cl] == 0){
               board[rw][cl] = -2;
           }
       }
   }

   public void prepare(){
        int randomRow, randomColumn;
        int count = 0;
        while (count != (size/4)){
            randomRow = random.nextInt(rowNumber);
            randomColumn = random.nextInt(columnNumber);
            if (map[randomRow][randomColumn] != -1){
                map[randomRow][randomColumn] = -1;
                count++;
            }
        }
   }
   public void print(int [][] arr) {
       for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++){
                if (arr[i][j] >= 0){
                    System.out.print(" ");
                }
                System.out.print(arr[i][j] + " ");
            }
           System.out.println();
       }
   }
}
