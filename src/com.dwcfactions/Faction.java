package com.dwcfactions;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Faction {
    private String name;
    private UUID owner;
    private Location spawnPoint;
    private List<UUID> factionMembers = new ArrayList<>();
    // Capital that will later be used to buy claims
    private double balance;
    private List<Chunk> claimedChunks = new ArrayList<>();

    // Creates new faction
    public Faction(String name, UUID owner) {
        this.name = name;
        this.owner = owner;
        this.spawnPoint = null;
        this.factionMembers = null;
    }

    // Checks if the player is in the faction
    public boolean isPlayerInFaction(Player player)
    {
        // If the unique user id of the player is the same as a member in the faction, return true. Otherwise, return false
        for (UUID member : factionMembers) {
            if (player.getUniqueId() == member)
                return true;
        }
        return false;
    }

    // Claim a chunk for your faction
    // Will make only players of faction can modify the land of the chunk
    // Next update will include more accurate claims of land without the use of chunks
    public void claimChunk(Player p) {
        if (!claimedChunks.contains(p.getLocation().getChunk()) && p.getUniqueId() == owner) {
            claimedChunks.add(p.getLocation().getChunk());
        } else {
            p.sendMessage(ChatColor.RED + "You can't claim this land!");
        }
    }

    public void unclaimChunk(Player p) {
        if (!claimedChunks.contains(p.getLocation().getChunk())) {
            claimedChunks.remove(p.getLocation().getChunk());
        } else {
            p.sendMessage(ChatColor.RED + "You can't remove this land from your faction!");
        }
    }

    public String getName() {return name;}
    public UUID getOwner() {return owner;}
    public double getBalance() {return balance;}
    public List getMembers() {return factionMembers;}
    public void setSpawnPoint(Location spawnPoint) {this.spawnPoint = spawnPoint;}
    public Location getSpawnPoint() {return spawnPoint;}
    // Else statement will print "No spawnpoint exists!" or something like that
    public void teleportPlayer(Player player) {if(spawnPoint != null) player.teleport(this.spawnPoint);}
    // Should be boolean return types to help with exceptions
    public void addPlayer(Player player) {factionMembers.add(player.getUniqueId());}
    public void removePlayer(Player player) {factionMembers.remove(player.getUniqueId());}

}
