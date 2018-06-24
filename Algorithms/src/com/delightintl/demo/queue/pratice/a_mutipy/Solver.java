package com.delightintl.demo.queue.pratice.a_mutipy;

import edu.princeton.cs.algs4.*;

public class Solver {
    private boolean solvable;
    private GameNode finalNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        solvable = true;
        Board twin = initial.twin();
        GameNode initNode = new GameNode(null, initial, 0, false);
        GameNode twinNode = new GameNode(null, twin, 0, true);
        MinPQ<GameNode> pq = new MinPQ<>();
        pq.insert(initNode);
        pq.insert(twinNode);
        while (!pq.isEmpty()) {
            GameNode gameNode = pq.delMin();
            if (gameNode.value.isGoal()) {
                if (gameNode.isTwin) {
                    solvable = false;
                }
                finalNode = gameNode;
                break;
            }
            for (Board board : gameNode.value.neighbors()) {
                if (gameNode.prev == null || !gameNode.prev.value.equals(board)) {
                    pq.insert(new GameNode(gameNode, board, gameNode.move + 1, gameNode.isTwin));
                }
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return isSolvable() ? finalNode.move : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;
        Stack<Board> stack = new Stack<>();
        stack.push(finalNode.value);
        while (finalNode.prev != null) {
            stack.push(finalNode.prev.value);
            finalNode = finalNode.prev;
        }
        return stack;
    }

    private class GameNode implements Comparable<GameNode> {
        GameNode prev;
        Board value;
        int move;
        boolean isTwin;

        public GameNode(GameNode prev, Board value, int move, boolean isTwin) {
            this.prev = prev;
            this.value = value;
            this.move = move;
            this.isTwin = isTwin;
        }

        @Override
        public int compareTo(GameNode other) {
            int priority = move + value.manhattan();
            int otherPriority = other.move + other.value.manhattan();
            if (priority > otherPriority)
                return 1;
            else if (priority < otherPriority)
                return -1;
            else
                return 0;
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
