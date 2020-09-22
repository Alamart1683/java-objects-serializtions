package Lab_4;

// Список импортов
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.Scanner;

// Основной класс второго задания
public class Lab_4_2
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
                    // Чтение и вывод файла
                    read_file();
                    break;
                case 2:
                    // Удаление файла
                    delete_file();
                    break;
                case 3:
                    // Копирование файла
                    copy_file();
                    break;
                case 4:
                    // Перемещение файла
                    move_file();
                    break;
                case 5:
                    // Создание нового файла и запись в него отобранных по длине пароля из коллекции пользователей
                    pick_requests_from_file();
                    break;
                case 6:
                    // Повторный вывод меню
                    show_menu();
                    break;
                case 7:
                    System.out.println("Выход из программы");
                    break;
                default:
                    System.out.println("Ошибка ввода");
            }
        }
        while (command != 7);
    }

    // Метод вывода меню программы
    public static void show_menu()
    {
        System.out.println("Меню программы:");
        System.out.println("1 - Чтение и вывод файла");
        System.out.println("2 - Удаление файла");
        System.out.println("3 - Копирование файла");
        System.out.println("4 - Перемещение файла");
        System.out.println("5 - Создание нового файла и запись в него отобранных по номеру дня из коллекции запросов");
        System.out.println("6 - Повторный вывод меню");
        System.out.println("7 - Выход из программы");
    }

    // Метод проверки факта наличия файла
    public static boolean file_existension(String file_name)
    {
        File file = new File(file_name);
        return file.isFile();
    }

    // Метод чтения и вывода файла
    public static void read_file()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String file_name = input.nextLine();
        // Проверка существования файла
        if (file_existension(file_name))
        {
            try
            {
                FileInputStream in = new FileInputStream(new File(file_name));
                int content;
                System.out.println("Содержимое файла:");
                // Посимвольный вывод содержимого файла
                while ((content = in.read()) != -1)
                {
                    // Преобразование в символ и вывод информации
                    System.out.print((char)content);
                }
                System.out.println();
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
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
                System.out.println("Ошибка чтения файла");
            }
        }
        else System.out.println("Ошибка: нет файла для чтения");
    }

    // Метод копирования файла
    public static void copy_file()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String file_name = input.nextLine();
        // Проверка существования файла
        if (file_existension(file_name))
        {
            try
            {
                System.out.print("Введите имя копии файла: ");
                String copy_name = input.nextLine();
                // Копирование файла
                File old_file = new File(file_name);
                File new_file = new File(copy_name);
                Files.copy(old_file.toPath(), new_file.toPath());
                System.out.println("Копия создана");
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
                System.out.println("Ошибка копирования файла");
            }
        }
        else System.out.println("Ошибка: нет файла для копирования");
    }

    // Метод удаления файла
    public static void delete_file()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String file_name = input.nextLine();
        // Проверка существования файла
        if (file_existension(file_name))
        {
            File file = new File(file_name);
            // Удаление файла
            if (file.delete()) System.out.println("Файл удалён");
            else System.out.println("Ошибка при удалении файла");
        }
        else System.out.println("Ошибка: нет файла для удаления");
    }

    // Перемещение файла
    public static void move_file()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите полное имя файла: ");
        String file_name = input.nextLine();
        // Проверка существования файла
        if (file_existension(file_name))
        {
            System.out.print("Введите новое полное имя файла: ");
            String new_name = input.nextLine();
            try
            {
                // Перемещение файла
                Path source_path = Paths.get(file_name);
                Path destination_path = Paths.get(new_name);
                Files.move(source_path, destination_path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Файл перемещён");
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
                System.out.println("Ошибка копирования файла");
            }
        }
        else System.out.println("Ошибка: нет файла для перемещения");
    }

    // Создание нового файла и запись в него отобранных по номеру дня объектов
    public static void pick_requests_from_file()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя файла с коллекцией: ");
        String file_name = input.nextLine();
        // Проверка существования файла
        if (file_existension(file_name))
        {
            LinkedList<Request> list = Lab_4_1.collection_read(file_name);
            if (list == null)
            {
                System.out.println("Ошибка при чтении файла");
            }
            else
                {
                // Отбор данных по критерию
                System.out.print("Введите номер дня, по которому будут отобраны запросы: ");
                while (!input.hasNextInt())
                {
                    System.out.println("Ошибка ввода");
                    System.out.println();
                    System.out.print("Введите номер дня ");
                    input.next();
                }
                int current_day = input.nextInt();
                int size = list.size();
                LinkedList<Request> new_list = new LinkedList<Request>();
                for (int index = 0; index < size; index++)
                {
                    if (list.get(index).get_day() == current_day)
                    {
                        new_list.add(list.get(index));
                    }
                }
                System.out.print("Введите имя нового файла с коллекцией: ");
                input.nextLine();
                file_name = input.nextLine();
                try
                {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file_name));
                    // Запись списка в файл
                    out.writeObject(new_list);
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
                    System.out.println("Ошибка записи коллекции в файл");
                }
            }
        }
        else System.out.println("Ошибка: нет файла для отбора данных");
    }
}
