package Lab_4;

// Список импортов
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

// Основной класс первого задания
public class Lab_4_1
{
    // Основной метод класса
    public static void main(String[] args)
    {
        int command;
        Scanner input = new Scanner(System.in);
        // Вывод меню программы
        show_menu();
        do
            {
            System.out.print("\nВведите число: ");
            // Проверка ввода переменной выбора
            while (!input.hasNextInt())
            {
                System.out.println("Ошибка ввода");
                System.out.println();
                System.out.print("Введите вариант: ");
                input.next();
            }
            command = input.nextInt();
            switch (command)
            {
                case 1:
                    // Запись сериализованного объекта в файл
                    one_object_write();
                    break;
                case 2:
                    // Чтение и отбражение сериализованного объекта
                    one_object_show();
                    break;
                case 3:
                    // Создание коллекции объекта и запись её в файл
                    object_collection();
                    break;
                case 4:
                    // Чтение и отображение коллекции объектов из файла
                    collection_show();
                    break;
                case 5:
                    // Запись текста в текстовый файл
                    file_write();
                    break;
                case 6:
                    // Считывание и вывод данных из текстового файла
                    file_show();
                    break;
                case 7:
                    // Повторный вывод меню
                    show_menu();
                    break;
                case 8:
                    System.out.println("Выход из программы");
                    break;
                default:
                    System.out.println("Ошибка ввода");
            }
        }
        while (command != 8);
    }

    // Метод вывода меню программы
    public static void show_menu()
    {
        System.out.println("Меню программы:");
        System.out.println("1 - Запись сериализованного объекта в файл");
        System.out.println("2 - Чтение и отбражение сериализованного объекта");
        System.out.println("3 - Создание коллекции объектов и запись её в файл");
        System.out.println("4 - Чтение и отображение коллекции объектов из файла");
        System.out.println("5 - Запись текста в текстовый файл");
        System.out.println("6 - Считывание и вывод данных из текстового файла");
        System.out.println("7 - Повторный вывод меню");
        System.out.println("8 - Выход из программы");
    }

    // Метод проверки факта наличия файла
    public static boolean file_existension(String file_name)
    {
        File file = new File(file_name);
        return file.isFile();
    }

