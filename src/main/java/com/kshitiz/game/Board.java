package com.kshitiz.game;

import java.util.Arrays;

public class Board {
    private final int[][] matrix;
    private final int size;

    public Board(final int size) {
        this.size = size;
        matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            Arrays.fill(matrix[i], PlayerMoveIdentifier.DEFAULT.getValue());
        }
    }

    public MoveState applyMove(Move move, Player player) {
        boolean isValid = checkIfMoveValid(move);

        if (!isValid) {
            return MoveState.ILLEGAL_MOVE;
        }

        matrix[move.getX()][move.getY()] = player.getId();

        return getMoveState(move, player);
    }

    private boolean checkIfMoveValid(Move move) {
        return move.getY() < size
            && move.getY() >= 0
            && move.getX() < size
            && move.getX() >= 0
            && matrix[move.getX()][move.getY()] == PlayerMoveIdentifier.DEFAULT.getValue()
            ;
    }

    private MoveState getMoveState(Move move, Player player) {
        MoveState state = MoveState.WIN;
        // Check horizontal
        for (int i = 0; i < size; i++) {
            if (matrix[move.getX()][i] != player.getId()) {
                state = MoveState.PROGRESS;
                break;
            }
        }

        if (MoveState.WIN.equals(state))
            return state;

        // Check vertical
        state = MoveState.WIN;
        for (int i = 0; i < size; i++) {
            if (matrix[i][move.getY()] != player.getId()) {
                state = MoveState.PROGRESS;
                break;
            }
        }
        if (MoveState.WIN.equals(state))
            return state;

        // Check diagonal
        state = MoveState.WIN;
        for (int i = 0; i < size; i++) {
            if (matrix[i][i] != player.getId()) {
                state = MoveState.PROGRESS;
                break;
            }
        }

        if (MoveState.WIN.equals(state))
            return state;

        state = MoveState.WIN;
        for (int j = size - 1, i = 0; i < size; j--, i++) {
            if (matrix[i][j] != player.getId()) {
                state = MoveState.PROGRESS;
                break;
            }
        }

        return state;
    }

    private boolean isMoveDiagonal(Move move) {
        return move.getX() == move.getY();
    }

    public void printMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((matrix[i][j] == PlayerMoveIdentifier.DEFAULT.getValue() ? "*" : matrix[i][j]) + "\t");
            }
            System.out.print("\n");
        }
    }
}
