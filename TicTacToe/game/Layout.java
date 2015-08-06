package game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Layout extends JPanel {
    private static final long serialVersionUID = 7400937291502232740L;
    private final static int gridSize = 3;
    private static boolean playerSwitcher;
    private static ImageIcon XImageIcon;
    private static ImageIcon OImageIcon;
    private static JToggleButton[][] keypad;
    private static char[][] playerPos;

    public Layout() {
        setLayout(new GridLayout(gridSize, gridSize));
        playerPos = new char[gridSize][gridSize];
        keypad = new JToggleButton[gridSize][gridSize];
        for (int rows = 0; rows < gridSize; rows++) {
            for (int cols = 0; cols < gridSize; cols++) {
                keypad[rows][cols] = new JToggleButton();
                keypad[rows][cols].setFocusable(false);
                add(keypad[rows][cols]);
            }
        }
        playerSwitcher = false;
        OImageIcon = new ImageIcon("src\\res\\imgs\\tic-tac-toe-O.png");
        XImageIcon = new ImageIcon("src\\res\\imgs\\tic-tac-toe-X.png");
    }

    public static void play() {
        for (int rows = 0; rows < gridSize; rows++) {
            for (int cols = 0; cols < gridSize; cols++) {
                keypad[rows][cols].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        for (int rows = 0; rows < gridSize; rows++) {
                            for (int cols = 0; cols < gridSize; cols++) {
                                if (keypad[rows][cols].isSelected()) {
                                    if (!playerSwitcher) {
                                        if (keypad[rows][cols].isEnabled()) {
                                            keypad[rows][cols].setIcon(Layout.XImageIcon);
                                            keypad[rows][cols].setEnabled(false);
                                            playerSwitcher = true;
                                            playerPos[rows][cols] = 'X';
                                            if (victory() == 'x') {
                                                victoryDisplay('x');
                                            } else if (isTheWholeGridSelected() && victory() == 'd') {
                                                victoryDisplay('d');
                                            }
                                        } else {
                                            continue;
                                        }

                                    } else {
                                        if (keypad[rows][cols].isEnabled()) {
                                            keypad[rows][cols].setIcon(Layout.OImageIcon);
                                            keypad[rows][cols].setEnabled(false);
                                            playerSwitcher = false;
                                            playerPos[rows][cols] = 'O';
                                            if (victory() == 'o') {
                                                victoryDisplay('o');
                                            } else if (isTheWholeGridSelected() && victory() == 'd') {
                                                victoryDisplay('d');
                                            }
                                        } else {
                                            continue;
                                        }

                                    }
                                }
                            }
                        }
                    }
                });

            }
        }
    }

    private static char victory() {
        //X Winner
        if (playerPos[0][0] == 'X' && playerPos[0][1] == 'X' && playerPos[0][2] == 'X') {
            return 'x';
        } else if (playerPos[0][0] == 'X' && playerPos[1][0] == 'X' && playerPos[2][0] == 'X') {
            return 'x';
        } else if (playerPos[0][0] == 'X' && playerPos[1][1] == 'X' && playerPos[2][2] == 'X') {
            return 'x';
        } else if (playerPos[0][1] == 'X' && playerPos[1][1] == 'X' && playerPos[2][1] == 'X') {
            return 'x';
        } else if (playerPos[0][2] == 'X' && playerPos[1][1] == 'X' && playerPos[2][0] == 'X') {
            return 'x';
        } else if (playerPos[0][2] == 'X' && playerPos[1][2] == 'X' && playerPos[2][2] == 'X') {
            return 'x';
        } else if (playerPos[0][0] == 'X' && playerPos[1][0] == 'X' && playerPos[2][0] == 'X') {
            return 'x';
        } else if (playerPos[1][0] == 'X' && playerPos[1][1] == 'X' && playerPos[1][2] == 'X') {
            return 'x';
        } else if (playerPos[0][2] == 'X' && playerPos[1][2] == 'X' && playerPos[2][2] == 'X') {
            return 'x';
        } else if (playerPos[0][1] == 'X' && playerPos[1][1] == 'X' && playerPos[2][1] == 'X') {
            return 'x';
        } else if (playerPos[2][0] == 'X' && playerPos[2][1] == 'X' && playerPos[2][2] == 'X') {
            return 'x';
        }

        //O winner
        else if (playerPos[0][0] == 'O' && playerPos[0][1] == 'O' && playerPos[0][2] == 'O') {
            return 'o';
        } else if (playerPos[0][0] == 'O' && playerPos[1][0] == 'O' && playerPos[2][0] == 'O') {
            return 'o';
        } else if (playerPos[0][0] == 'O' && playerPos[1][1] == 'O' && playerPos[2][2] == 'O') {
            return 'o';
        } else if (playerPos[0][1] == 'O' && playerPos[1][1] == 'O' && playerPos[2][1] == 'O') {
            return 'o';
        } else if (playerPos[0][2] == 'O' && playerPos[1][1] == 'O' && playerPos[2][0] == 'O') {
            return 'o';
        } else if (playerPos[0][2] == 'O' && playerPos[1][2] == 'O' && playerPos[2][2] == 'O') {
            return 'o';
        } else if (playerPos[0][0] == 'O' && playerPos[1][0] == 'O' && playerPos[2][0] == 'O') {
            return 'o';
        } else if (playerPos[1][0] == 'O' && playerPos[1][1] == 'O' && playerPos[1][2] == 'O') {
            return 'o';
        } else if (playerPos[0][2] == 'O' && playerPos[1][2] == 'O' && playerPos[2][2] == 'O') {
            return 'o';
        } else if (playerPos[0][1] == 'O' && playerPos[1][1] == 'O' && playerPos[2][1] == 'O') {
            return 'o';
        } else if (playerPos[2][0] == 'O' && playerPos[2][1] == 'O' && playerPos[2][2] == 'O') {
            return 'o';
        }

        //Draw
        return 'd';
    }

    private static boolean isTheWholeGridSelected() {
        for (int rows = 0; rows < gridSize; rows++) {
            for (int cols = 0; cols < gridSize; cols++) {
                if (!keypad[rows][cols].isSelected()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void disableAllButtons() {
        for (int rows = 0; rows < gridSize; rows++) {
            for (int cols = 0; cols < gridSize; cols++) {
                keypad[rows][cols].setSelected(true);
                keypad[rows][cols].setEnabled(false);
            }
        }
    }

    private static void victoryDisplay(char victoryReturn) {

        if (victoryReturn == 'x') {
            disableAllButtons();
            StatusBar.timerStop();
            JOptionPane.showMessageDialog(null,
                    "               X player won\nTime played: " + Integer.toString(StatusBar.getTimer())
                            + "seconds \n" + "Finished: " + StatusBar.getCurrentTimeToString(), "GAME OVER",
                    JOptionPane.INFORMATION_MESSAGE, null);

        } else if (victoryReturn == 'o') {
            disableAllButtons();
            StatusBar.timerStop();
            JOptionPane.showMessageDialog(null,
                    "             O player won\nTime played: " + Integer.toString(StatusBar.getTimer()) + "seconds \n"
                            + "Finished: " + StatusBar.getCurrentTimeToString(), "GAME OVER",
                    JOptionPane.INFORMATION_MESSAGE, null);
        } else if (victoryReturn == 'd') {
            disableAllButtons();
            StatusBar.timerStop();
            JOptionPane.showMessageDialog(null,
                    "                   DRAW\nTime played: " + Integer.toString(StatusBar.getTimer()) + "seconds \n"
                            + "Finished: " + StatusBar.getCurrentTimeToString(), "GAME OVER",
                    JOptionPane.INFORMATION_MESSAGE, null);
        }
    }

    public static void resetButtonsState() {
        for (int rows = 0; rows < gridSize; rows++) {
            for (int cols = 0; cols < gridSize; cols++) {
                keypad[rows][cols].setSelected(false);
                keypad[rows][cols].setEnabled(true);
                keypad[rows][cols].setIcon(null);
                playerSwitcher = false;
                playerPos[rows][cols] = '-';
            }
        }
    }

    public static JToggleButton[][] getKeypad() {
        return keypad;
    }

    public static char[][] getPlayerPos() {
        return playerPos;
    }

    public static int getGridSize() {
        return gridSize;
    }
}
