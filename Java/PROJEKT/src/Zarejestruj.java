import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Zarejestruj implements Runnable,Obserwowany{
    private JPanel panel1;
    private JTextArea podajLoginIHasłoTextArea;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton zarejestrujSięButton;
    Obserwator obserwator;


    public JPanel getPanel1()
    {
        return panel1;
    }
    public Zarejestruj(){
        final Main main=new Main();
        final JFrame frame2=new JFrame("ZarejestrujWiadomosc");
        zarejestrujSięButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((textField1.getText().equals("")) && (passwordField1.getText().equals(""))){
                    JOptionPane.showMessageDialog(frame2, "Niepoprawny login lub hasło,Spróbuj jeszcze raz!");
            }
                else {
                    JOptionPane.showMessageDialog(frame2, "Zarejstrowano poprawnie, możesz się zalogować.");
                    main.signin(textField1.getText(), passwordField1.getText());

                }

            }});

    }

    public void run() {

        if( obserwator != null )
            obserwator.inform();

    }

    public void subscribe( Obserwator ob ){
        this.obserwator = ob;
    }

    public void unsubscribe( Obserwator ob ){
        if(obserwator==ob)
            this.obserwator = null;
    }

    public static void main(String args[]){

        JFrame frame = new JFrame("Zarejestruj");
        frame.setContentPane(new Zarejestruj().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
