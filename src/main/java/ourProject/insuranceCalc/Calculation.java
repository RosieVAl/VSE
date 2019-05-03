package ourProject.insuranceCalc;


import javax.swing.*;
import java.util.Arrays;

public class Calculation {
    private boolean isForeigner = false;
    public int rateNumber = 7;

    /* Массивы данных с коэффициентами для вычисления стоимости полиса. */
    private String[] error = {"Ошибка ввода", "Введенные данные некорректны, повторите ввод!"};
    private static double[] baseRate = {1050, 3844, 3930, 5918, 3145, 3930, 1397};
    private double[] territorialRate = {1.8, 2.0, 1.8, 1.8, 2.0, 1.4, 1.5, 1.8, 1.6, 1.6, 1.8, 2.0, 1.8, 1.7};
    private double[] capacityRate = {0.6, 1.0, 1.1, 1.2, 1.4,1.6};
    private double[] malusRate = {0.5,0.55,0.6,0.65,0.7,0.75,0.8,0.85,0.9,0.95,1.0,1.4,1.55,2.3,2.45};
    private double[] periodRate = {0.5, 0.6, 0.65, 0.7, 0.8, 0.9, 0.95, 1.0};
    private double[] termRate = {0.2,0.3,0.4,0.5, 0.6, 0.65, 0.7, 0.8, 0.9, 0.95, 1.0};
    private double[][] expRate ={{1.87,1.87,1.87,1.66,1.66,-1,-1,-1},
            {1.77,1.77,1.77,1.04,1.04,1.04,-1,-1},
            {1.77,1.69,1.63,1.04,1.04,1.04,1.01,-1},
            {1.63,1.63,1.63,1.04,1.04,1.01,0.96,0.96},
            {1.63,1.63,1.63,0.99,0.96,0.96,0.96,0.96},
            {1.63,1.63,1.63,0.99,0.96,0.96,0.96,0.96},
            {1.63,1.63,1.63,0.96,0.96,0.96,0.96,0.96},
            {1.60,1.60,1.60,0.93,0.93,0.93,0.93,0.93}
    };

    public double[] getBaseRate(){
       return baseRate;
    }

    public void setBaseRate(double[] newBaseRate){
        baseRate = Arrays.copyOf(newBaseRate,newBaseRate.length);
    }

    /* Метод, вычисляющий стоимость. Принимает массив данных, введенных пользователем в форму. Вызывает методы вычисления всех коэффициентов, находит их произведение.*/
    public double calculateCost(int[] formData) {
        double base =calcBaseRate(formData[0]);
        isForeigner = foreignerFlag(formData[1], isForeigner);
        double territorial = calcTerritorialRate(formData[2], isForeigner);
        double capacity = calcCapacityRate(formData[3]);
        double experience = calcExpRate(formData[4],formData[5]);
        double malus = calcMalusRate(formData[6]);
        double period = calcPeriodRate(formData[7], isForeigner);
        double term = calcTermRate(formData[8], isForeigner);
        double restriction = calcRestrictionRate(formData[9]);
        double violation = calcViolationRate(formData[10]);
        double cost = base * territorial * capacity * experience * malus * restriction * violation * period * term;
        for (int i=0; i<formData.length; i++)
        	System.out.println(formData[i]);
        return cost;
    }

    /*Метод вычисляет базовый тариф путем сопоставления полученного значения коэффициенту в соответствующем массиве.*/
    private double calcBaseRate(int value){
        double rate;
        rate = baseRate[value];
        return rate;
    }

    /*Метод устанавливает флаг "Иностранец". Данный флаг влияет на ход вычисления и коэффициенты.*/
    private boolean foreignerFlag(int value, boolean isForeigner){
        if(value==1) isForeigner=true;
        return isForeigner;
    }

    /*Метод вычисляет территориальный коэффициент путем сопоставления полученного значения коэффициенту в соответствующем массиве.*/
    private double calcTerritorialRate(int value, boolean isForeigner){
        double coefficient;
        coefficient = territorialRate[value];
        if(isForeigner) coefficient = 1.7;
        return coefficient;
    }

    /*Метод вычисляет коэффициент, соответствующий мощности ТС путем сопоставления полученного значения коэффициенту в соответствующем массиве.*/
    private double calcCapacityRate(int value){
        double coefficient;
        coefficient = capacityRate[value];
        return coefficient;
    }

    public double calcExpRate(int exp, int age){
        double coefficient;
        if(expRate[age][exp]<=0){
            coefficient=0;
            JOptionPane.showMessageDialog(null, error[1],error[0],JOptionPane.ERROR_MESSAGE);
        }else{
            coefficient=expRate[age][exp];
        }
        System.out.println(coefficient);
        return coefficient;
    }

    /*Метод вычисляет коэффициент за ограничение количества лиц, допущенных к управлению ТС.*/
    private double calcRestrictionRate(int value){
        double coefficient = 1.87;
        if(value==1) coefficient = 1.0;
        return coefficient;
    }

    /*Метод вычисляет коэффициент за наличие правонарушений.*/
    private double calcViolationRate(int value){
        double coefficient = 1.0;
        if(value==1) coefficient = 1.5;
        return coefficient;
    }

    /*Метод вычисляет значение скидки за безаварийную езду путем сопоставления полученного значения коэффициенту в соответствующем массиве.*/
    private double calcMalusRate(int value){
        double coefficient;
        coefficient = malusRate[value];
        return coefficient;
    }

    /*Метод вычисляет коэффициент в зависимости от периода использования ТС путем сопоставления полученного значения коэффициенту в соответствующем массиве.*/
    private double calcPeriodRate(int value, boolean isForeigner){
        double coefficient;
        coefficient = periodRate[value];
        if(isForeigner) coefficient = 1.0;
        return coefficient;
    }

    /*Метод вычисляет коэффициент в зависимости от срока страхования путем сопоставления полученного значения коэффициенту в соответствующем массиве.*/
    private double calcTermRate(int value, boolean isForeigner){
        double coefficient;
        coefficient = termRate[value];
        if(!isForeigner) coefficient = 1.0;
        return coefficient;
    }
}