    // Метод записи одиночного объекта в файл
    public static void one_object_write()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String file_name = input.nextLine();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер дня: ");
        while (!scanner.hasNextInt())
        {
            System.out.println("Ошибка ввода");
            System.out.println();
            System.out.print("Введите номер дня: ");
            scanner.next();
        }
        int temp_day = scanner.nextInt();
        System.out.print("Введите место встречи: ");
        String temp_place = input.nextLine();
        System.out.print("Введите время начала: ");
        while (!scanner.hasNextInt())
        {
            System.out.println("Ошибка ввода");
            System.out.println();
            System.out.print("Введите время начала ");
            scanner.next();
        }
        int temp_begin = scanner.nextInt();
        System.out.print("Введите время конца: ");
        while (!scanner.hasNextInt())
        {
            System.out.println("Ошибка ввода");
            System.out.println();
            System.out.print("Введите время конца: ");
            scanner.next();
        }
        int temp_end = scanner.nextInt();
        Request request = new Request(temp_day, temp_place, temp_begin, temp_end);
        try
        {
            // Запись объекта в файл
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file_name));
            out.writeObject(request);
            System.out.println("Объект записан в файл");
            // Закрытие потока
            try
            {
                if (out != null) out.close();
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
                System.out.println("Ошибка закрытия потока");
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
            System.out.println("Ошибка записи объекта в файл");
        }
    }

    // Метод чтения одного объекта из файла
    public static String one_object_read(String file_name)
    {
        try
        {
            // Чтение объекта из файла
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file_name));
            String string = in.readObject().toString();
            // Закрытие потока
            try
            {
                if (in != null) in.close();
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
                System.out.println("Ошибка закрытия потока");
            }
            return string;
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    // Метод отображение прочтитанного объекта
    public static void one_object_show()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String file_name = input.nextLine();
        // Проверка существования файла
        if (file_existension(file_name))
        {
            // Чтение объекта из файла
            String string = one_object_read(file_name);
            if (string == null)
            {
                System.out.println("Ошибка при чтении файла");
            }
            else
            {
                // Вывод объекта
                System.out.println("Прочитанный объект:");
                System.out.println(string);
            }
        }
        else System.out.println("Ошибка: нет файла для чтения");
    }

    // Метод создания и записи в файл коллекции объектов
    public static void object_collection()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String file_name = input.nextLine();
        Scanner scanner = new Scanner(System.in);
        LinkedList<Request> list = new LinkedList<Request>();
        System.out.print("Введите количество записей в списке: ");
        int size = scanner.nextInt();
        if (size < 0)
        {
            System.out.println("Ошибка: недопустимое количество записей в списке");
        }
        else
            {
            scanner.nextLine();
            // Заполнение списка
            for (int index = 0; index < size; index++)
            {
                System.out.print("Введите номер дня: ");
                while (!scanner.hasNextInt())
                {
                    System.out.println("Ошибка ввода");
                    System.out.println();
                    System.out.print("Введите номер дня: ");
                    scanner.next();
                }
                int temp_day = scanner.nextInt();
                System.out.print("Введите место встречи: ");
                String temp_place = input.nextLine();
                System.out.print("Введите время начала: ");
                while (!scanner.hasNextInt())
                {
                    System.out.println("Ошибка ввода");
                    System.out.println();
                    System.out.print("Введите время начала ");
                    scanner.next();
                }
                int temp_begin = scanner.nextInt();
                System.out.print("Введите время конца: ");
                while (!scanner.hasNextInt())
                {
                    System.out.println("Ошибка ввода");
                    System.out.println();
                    System.out.print("Введите время конца: ");
                    scanner.next();
                }
                int temp_end = scanner.nextInt();
                list.add(new Request(temp_day, temp_place, temp_begin, temp_end));
            }
            try
            {
                // Запись списка в файл
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file_name));
                out.writeObject(list);
                System.out.println("Коллекция записана в файл");
                // Закрытие потока
                try
                {
                    if (out != null) out.close();
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                    System.out.println("Ошибка закрытия потока");
                }

            }
            catch (IOException exception)
            {
                exception.printStackTrace();
                System.out.println("Ошибка записи коллекции элементов в файл");
            }
        }
    }

    // Метод чтения коллекции объектов из файла
    public static LinkedList<Request> collection_read(String file_name)
    {
        try
        {
            // Чтение коллекции из файла
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file_name));
            LinkedList<Request> list = (LinkedList<Request>)in.readObject();
            // Закрытие потока
            try
            {
                if (in != null) in.close();
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
                System.out.println("Ошибка закрытия потока");
            }
            return list;
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    // Метод отображения прочитанной коллекции файлов
    public static void collection_show()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String file_name = input.nextLine();
        if (file_existension(file_name))
        {
            // Чтение коллекции из файла
            LinkedList<Request> list = collection_read(file_name);
            if (list == null)
            {
                System.out.println("Ошибка при чтении файла");
            }
            else
            {
                // Вывод коллекции объектов
                System.out.println("Содержимое файла:");
                int size = list.size();
                for (int index = 0; index < size; index++)
                {
                    System.out.println(list.get(index).toString());
                }
            }
        }
        else System.out.println("Ошибка: нет файла для чтения");
    }

    // Метод записи текста в текстовый файл
    public static void file_write()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String file_name = input.nextLine();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите текст: ");
        String text = scanner.nextLine();
        try
        {
            PrintWriter writer = new PrintWriter(file_name);
            writer.write(text);
            System.out.println("Текст записан в текстовый файл");
            // Закрытие потока
            try
            {
                if (writer != null)
                    writer.close();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
                System.out.println("Ошибка закрытия потока");
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
            System.out.println("Ошибка записи текста в файл");
        }
    }

    // Метод считывания данных из текстового файла
    public static LinkedList<String> file_read(String file_name)
    {
        try
        {
            LinkedList<String> list = new LinkedList<String>();
            File file = new File(file_name);
            Scanner scanner = new Scanner(file);
            // Построчное чтение текстового файла
            while (scanner.hasNext())
            {
                list.add(scanner.nextLine());
            }
            return list;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
    }

    // Вывод содержимого файла
    public static void file_show()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String file_name = input.nextLine();
        if (file_existension(file_name))
        {
            // Чтение текстового файла
            LinkedList<String> list = file_read(file_name);
            if (list == null)
            {
                System.out.println("Ошибка при чтении файла");
            }
            else
            {
                // Вывод текста из файла
                System.out.println("Содержимое файла:");
                int size = list.size();
                for (int index = 0; index < size; index++)
                {
                    System.out.println(list.get(index));
                }
            }
        }
        else System.out.println("Ошибка: нет файла для чтения");
    }
}