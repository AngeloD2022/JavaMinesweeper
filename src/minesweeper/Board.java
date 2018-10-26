/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Random;

/**
 *
 * @author delucaa.2022
 */
public class Board {

    Tile[][] tiles;
    int numMines;
    int width;
    int height;

    Board(int width, int height, int numMines) {
        tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
        this.numMines = numMines;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = new Tile();
            }
        }

        Random r = new Random();
        int rx;
        int ry;

        for (int i = 0; i < numMines; i++) {
            do {
                rx = r.nextInt(width);
                ry = r.nextInt(height);
            } while (tiles[rx][ry].isMine == true);

            tiles[rx][ry].isMine = true;
        }

    }

    void displayBoard() {
        System.out.print("  ");
        for (int x = 0; x < width; x++) {
            System.out.print((x%10)+" ");
        }
        System.out.println();
        
        for(int y = 0; y < height; y++){
            System.out.print((y%10)+ " ");
            for(int x = 0; x < width; x++){
                System.out.print(". ");
            }
            System.out.println();
        }
        
    }

    boolean isInBounds(int x, int y) {
        if (x >= width || x < 0 || y >= height || y < 0) {
            return false;
        }
        return true;
    }
}
