/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ihm;

/**
 *
 * @author zzahir
 */
public class PlayerState {
    private int hookX = 0; // X position of the hook
    private int hookY = 0; // Y position of the hook

    public PlayerState(int hookX, int hookY) {
        this.hookX = hookX;
        this.hookY = hookY;
    }

    public int getHookX() {
        return hookX;
    }

    public int getHookY() {
        return hookY;
    }

    public void moveHookUp() {
        hookY -= 5; // Move up by decreasing the y-coordinate
    }

    public void moveHookDown() {
        hookY += 5; // Move down by increasing the y-coordinate
    }

    public void moveHookLeft() {
        hookX -= 5; // Move left by decreasing the x-coordinate
    }

    public void moveHookRight() {
        hookX += 5; // Move right by increasing the x-coordinate
    }

    @Override
    public String toString() {
        return "Hook Position: (" + hookX + ", " + hookY + ")";
    }
}
