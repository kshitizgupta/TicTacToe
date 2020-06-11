package com.kshitiz.game;

import java.util.Scanner;

public class GameRunner {
    private final static String ILLEGAL_MOVE_MESSAGE = "Sorry this move is not allowed, try again";
    private final static String PLAYER_WIN_MESSAGE = "Player:[%s] wins";
    private final static String ENTER_FIRST_PLAYER_MESSAGE = "Enter player 1 name";
    private final static String ENTER_SECOND_PLAYER_MESSAGE = "Enter player 2 name";

    private Player p1;
    private Player p2;
    private Board board;
    private Scanner sc = new Scanner(System.in);

    private void init() {
        board = new Board(3);

        System.out.println(ENTER_FIRST_PLAYER_MESSAGE);
        p1 = new Player(PlayerMoveIdentifier.ONE, sc.nextLine());

        System.out.println(ENTER_SECOND_PLAYER_MESSAGE);
        p2 = new Player(PlayerMoveIdentifier.ZERO, sc.nextLine());

    }

    private void playGame() {

        boolean isPlayerOneChance = true;

        Player currentPlayer;

        while (true) {
            currentPlayer = isPlayerOneChance ? p1 : p2;
            System.out.printf(
                "\nPlayer %s Enter the coordinates(x,y)",
                currentPlayer.getName()
            );

            String input = sc.nextLine();
            Move currentMove = constructMoveFromInput(input);

            MoveState state = board.applyMove(currentMove, currentPlayer);

            switch (state) {
                case ILLEGAL_MOVE:
                    System.out.println(ILLEGAL_MOVE_MESSAGE);
                    continue;
                case WIN:
                    board.printMatrix();
                    System.out.printf("\n" + PLAYER_WIN_MESSAGE, currentPlayer.getName());
                    exitGame();
                    break;
                case PROGRESS:
                    isPlayerOneChance = !isPlayerOneChance;
                    board.printMatrix();
                    continue;
                default:
            }
        }
    }

    private void exitGame() {
        sc.close();
    }

    private Move constructMoveFromInput(final String input) {
        int x = Integer.parseInt(input.split(",")[0]);
        int y = Integer.parseInt(input.split(",")[1]);

        return new Move(x, y);
    }

    public static void run() {
        GameRunner game = new GameRunner();
        game.init();
        game.playGame();
    }

}
