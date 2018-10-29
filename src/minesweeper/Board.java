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
            System.out.print((x % 10) + " ");
        }
        System.out.println();

        for (int y = 0; y < height; y++) {
            System.out.print((y % 10) + " ");
            for (int x = 0; x < width; x++) {
                if (tiles[x][y].uncovered && tiles[x][y].isMine) {
                    System.out.print("X ");
                    
                } else {
                    if (tiles[x][y].adjacent > 0) {
                        System.out.print(tiles[x][y].adjacent + " ");
                    }else if(tiles[x][y].adjacent == 0 && tiles[x][y].uncovered){
                        System.out.print("  ");
                    }
                    else{
                        System.out.print(". ");
                    }

                }

            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        

    }

    boolean isInBounds(int x, int y) {
        if (x >= width || x < 0 || y >= height || y < 0) {
            return false;
        }
        return true;
    }

    void uncoverTile(int x, int y) {
        Tile selected = tiles[x][y];
        if (!selected.uncovered) {
            tiles[x][y].uncovered = true;
            if (selected.isMine) {
                endGame();
            } else {
                int minesAdjacent = 0;
                for (int iy = y - 1; iy <= y + 1; iy++) {
                    for (int ix = x - 1; ix <= x + 1; ix++) {
                        if (isInBounds(ix, iy)) {
                            if (tiles[ix][iy] != tiles[x][y]) {
                                if (tiles[ix][iy].isMine) {
                                    minesAdjacent++;
                                }
                            }
                        }
                    }
                }
                tiles[x][y].adjacent = minesAdjacent;
                //count how many mines surround this tile (3x3 square)
                //int adjacent

                if (minesAdjacent == 0) {
                    for (int iy = y - 1; iy <= y + 1; iy++) {
                        for (int ix = x - 1; ix <= x + 1; ix++) {
                            if (isInBounds(ix, iy)) {
                                if (tiles[ix][iy] != tiles[x][y]) {
                                    uncoverTile(ix, iy);
                                }
                            }
                        }
                    }
                }
            }

        }
        //no mines adjacent
        //uncover surrounding
    }
    boolean gameOver = false;

    void endGame() {
        gameOver = true;
        System.out.println("Boom. you lost.");
    }
}
