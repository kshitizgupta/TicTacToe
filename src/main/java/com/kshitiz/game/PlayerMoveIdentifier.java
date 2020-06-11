package com.kshitiz.game;

public enum PlayerMoveIdentifier {
    ZERO(0),
    ONE(1),
    DEFAULT(-1);

    private int value;

    PlayerMoveIdentifier(final int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
