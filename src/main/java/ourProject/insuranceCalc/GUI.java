package ourProject.insuranceCalc;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {
    public static final Color LIGHT_GRAY = new Color(243,243,243);
    public static final Color DARK_GRAY = new Color(210,210,210);

    private String defaultWindowPreName = "Страховой калькулятор: ";
    public static final Font REGULAR_FONT = new Font("Arial", Font.PLAIN,14);
    
    public Color panelColors[] = {Color.WHITE,Color.WHITE,Color.WHITE,LIGHT_GRAY,LIGHT_GRAY};

    public void initWindow(JFrame frame, int width, int height, String title, int actionOnClose, JPanel panel){
        frame.setSize(width, height);
        frame.setTitle(defaultWindowPreName + title);
        frame.setDefaultCloseOperation(actionOnClose);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(panel);
       
        frame.setVisible(true);

    }

    public void initPanel(JPanel panel, int bounds[], Color panelColor, String panelTitle){
        panel.setLayout(null);
        panel.setBounds(bounds[0],bounds[1],bounds[2],bounds[3]);
        panel.setBackground(panelColor);
        Border etched = BorderFactory.createEtchedBorder();
        panel.setBorder(BorderFactory.createTitledBorder(etched,panelTitle,2,2,REGULAR_FONT));
    }

    public JLabel addLabel(String text, int[] bounds){
        JLabel label = new JLabel(text);
        label.setBounds(bounds[0],bounds[1],bounds[2],bounds[3]);
        label.setFont(REGULAR_FONT);
        return label;
    }

    public JComboBox addComboBox(String[] items, int[] bounds){
        JComboBox comboBox = new JComboBox(items);
        comboBox.setFont(REGULAR_FONT);
        comboBox.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        comboBox.setBackground(Color.WHITE);
        return comboBox;
    }

    public JCheckBox addCheckBox(String text, int[] bounds){
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setFont(REGULAR_FONT);
        checkBox.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        checkBox.setBackground(Color.WHITE);
        return checkBox;
    }

    public JButton addButton(String text, int x, int y, int width, int height, ActionListener listener){
        Color DARK_BLUE = new Color(0,122,251);
        JButton button = new JButton(text);
        button.setFont(REGULAR_FONT);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(DARK_BLUE));
        button.addActionListener(listener);
        return button;
    }

    public void colorize(JButton button){
        Color DARK_BLUE = new Color(0,122,251);
        Color LIGHT_BLUE = new Color(198,222,248);
        button.setBorder(BorderFactory.createLineBorder(DARK_BLUE));
        button.setBackground(LIGHT_BLUE);
    }

    public void setBold(JLabel label){
        label.setFont(new Font("Arial", Font.BOLD,30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setInactivate(JComponent component){
        component.setEnabled(false);
        component.setBorder(BorderFactory.createLineBorder(DARK_GRAY));
        component.setBackground(Color.WHITE);
    }
    public void setActivate(JComponent component){
        Color DARK_BLUE = new Color(0,122,251);
        component.setEnabled(true);
        component.setBorder(BorderFactory.createLineBorder(DARK_BLUE));
        component.setBackground(Color.WHITE);
    }
}
