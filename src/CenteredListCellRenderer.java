import javax.swing.*;
import java.awt.*;

public class CenteredListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        return renderer;
    }
}
