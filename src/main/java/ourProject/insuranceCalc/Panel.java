package ourProject.insuranceCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {
    /*Создание экземпляра класса элементов графического интерфейса*/
    private GUI gui = new GUI();

    /*Переменная режима использования*/
    boolean isAdmin = false;

    /*Перменная стоимость полиса ОСАГО*/
    private double cost = 0.0;
    /*Массив хранящий данные введенные пользователем*/
    private int[] choice = new int[11];

    /*Переменные хранящие количество различных графических элементов*/
    private int panelNumber = 5;
    private int buttonNumber = 5;
    private int comboBoxNumber = 9;
    private int checkBoxNumber = 2;

    /*Создание массивов графичечких элементов*/
    private JPanel[] panels = new JPanel[panelNumber];
    private JComboBox[] comboBoxes = new JComboBox[comboBoxNumber];
    private JButton[] buttons = new JButton[buttonNumber];
    private JCheckBox[] checkBoxes = new JCheckBox[checkBoxNumber];

    /*Массив надписей режимов использования*/
    String[] modeLabels = {"Выполнен вход - Пользователь", "Выполнен вход - Администратор"};

    /*Надпись отображающая текущий режим использования*/
    private JLabel modeLabel;
    /*Надпись отображающая расчитанную стоимость полиса ОСАГО*/
    private JLabel costLabel;

    /*Конструктор класса главной панели*/
    Panel(){
        /*Массив хранящий координаты и размеры панелей*/
        int[][] panelBounds = {
                {10, 10, 430, 180},
                {10, 200, 430, 150},
                {10, 360, 430, 185},
                {0, 555, 800, 60},
                {460, 15, 230, 240}
        };

        /*Массив хранящий координаты и размеры кнопок на каждой из панелей. Первое измерение номер панели, второе измерение номер кнопки на панели, третье измерение координаты и размеры кнопки.*/
        int[][][] buttonBounds = {
                {},
                {},
                {},
                {{255,10,90,30}, {395,7,140,36}, {550,7,140,36}},
                {{15,140,200,36},{15,190,200,36}},
        };

        /*Массив со значениями выпадающих списков*/
        String[][][] comboItem = {
                {{"ТС категории A, M", "ТС категории B, BE физ. лиц, ИП", "C,CE c макс. массой 16 тонн и менее", "C,CE c макс. массой более 16 тонн", "D,DE c числом пасс. мест до 16 вкл.", "D,DE c числом пасс. мест более 16", "Тракторы"},
                        {"Россия", "В иностранном государстве"},
                        {"Уфа", "Казань", "Краснодар, Новороссийск", "Красноярск", "Пермь", "Астрахань", "Воронеж", "Нижний Новгород", "Омск", "Самара", "Екатеринбург", "Москва", "Санкт-Петербург", "Иркутск"},
                        {"До 50 включительно", "Свыше 50 до 70 включительно", "Свыше 70 до 100 включительно", "Свыше 100 до 120 включительно", "Свыше 120 до 150 включительно", "Свыше 150"}},
                {{"1-18","18-25","26-67"},{"1-6","43-54"},{"0.2"}},
                {{"3 месяца", "4 месяца", "5 месяцев", "6 месяцев", "7 месяцев", "8 месяцев", "9 месяцев", "10 месяцев и более"},
                        {"От 5 до 15 дней", "От 16 дней до 1 месяца", "2 месяца", "3 месяца", "4 месяца", "5 месяцев", "6 месяцев", "7 месяцев", "8 месяцев", "9 месяцев", "10 месяцев и более"}}
        };

        /*Массив с заголовками панелей*/
        String panelTitle[] = {"Транспортное средство", "Владелец транспортного средства", "Другие сведения", null, null};

        /*Массив с подписями к выпадающим спискам*/
        String panelLabels[][] = {
                {"Категория:", "Регистрация:", "Регион:", "Мощность:"},
                {"Стаж:","Возраст:","Бонус-малус:"},
                {"Период:","Срок:"}, {}, {}
        };

        /*Массив с надписями кнопок*/
        String[][] buttonText = {{}, {}, {},
                {"Сменить", "Очистить поля", "Рассчитать"},
                {"Настройка", "Справка"}
        };

        /*Массив строк с текстом для флажков*/
        String[] checkBoxText = {"<html>Предусмотреть ограничение для лиц допущенных к управлению - до 5 человек</html>","Зафиксированны нарушения правил страхования"};

        /*Заголовок панели стоимости*/
        String costTitle = "<html>Стоимость полиса ОСАГО составит:</html>";

        /*Массивы со значениями начальных координат и размеров элементов*/
        int[] mainPanelBounds = {0,0, Window.WIDTH, Window.HEIGHT};
        int[] comboBoxBounds = {125,30,280,28};
        int[] checkBoxBounds = {10,107,400,35};
        int[] labelBounds = {20,30,100,26};
        int[][] costLabelsBounds = {{20,10,200,50},{10,60,210,50}};
        int[] modeLabelBounds = {15,10,250,30};

        /*Переменная устанавливающая отступ между элементами(выпадающие списки, надписи)*/
        int elementIndent = 36;

        /*Инициализация главной панели*/
        Color mainPanelColor = Color.WHITE;
        gui.initPanel(this,mainPanelBounds,mainPanelColor,null);
        setBorder(null);

        /*Вызов метода создания панелей сбор данных*/
        createPanels(panelNumber,panelBounds,gui.panelColors,panelTitle,this);

        /*Вызов метода создания надписей*/
        createLabels(panelLabels, labelBounds, elementIndent);

        /*Инициализация режима использования*/
        modeLabel = gui.addLabel(modeLabels[0],modeLabelBounds);
        panels[3].add(modeLabel);

        /*Инициализация элементов панели стоимости полиса*/
        JLabel costTitleLabel = gui.addLabel(costTitle, costLabelsBounds[0]);
        costLabel = gui.addLabel("",costLabelsBounds[1]);
        gui.setBold(costLabel);
        panels[4].add(costTitleLabel);
        panels[4].add(costLabel);
        setCost(cost);

        /*Вызов метода создания выпадающих списков*/
        createComboBoxes(comboItem,comboBoxBounds,elementIndent,comboBoxes,panels);

        /*Вызов метода создания флажков*/
        createCheckBoxes(checkBoxText,checkBoxBounds,elementIndent,checkBoxes,panels[2]);

        /*Вызов метода создания кнопок. Смена цвета для кнопки расчета.*/
        createButtons(buttonBounds,buttonText,this,panels);
        gui.colorize(buttons[2]);

        /*Добавление для выпадающего списка -Регистрация- слушателя событий для динамического изменения параметров ввода.*/
        comboBoxes[1].addActionListener(foreignListner);

        /*Инициализация начального режима использования*/
        changeMode(isAdmin);
    }

    /*Метод создания панелей. Метод получает количество панелей, массив с их фоновыми цветами, массив заголовов, и контейнер, куда данные панели будут добвлены.*/
    private void createPanels(int panelNumber, int[][] panelBounds, Color[] panelColors, String[] borderTitle, JPanel targetPanel) {
        for (int i = 0; i < panelNumber; i++) {
            panels[i] = new JPanel();
            gui.initPanel(panels[i], panelBounds[i], panelColors[i], borderTitle[i]);
            targetPanel.add(panels[i]);
        }
    }

    private void createLabels(String[][] panelLabels, int[] bounds, int merge){
        for (int i = 0; i < panels.length; i++) {
            int startYPosition = bounds[1];
            for (int j = 0; j < panelLabels[i].length; j++) {
                JLabel label = gui.addLabel(panelLabels[i][j],bounds);
                panels[i].add(label);
                bounds[1]+=merge;
            }
            bounds[1]=startYPosition;
        }
    }

    private void createComboBoxes(String[][][] items, int[] bounds, int indent, JComboBox[] comboBoxes,JPanel[] panels){
        int counter=0;
        for(int i=0; i<items.length;i++){
            int startYPosition = bounds[1];
            for(int j=0; j<items[i].length;j++){
                comboBoxes[counter]=gui.addComboBox(items[i][j], bounds);
                panels[i].add(comboBoxes[counter]);
                counter++;
                bounds[1]+=indent;
            }
            bounds[1]=startYPosition;
        }
    }

    private void createCheckBoxes(String[] text, int[] bounds, int indent, JCheckBox[] checkBoxes, JPanel panel){
        for(int i=0; i<text.length; i++){
            checkBoxes[i]=gui.addCheckBox(text[i],bounds);
            panel.add(checkBoxes[i]);
            bounds[1]+=indent;
        }

    }

    private void createButtons(int[][][] buttonBounds, String[][] buttonText, ActionListener listener, JPanel[] panels){
        int counter=0;
        for(int i=0; i<buttonText.length;i++){
            for(int j=0; j<buttonText[i].length; j++){
                buttons[counter] = gui.addButton(buttonText[i][j],buttonBounds[i][j][0],buttonBounds[i][j][1],buttonBounds[i][j][2],buttonBounds[i][j][3],listener);
                panels[i].add(buttons[counter]);
                counter++;
            }
        }
    }

    private void setCost(double newCost){
        cost = Math.round(newCost);
        costLabel.setText(this.cost + " руб.");
    }

    public void getData(JComboBox[] comboBoxes, JCheckBox[] checkBoxes, int[] choice){
        int counter =0;
        for (int i=0; i<comboBoxNumber; i++){
            choice[counter] = comboBoxes[i].getSelectedIndex();
            counter++;
        }
        for(int i=0;i<checkBoxNumber;i++){
            if(checkBoxes[i].isSelected()){
                choice[counter] = 1;
            }else{
                choice[counter] = 0;
            }
            counter++;
        }
    }

    public void clearData(JComboBox[] comboBoxes, JCheckBox[] checkBoxes){
        for (int i=0; i<comboBoxNumber; i++){
            comboBoxes[i].setSelectedIndex(0);
        }
        for(int i=0;i<checkBoxNumber;i++){
           checkBoxes[i].setSelected(false);
        }
    }

    private void changeMode(boolean targetMode){
        if(targetMode){
            gui.setActivate(buttons[3]);
            modeLabel.setText(modeLabels[1]);

        }else{
            gui.setInactivate(buttons[3]);
            modeLabel.setText(modeLabels[0]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Рассчитать")){
            getData(comboBoxes,checkBoxes,choice);
        }
        if(e.getActionCommand().equals("Очистить поля")){
            clearData(comboBoxes,checkBoxes);
        }
        if(e.getActionCommand().equals("Сменить")){
           Auth auth = new Auth();
           isAdmin = auth.logIn(isAdmin);
           changeMode(isAdmin);
        }
    }

    private ActionListener foreignListner = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(comboBoxes[1].getSelectedIndex()==1){
                comboBoxes[2].setEnabled(false);
                comboBoxes[7].setEnabled(false);
                comboBoxes[8].setEnabled(true);

            }else{
                comboBoxes[2].setEnabled(true);
                comboBoxes[7].setEnabled(true);
                comboBoxes[8].setEnabled(false);
            }
        }
    };
}
