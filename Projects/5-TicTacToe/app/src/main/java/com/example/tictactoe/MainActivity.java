package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String Player1 = "X";
    static final String Player2 = "O";

    boolean player1Turn = true;

    byte[][] board = new byte[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout table = findViewById(R.id.board);
        for(int i=0; i<3; i++){
            TableRow  row =(TableRow) table.getChildAt(i);
            //burada her bir row değeine eriştik
            for(int j =0; j<3; j++){
               Button button = (Button) row.getChildAt(j);
               button.setOnClickListener(new CellListener(i,j));
            }
        }

    }

    class CellListener implements View.OnClickListener{
        int row, col;

        public CellListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {
            if(!isValidMove(row,col)){
                Toast.makeText(MainActivity.this,"Cell is already occupaite",Toast.LENGTH_LONG).show();
                return;
            }
            if(player1Turn){
                ((Button)v).setText(Player1);
                board[row][col]=1;
            }else{
                ((Button)v).setText(Player2);
                board[row][col]=2;
            }

            if(gameEnd(row,col)== -1){
                player1Turn = !player1Turn;

            }else if(gameEnd(row,col)== 0){
                Toast.makeText(MainActivity.this,"It is Draw",Toast.LENGTH_LONG).show();
                return;
            }else if(gameEnd(row,col)== 1){
                Toast.makeText(MainActivity.this,"Player 1 is Win",Toast.LENGTH_LONG).show();
                return;
            }else{
                Toast.makeText(MainActivity.this,"Player 2 is Win",Toast.LENGTH_LONG).show();
                return;
            }




        }
    }
        public boolean isValidMove(int row, int col){
        return (board[row][col] ==0 );
        }
        public int gameEnd(int row,int col){

            //check rows
                int symbol = board[row][col];
                boolean win = true;
                for(int i =0;  i<3;i++){
                    if(board[i][col] != symbol){
                        win= false;
                        break;
                    }
                }if(win){
                    return symbol;
            }
            //check Columns

            //check dioganals
        return -1;

        }


}