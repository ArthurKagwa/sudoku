package org.example;

import java.util.Scanner;

public class Main {

    static     Scanner scanner=new Scanner(System.in);

    public static void printSudoku(int sudoku[][]){
        System.out.println("\n Sudoku \n-------------\n");
        System.out.print("--------------\n");

        for(int i=0;i<9;i++){

            System.out.print("|");
            for(int j = 0; j <9; j++){
                if(sudoku[i][j]==0)
                    System.out.print("0");
                else
                    System.out.print(sudoku[i][j]);
                if((j+1)%3==0 && (j+1)!=9){
                    System.out.print("|");
                }
            }
            System.out.print("|\n");
            if((i+1)%3==0 ){
                System.out.print("-------------\n");
            }

        }
    }

    public static boolean row(int sudoku[][],int digit,int row){
        boolean bool=true;

            for(int j = 0; j <9; j++){
                if (digit!=0&&digit==sudoku[row][j]) {
                    bool = false;
                    break;
                }
            }

        return bool;
    }

    public static boolean col(int sudoku[][],int digit,int col){
        boolean bool=true;
        for(int i=0;i<9;i++){
            if (digit!=0&&digit==sudoku[i][col]) {
                bool = false;
                break;
            }
        }
        return bool;
    }

    public static  boolean box(int sudoku[][] ,int digit ,int col,int row){
        int rowLimit=0;
        int colLimit=0;
        boolean bool=true;

        while(row<9){
            if ((row+1)%3==0) {
                rowLimit = (row );
                break;
            }
            row++;

        }
        while(col<9){
            if ((col+1)%3==0) {
                colLimit = (col );
                break;
            }
            col++;

        }

            for (int i = (rowLimit - 2); i < (rowLimit + 1); i++) {
                for (int j = (colLimit - 2); j < (colLimit + 1); j++) {

                    if (digit!=0&&digit == sudoku[i][j]) {
                        bool = false;
                        break;
                    }
                }
            }

        return bool;
    }

    public static boolean  solveSudoku(int sudoku[][]){
        int[] values={1,2,3,4,5,6,7,8,9};
        
            for(int i=0;i<9;i++){
                for(int j = 0; j <9; j++){
                    if(sudoku[i][j]==0){
                        for (int value : values) {
                            if((row(sudoku,value, i)&&col(sudoku,value, j)&&box(sudoku,value, j, i))){
                                sudoku[i][j]=value;
                                System.out.println("yugf");
                                if(solveSudoku(sudoku)){
                                    return true;
                                }else{
                                    sudoku[i][j]=0;
                                }
                            }

                        }
                        return false;

                    }
                    
                    
                }
            }
         return true;   
        
    }
    public static  boolean checkCompletion(int sudoku[][]){
        boolean check=true;
        for(int i=0;i<9;i++){
            for(int j = 0; j <9; j++){
                if(sudoku[i][j]==0){
                    check=false;
                }
                
            }
        }
        return check;
    }

    public static void enterNum(int sudoku[][],int i, int j){
        System.out.println("Col "+ (j+1) +"=");
        int number=scanner.nextInt();
        if ((number>=0 && number<10)){
            if (!col(sudoku,number,j)){
                System.out.println("invalid col entry");
                enterNum(sudoku,i,j);
            } else if (!row(sudoku,number,i)) {
                System.out.println("invalid row entry");
                enterNum(sudoku,i,j);
            }else if (!box(sudoku,number,j,i)){
                System.out.println("invalid entry into 3x3 box");
                enterNum(sudoku,i,j);
            }else
            sudoku[i][j]=number;

        }
        else {
            System.out.println("enter integer between 0 and 10");
            enterNum(sudoku,i,j);
        }
    }

    public static void main(String[] args) {
         int[][] sudoku=new int[][]{
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        };
    printSudoku(sudoku);
    if(!checkCompletion(sudoku)){
        solveSudoku(sudoku);
    }
    printSudoku(sudoku);
    }
}