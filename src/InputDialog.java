
import java.awt.Dialog;
import java.awt.Frame;

public class InputDialog extends Dialog {

    private String input = null;

    public InputDialog(Frame owner) {
        super(owner);
        super.setModal(true);
    }

    public String getInput() {
        return input;
    }

}
