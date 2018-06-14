
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InputDialog extends Dialog {
    
    private String input = null;
    
    public InputDialog(Frame owner) {
        super(owner);
        initDialog();
        initComponents();
    }
    
    private void initDialog() {
        super.setModal(true);
        super.setLayout(null);
        super.setTitle("Titel ändern");
        super.setSize(300, 200);
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }
    
    private void initComponents() {
        Label text = new Label("Neuer Titel:");
        TextField inputF = new TextField();
        Button button = new Button("OK");
        
        super.add(text);
        super.add(inputF);
        super.add(button);
        
        text.setAlignment(Label.RIGHT);
        
        super.addComponentListener(new ComponentAdapter() {
            
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                // Positionierung der Eingabe
                Insets insets = getInsets();
                Dimension size = text.getPreferredSize();
                
                int x = insets.left,
                        y = (getHeight() - insets.top - insets.bottom) / 2;
                text.setBounds(x, y, size.width, size.height);
                
                x = insets.left + size.width;
                int width = getWidth() - insets.left - insets.right - size.width - 5;
                inputF.setBounds(x, y, width, size.height);

                // Positionierung des Buttons
                size = button.getPreferredSize();
                
                x = (getWidth() - insets.left - insets.right) / 2;
                y = getHeight() - insets.bottom - size.height - 10;
                
                button.setBounds(x, y, size.width, size.height);
            }
            
        });
        
        button.addActionListener((ActionEvent e) -> {
            input = inputF.getText();
            super.dispose();
        });
    }
    
    public String getInput() {
        return input;
    }
    
}
