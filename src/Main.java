
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends Frame {

    private MenuBar menu;
    private Label text;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Main() {
        initFrame();
        initMenu();
        initText();

        this.setTitle("Hier könnte ihre Werbung stehen");
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        text.setText(title);
    }

    private void initMenu() {
        menu = new MenuBar();
        Menu datei = new Menu("Datei"),
                titel = new Menu("Titel"),
                menuM = new Menu("Menu");
        MenuItem beenden = new MenuItem("Beenden"),
                titelAendern = new MenuItem("Titel ändern"),
                menuWeg = new MenuItem("MenuWeg");
        datei.add(beenden);
        titel.add(titelAendern);
        menuM.add(menuWeg);

        beenden.addActionListener((ActionEvent e) -> {
            super.dispose();
        });

        titelAendern.addActionListener((ActionEvent e) -> {
            InputDialog dialog = new InputDialog(this);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            String titelNeu = dialog.getInput();
            if (titelNeu != null && !titelNeu.isEmpty()) {
                setTitle(titelNeu);
                posText();
            }
        });

        menuWeg.addActionListener((ActionEvent e) -> {
            super.setMenuBar(null);
        });

        menu.add(datei);
        menu.add(titel);
        menu.add(menuM);
        super.setMenuBar(menu);
    }

    private void initFrame() {
        super.setLayout(null);
        super.setSize(500, 500);
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        super.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                posText();
            }

        });
    }

    private void initText() {
        text = new Label();
        text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (e.getClickCount() == 2) {
                        setMenuBar(menu);
                    }
                }
            }
        });
        super.add(text);
    }

    private void posText() {
        Insets insets = super.getInsets();
        Dimension size = text.getPreferredSize();
        int x = ((super.getWidth() - insets.right - size.width) / 2),
                y = ((super.getHeight() - insets.top - insets.bottom - size.height) / 2);
        text.setBounds(insets.left + x, insets.top + y, size.width, size.height);
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
