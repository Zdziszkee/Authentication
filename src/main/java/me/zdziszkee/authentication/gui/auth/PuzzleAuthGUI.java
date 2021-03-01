package me.zdziszkee.authentication.gui.auth;

import me.zdziszkee.authentication.Authentication;
import me.zdziszkee.authentication.configuration.GeneralConfiguration;
import me.zdziszkee.authentication.configuration.PuzzleAuthGUIConfiguration;

import me.zdziszkee.authentication.gui.GUI;
import me.zdziszkee.authentication.utils.Coordinates;
import me.zdziszkee.authentication.utils.GUIUtils;
import me.zdziszkee.authentication.utils.SpaceUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.concurrent.ThreadLocalRandom.current;

public class PuzzleAuthGUI implements GUI {
    private final Player player;
    private final Inventory inventory;
    private final PuzzleAuthGUIConfiguration puzzleAuthGUIConfiguration;
    private final static Map<Integer, Integer> slotMap = new HashMap<>();
    private final PlayerKicker playerKicker;
    private final GeneralConfiguration generalConfiguration;
    private final static int[] patternSlotPool = new int[]{
            18,
            19,
            20,
            21,

            27,
            28,
            29,
            30,

            36,
            37,
            38,
            39,

            45,
            46,
            47,
            48,
    };
    private final static int[] inputSlotPool = new int[]{
            23,
            24,
            25,
            26,

            32,
            33,
            34,
            35,

            41,
            42,
            43,
            44,

            50,
            51,
            52,
            53
    };

    static {
        for (int i = 0; i < 15; i++) {
            slotMap.put(patternSlotPool[i], inputSlotPool[i]);
        }
    }

    private final int[] randomFilledSlots = new int[]{
            -1, -1, -1, -1, -1, -1, -1, -1
    };
    private final int[] playerInput = new int[]{
            -1, -1, -1, -1, -1, -1, -1, -1
    };

    public PuzzleAuthGUI(Player player, PuzzleAuthGUIConfiguration puzzleAuthGUIConfiguration,PlayerKicker playerKicker,GeneralConfiguration generalConfiguration) {
        this.player = player;
        this.puzzleAuthGUIConfiguration = puzzleAuthGUIConfiguration;
        this.inventory = Bukkit.createInventory(this, 54, ChatColor.translateAlternateColorCodes('&', puzzleAuthGUIConfiguration.getInventoryName()));
        this.playerKicker = playerKicker;
        this.generalConfiguration = generalConfiguration;

    }

    private void updateInventory() {
        inventory.setContents(getInventoryContents());
    }

    @Override
    public void onClose(InventoryCloseEvent inventoryCloseEvent) {
        Bukkit.getScheduler().runTaskLater(Authentication.getInstance(), this::openInventory,1);
    }

    @Override
    public void onClick(InventoryClickEvent inventoryClickEvent) {
        int slot = inventoryClickEvent.getSlot();
        if (slot == 3) {
            resetAuth();
        }
        if (slot ==5){
            if (isPatternCorrect()) {
                player.closeInventory();
                Coordinates velocity = generalConfiguration.getSpaceVelocity();
                player.setVelocity(new Vector(velocity.getX(),velocity.getY(),velocity.getZ()));
                Bukkit.getScheduler().runTaskLater(Authentication.getInstance(), () -> {
                    SpaceUtil.connect(player,generalConfiguration.getSeverNameForTeleporting(),Authentication.getInstance());

                },20L*generalConfiguration.getSpaceTeleportDelayInSeconds());

            }else{
                playerKicker.kickPlayer(player);
            }
        }
        if(isSlotFromInputPool(slot)){

            for (int i = 0; i < playerInput.length; i++) {
                if (playerInput[i]==-1) {
                    playerInput[i]=slot;
                    break;
                }
            }
        }
        updateInventory();
    }
    private boolean isSlotFromInputPool(int slot){
        for (int i : inputSlotPool) {
            if(i==slot)return true;
        }
        return false;
    }

    private boolean isPatternCorrect() {

        for (int randomFilledSlot : randomFilledSlots) {
            if (!doesPlayerInputContainsSlot(slotMap.get(randomFilledSlot))) {
                return false;
            }
        }
        return true;
    }

    private ItemStack[] getInventoryContents() {
        Inventory temp = Bukkit.createInventory(null, 54);
        ItemStack blackGlassPane = GUIUtils.getGlassPane(15);
        ItemStack greenGlassPane = GUIUtils.getGlassPane(5);
        ItemStack redGlassPane = GUIUtils.getGlassPane(14);

        for (int i = 9; i < 18; i++) {
            temp.setItem(i, blackGlassPane);
        }
        temp.setItem(13, blackGlassPane);
        temp.setItem(22, blackGlassPane);
        temp.setItem(31, blackGlassPane);
        temp.setItem(40, blackGlassPane);
        temp.setItem(49, blackGlassPane);
        temp.setItem(3, puzzleAuthGUIConfiguration.getCancelHead().getItemStack());
        temp.setItem(4, puzzleAuthGUIConfiguration.getInformationHead().getItemStack());
        temp.setItem(5, puzzleAuthGUIConfiguration.getProceedHead().getItemStack());
        for (int i : inputSlotPool) {
               temp.setItem(i,redGlassPane);
        }
        for (int i : playerInput) {
            if(i==-1)continue;
            temp.setItem(i,greenGlassPane);
        }
        for (int randomFilledSlot : randomFilledSlots) {
            temp.setItem(randomFilledSlot, GUIUtils.getGUIItem(Material.LEAVES));
        }

        return temp.getContents();
    }

    private void generateRandomPattern() {
        for (int i = 0; i < randomFilledSlots.length; i++) {
            if (randomFilledSlots[i] == -1) {
                int randomSlot = patternSlotPool[current().nextInt(15)];
                randomFilledSlots[i] = randomSlot;
            }
        }
    }

    private void resetAuth() {
        Arrays.fill(playerInput, -1);
        generateRandomPattern();

    }

    private boolean doesPlayerInputContainsSlot(int slot){
        for (int i : playerInput) {
            if(slot==i)return  true;
        }
        return false;
    }

    public void openInventory() {
        resetAuth();
        this.inventory.setContents(getInventoryContents());
        player.openInventory(inventory);
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}
