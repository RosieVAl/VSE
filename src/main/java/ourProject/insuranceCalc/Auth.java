package ourProject.insuranceCalc;

/*
 * Класс Авторизация выполняет проверку корректности введенного пароля, а также содержит логическую часть процесса смены режима.
 */
import javax.swing.*;
import java.util.Arrays;

public class Auth  {
    /*Массив, содержащий правильный пароль*/
    private final static char[] CORRECT_PASSWORD = {'1', '9','9','9'};
    String passTitle = "Авторизация";
    String passPromt = "Введите пароль:";
    String passIncorrect = "Введеный пароль - неверен";
    String passConfirm = "Вы действительно хотите завершить сессию в режиме Администратор?";
    /*Объявление поля пароля*/
    JPasswordField passwordField = new JPasswordField();

    /*Метод, управляющий процессом смены пароля. Принимает значение текущего режима использования. В зависимости от него происходит либо выход из режима администратора, либо вход в данный режим.*/
    public boolean logIn(boolean currentMode){
        boolean mode = currentMode;
        if (currentMode){
            int option = JOptionPane.showConfirmDialog(null, passConfirm,passTitle, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(option==JOptionPane.OK_OPTION) {
                mode = false;
            }
        }else{
            JOptionPane.showConfirmDialog(null, passwordField, passPromt, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            mode = checkPassword();
        }
        return mode;
    }

    /*Метод проверки пароля. Сравнивается длина входящего массива с массивом правильного пароля, а также сами массивы. В случае выполнения условия метод возвращает значение истина, в противном случае возвращает значение ложь и сообщение о неверном введенном пароле.*/
    private boolean checkPassword(){
        boolean isCorrect;
        char[] input = passwordField.getPassword();
        if (input.length == CORRECT_PASSWORD.length && Arrays.equals(input, CORRECT_PASSWORD)) {
            isCorrect = true;
        }else {
            isCorrect = false;
            JOptionPane.showMessageDialog(null, passIncorrect,passTitle,JOptionPane.ERROR_MESSAGE);
        }
        return isCorrect;
    }
}
