package Lab_4;

// Список импортов
import java.io.Serializable;

// Класс запроса из предыдущей лабораторной работы
public class Request implements Serializable
{
    // Поля класса
    private int day; // День недели
    private String place; // Место встречи
    private int start_hour; // Время время начала
    private int end_hour; // Время окончания

    // Конструктор с параметрами
    public Request(int day, String place, int from, int to)
    {
        this.day = day;
        this.place = place;
        this.start_hour = from;
        this.end_hour = to;
    }

    // Метод получения места встречи
    public String get_place()
    {
        return place;
    }

    // Метод получения дня недели встречи
    public int get_day()
    {
        return day;
    }

    // Метод получения времени начала встречи
    public int get_start_hour()
    {
        return start_hour;
    }

    // Метод получения времени завершения встречи
    public int get_end_hour()
    {
        return end_hour;
    }

    // Метод сериализации объекта класса
    @Override
    public String toString()
    {
        return this.day + " " + this.place + " " + this.start_hour + " " + this.end_hour;
    }
}
