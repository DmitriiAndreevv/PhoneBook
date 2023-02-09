package Phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Phonebook {
    Scanner input = new Scanner(System.in);
    //arraylist для контактов
    ArrayList<Contacts> list = new ArrayList<Contacts>();

    //Добавляет контакт в arraylist
    public void addingContact(Contacts contactAdd){
        list.add(contactAdd);
    }

    //выводит каждый контакт
    public String outputContact() {
        int contactNumber = 1;
        String output = "";

        //просматривает каждый контакт и добавляет их в выходной список
        for (int i = 0; i < this.list.size(); i++){
            output += "Contact #" + contactNumber + ": " + this.list.get(i).toString() + "\n\n";
            //увеличивает контактный номер, если это необходимо
            contactNumber++;
        }
        return output;// возвращает окончательные контакты, за которыми следует формат
    }

    public int sizeContact() {
        return list.size();
    }

    //удалить контакт из телефонной книги
    public void removingContact(Contacts remove) {
        this.list.remove(remove);
    }

    //выглядит так, чтобы соответствовать контакту, который представляет пользователь
    public int findContact(String firstName, String lastName, int begin, int end) {

        if(end >= begin) {
            // Вычислить среднюю точку
            int mid = begin + (end - begin)/2;

            //хранит имя и фамилию
            int ln = 0;
            int fn = this.list.get(mid).getFirstName().compareToIgnoreCase(firstName);

            //базовый регистр, чтобы проверить, равно ли среднее значение имени
            if(fn == 0) {
                //изменяет фамилию для сравнения с введенной фамилией
                ln = this.list.get(mid).getLastName().compareToIgnoreCase(lastName);
                if(ln == 0) {
                    return mid;
                }
            }

            //смотрит, находится ли имя ближе к началу или концу
            if(fn >= 0) {
                if(ln >= 0) {
                    return findContact(firstName, lastName, begin, mid - 1);
                }
            } else {
                return findContact(firstName, lastName, mid + 1, end);
            }

        }
        return -1; //Альтернативный базовый вариант, если не найден.
    }
    //переключает значение индекса на имя
    public Contacts indexSwitch(int index){
        return this.list.get(index);
    }

    //Сортировка имени по выбору сортировка
    public void firstNameSort(){
        int smallIndex;
        for (int i = 0; i < this.list.size(); i++) {
            //найти индекс наименьшего значения
            smallIndex = i;
            for (int k = i + 1; k < this.list.size(); k++){
                if (this.list.get(smallIndex).getFirstName().compareToIgnoreCase(this.list.get(k).getFirstName()) > 0){
                    smallIndex = k;
                }
            }
            Contacts temp = this.list.get(i);
            this.list.set(i, this.list.get(smallIndex));
            this.list.set(smallIndex, temp);
        }
    }
    public void lastNameSort(){
        Contacts temp;
        for (int i = 0; i < this.list.size(); i++){
            for (int k = 1; k < this.list.size() - i; k++){
                //сравнение, чтобы увидеть, есть ли это имя в контактах
                if(this.list.get(k).getLastName().compareToIgnoreCase(this.list.get(k - 1).getLastName()) < 0){
                    //поменять местами соседние элементы, вышедшие из строя
                    temp = this.list.get(k);
                    this.list.set(k, this.list.get(k-1));
                    this.list.set(k-1, temp);
                }
            }
        }

    }
    //экспортирует файл
    public void exportFile(String name) throws FileNotFoundException{
        PrintWriter export = new PrintWriter(name);
        //просматривает список контактов
        for(int i = 0; i < list.size(); i++) {
            Contacts eachContact = list.get(i);

            export.print(eachContact.getFirstName());
            export.print(", ");
            export.print(eachContact.getLastName());
            export.print(", ");
            export.print(eachContact.getPhoneNumber());
            export.print(", ");
            export.print(eachContact.getBirthDay());
            export.print(", ");
            export.print(eachContact.getHome());
            export.print(", ");

            System.out.println("\nФайл был экспортирован.\n");
        }
        export.close();
    }

    // позволяет пользователю успешно импортировать файл
    public void importFile(String name) throws FileNotFoundException {

        //создание файлового объекта
        Scanner file = new Scanner(new File(name));
        while (file.hasNext()) {
            String line = file.nextLine();
            Scanner items = new Scanner(line);

            //последовательность символов, определяющая границу между обычным текстом
            items.useDelimiter(", ");

            String firstName = items.next();
            String lastName = items.next();
            //создается новый объект контакта, в котором сохраняются имя и фамилия
            Contacts newContacts = new Contacts(firstName, lastName);

            //считывает каждое значение, за которым следует ","
            String phoneNumber = items.next();
            String birthday = items.next();
            String homeAdd = items.next();

            newContacts.setPhoneNumber(phoneNumber);
            newContacts.setBirthDay(birthday);
            newContacts.setHomeAddress(homeAdd);

            addingContact(newContacts);
            items.close();

        }

        file.close();
    }
}
