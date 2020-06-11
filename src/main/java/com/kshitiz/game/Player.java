package com.kshitiz.game;

public class Player {
    private final PlayerMoveIdentifier id;
    private final String name;

    public Player(final PlayerMoveIdentifier id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id.getValue();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
