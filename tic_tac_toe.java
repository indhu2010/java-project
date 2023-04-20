import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class tic_tac_toe implements ActionListener {
    static int h = 0, m = 0, s=0;
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;

    public tic_tac_toe() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
       
        for ( int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Roboto", Font.PLAIN, 40));
            buttons[i].addActionListener(this);
	buttons[i].setBackground(Color.white);
            panel.add(buttons[i]);
        }
       
       
        frame.getContentPane().setBackground(Color.magenta);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setVisible(true);

        JLabel timer = new JLabel("Timer", JLabel.CENTER);
        Font font1 = new Font ("Comic Sans MS", Font.BOLD, 48);
        timer.setFont(font1);
        frame.add(timer, BorderLayout.NORTH);

	Thread timer_thread = new Thread(
		//Lambda to implement run()

//() -> {}  structure of Lambda
	() -> {
		while(true)
		{
		    s++;
		    if(s == 60) { m++; s=0; }
		    if(m == 60) { h++; m =0; }
		     try{ Thread.sleep(1000);   }
		    catch(Exception exc) {}
		    


		//To update the counter
		timer.setText(""+h + " : " + m + " : " + s);
		
		}
	        }
				);	
	timer_thread.start();
    }
  

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
             Font font1 = new Font ("Comic Sans MS", Font.BOLD, 48);
	 button.setFont(font1);
	 //frame.getContentPane().setForeground(Color.blue);
	 
	

        if (xTurn) {
 	button.setBackground(Color.cyan);
	button.setForeground(Color.white);
            button.setText("X");
            
        } 
    else {
	button.setBackground(Color.orange);
	button.setForeground(Color.white);
            button.setText("O");
        }
        button.setEnabled(false);
        xTurn = !xTurn;

        checkForWinner();
    }

    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) && !buttons[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " wins!");
                resetGame();
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText()) && !buttons[i].isEnabled()) {
                JOptionPane.showMessageDialog(frame, buttons[i].getText() + " wins!");
                resetGame();
                return;
            }
        }

        // Check diagonals
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && !buttons[0].isEnabled()) {
            JOptionPane.showMessageDialog(frame, buttons[0].getText() + " wins!");
            resetGame();
            return;
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && !buttons[2].isEnabled()) {
            JOptionPane.showMessageDialog(frame, buttons[2].getText() + " wins!");
            resetGame();
            return;
        }

        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].isEnabled()) {
                tie = false;
                break;
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(frame, "Tie game!");
            resetGame();
        }
    }

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
	buttons[i].setBackground(Color.black);
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        new tic_tac_toe();
    }
}