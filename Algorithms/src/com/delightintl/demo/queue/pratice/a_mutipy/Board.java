package com.delightintl.demo.queue.pratice.a_mutipy;

import edu.princeton.cs.algs4.Stack;

public class Board {
    private int n;
    private int hamming;
    private int manhattan;
    private int[][] tiles;
    private int blankX;
    private int blankY;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        tiles = blocks;
        n = blocks.length;
        hamming = 0;
        manhattan = 0;
        for (int i = 0; i < n; i++) {
            int[] row = tiles[i];
            if (row.length != n)
                throw new IllegalArgumentException();
            for (int j = 0; j < n; j++) {
                if (row[j] == 0) {
                    blankX = i;
                    blankY = j;
                    continue;
                }
                int val = i * n + j + 1;
                if (row[j] != val) {
                    hamming++;
                    int x = (row[j] - 1) / n;
                    int y = (row[j] - 1) % n;
                    manhattan += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of blocks out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        if (tiles[0][0] == 0 || tiles[0][1] == 0)
            return getBoard(1, 0, 1, 1);
        return getBoard(0, 0, 0, 1);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board board = (Board) y;
        if (n != board.dimension()) return false;
        if (toString().equals(board.toString())) return true;
        else return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> stack = new Stack<>();
        if (blankX + 1 < n) {
            stack.push(getBoard(blankX, blankY, blankX + 1, blankY));
        }
        if (blankX - 1 >= 0) {
            stack.push(getBoard(blankX, blankY, blankX - 1, blankY));
        }
        if (blankY + 1 < n) {
            stack.push(getBoard(blankX, blankY, blankX, blankY + 1));
        }
        if (blankY - 1 >= 0) {
            stack.push(getBoard(blankX, blankY, blankX, blankY - 1));
        }
        return stack;
    }

    private Board getBoard(int x1, int y1, int x2, int y2) {
        int[][] tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == x1 && j == y1) {
                    tmp[i][j] = tiles[x2][y2];
                } else if (i == x2 && j == y2) {
                    tmp[i][j] = tiles[x1][y1];
                } else {
                    tmp[i][j] = tiles[i][j];
                }
            }
        }
        return new Board(tmp);
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
