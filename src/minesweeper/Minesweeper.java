/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Scanner;

/**
 *
 * @author delucaa.2022
 */
public class Minesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Board b = new Board(20, 15, 9);
        while(!b.gameOver){
            b.displayBoard();
            Scanner s = new Scanner(System.in);
            String r = s.nextLine();
            String[] params = r.split(",");
            b.uncoverTile(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
            if(b.gameOver){
                b.displayBoard();
                System.out.println("BOOOOONMSMNM!!");
            }
        }
        
        
    }
    
}
