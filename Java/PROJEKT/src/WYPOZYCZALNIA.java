import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;


public class WYPOZYCZALNIA implements Obserwator{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JPasswordField passwordField1;
    private JTextField loginTextField;
    private JButton button1;
    private JTextArea szuknijSeFilmuWpiszTextArea;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton WYLOSUJTRAILERButton;
    private JButton nieMaszKontaZarejestrujButton;


    //wnioskuje ze uzytkownikow i filmy musimy tu wprowadzic a reszta to po prostu metody do nich itd xd
    public WYPOZYCZALNIA(final JFrame frame) {
    final uzytkownik rozia=new uzytkownik("rozia","miro2010",0,0);
        final JFrame frame2=new JFrame("Login/Passwd");
        final JFrame frame3=new JFrame("Zarejestruj");

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(loginTextField.getText().equals(rozia.getLogin())&&passwordField1.getText().equals(rozia.getHaslo()))
                {
                    JOptionPane.showMessageDialog(frame2,"Logowanie pomyślne");

                }
                else
                {
                    JOptionPane.showMessageDialog(frame2,"Błędny login lub hasło");
                }
            }
        });


        nieMaszKontaZarejestrujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Zarejestruj zarejestruj=new Zarejestruj();
                frame3.setContentPane(new Zarejestruj().getPanel1());
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame3.pack();
                frame3.setVisible(true);

            }
        });
    }






    public static void main(String args[]){
        JFrame frame = new JFrame("WOLF&ROSES");
        frame.setContentPane(new WYPOZYCZALNIA(frame).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void inform() {

    }
}


