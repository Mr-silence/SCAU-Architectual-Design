import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(double temperature);
}

interface Observable {
    void addObserver(Observer observer);

    void setChange(double temperature);

    void notifyObservers(double temperature);
}

class TemperatureGUI implements Observable {
    private double temperature;
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void setChange(double temperature) {
        this.temperature = temperature;
        notifyObservers(temperature);
    }

    @Override
    public void notifyObservers(double temperature) {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

}

class CelsiusGUI implements Observer {
    @Override
    public void update(double temperature) {
        System.out.println("Celsius GUI: " + temperature + " °C");
    }
}

class FahrenheitGUI implements Observer {
    @Override
    public void update(double temperature) {
        double fahrenheit = (temperature * 9 / 5) + 32;
        System.out.println("\u001B[34mFahrenheit GUI: " + fahrenheit + " °F\u001B[0m"); // 蓝
    }
}

class KelvinCUI implements Observer {
    @Override
    public void update(double temperature) {
        double kelvin = temperature + 273.15;
        System.out.println("\u001B[32mKelvin CUI: " + kelvin + " K\u001B[0m"); // 绿
    }
}

public class Temperature {
    public static void main(String[] args) {
        TemperatureGUI temperatureGUI = new TemperatureGUI();
        CelsiusGUI celsiusGUI = new CelsiusGUI();
        FahrenheitGUI fahrenheitGUI = new FahrenheitGUI();
        KelvinCUI kelvinCUI = new KelvinCUI();

        temperatureGUI.addObserver(celsiusGUI);
        temperatureGUI.addObserver(fahrenheitGUI);
        temperatureGUI.addObserver(kelvinCUI);

        temperatureGUI.setChange(70);
        temperatureGUI.setChange(20);
        temperatureGUI.setChange(280);
    }
}
