package com.javaGeneric.players;
import java.util.ArrayList;

public class Team<T extends Player> {
    private final String name;
    int played;
    int won = 0;
    int loss = 0;
    int tied = 0;

    private final ArrayList<Player> memeber = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addPlayer(Player player) {
        if (memeber.contains(player)) {
            System.out.println(player.getName() + "already add to the team!");
            return false;
        }
        memeber.add(player);
        System.out.println(player.getName() + " added to the team!");
        return true;
    }

    public int numPlayer() {
        return this.memeber.size();
    }

    public void matchResult(Team<T> opponent, int ourScore, int theirScore) {
        final int i = ourScore > theirScore ? won++ : ourScore < theirScore ? loss++ : tied++;
        played++;
    }

    public int ranking() {
        return won * 2 + tied;
    }
}
