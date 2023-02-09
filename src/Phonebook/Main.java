package Phonebook;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /** Определяет есть имя в телефонной книге или нет*/
   public static boolean ifContactAdd(int contactCheck){
       if(contactCheck == -1){
           System.out.println("Имя не определено. Попробуйте ввести снова! \n");
           return false;
       }return true;
   }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        Phonebook allContacts = new Phonebook();

        System.out.println("Добро пожаловать в телефонную книгу");

        boolean runProgram = true;

        while (runProgram){

        System.out.println("Введите 1: Добавить контакт");
        System.out.println("Введите 2: Отображения контактов");
        System.out.println("Введите 3: Изменить контакт");
        System.out.println("Введите 4: Удалить контакт");
        System.out.println("Введите 5: Найти контакт");
        System.out.println("Введите 6: Отсортировать контакт по имени");
        System.out.println("Введите 7: Отсортировать контакт по фамилии");
        System.out.println("Введите 8: Экспорт данных");
        System.out.println("Введите 9: Импорт данных");
        System.out.println("Введите 10: Выйти из меню");

        System.out.println("\nВведите номер: ");
        int choice = input.nextInt();
        System.out.println();

        String extraInput = input.nextLine();

        if (choice == 1) {
            System.out.println("Вы выбрали создание нового контакта.");
            /**запрашивает имя и фамилию*/
            System.out.print("Введите имя: ");
            String firstName = input.nextLine();
            System.out.print("Введите фамилию: ");
            String lastName = input.nextLine();

            Contacts contact = new Contacts(firstName, lastName);

            System.out.print("Введите номер телефона : ");
            String phoneNum = input.nextLine();
            contact.setPhoneNumber(phoneNum);

            System.out.print("Введите дату рождения (MMM-DD-YYYY): ");
            String birthdate = input.nextLine();
            contact.setBirthDay(birthdate);

            System.out.print("Введите домашний адрес: ");
            String homeAdd = input.nextLine();
            contact.setHomeAddress(homeAdd);

            allContacts.addingContact(contact);

            System.out.println("Спасибо! Контакт был добавлен!");
            System.out.println();
        }
        else if (choice == 2) {
            System.out.println(allContacts.outputContact());
        }
        else if (choice == 3) {
            /**запрашивает имя и фамилию для подтверждения личности*/
            System.out.println("Вы выбрали изменение контакта.");
            System.out.print("Введите имя: ");
            String firstN = input.nextLine();
            System.out.print("Введите фамилию: ");
            String lastN = input.nextLine();


            allContacts.firstNameSort();


            int numContact = allContacts.sizeContact() - 1;
            int index = allContacts.findContact(firstN, lastN, 0, numContact);
            boolean existingContactChecker = ifContactAdd(index);

            boolean programMenu = true;
            while (programMenu && existingContactChecker) {

                Contacts temp = allContacts.indexSwitch(index);

                System.out.println("Введите 1: Изменение имени");
                System.out.println("Введите 2: Изменение фамилии");
                System.out.println("Введите 3: Изменение номера телефона");
                System.out.println("Введите 4: Изменение дня рождения");
                System.out.println("Введите 5: Изменение домашнего адреса");
                System.out.println("Введите 6: Выход");

                System.out.print("Введите номер: ");
                int choiceCheck = input.nextInt();
                System.out.println();

                extraInput = input.nextLine();


                if (choiceCheck == 1) {
                    System.out.print("Введите новое имя: ");
                    String firstName = input.nextLine();
                    //sets the new first name in the phonebook
                    temp.setFirstName(firstName);

                    System.out.println("Спасибо! Изменение прошло успешно!\n");
                } else if (choiceCheck == 2) {
                    System.out.print("Введите новую фамилию: ");
                    String lastName = input.nextLine();
                    //sets the new last name in the phonebook
                    temp.setLastName(lastName);

                    System.out.println("Спасибо! Изменение прошло успешно!\n");
                } else if (choiceCheck == 3) {
                    System.out.print("Введите новый номер телефона (###-###-####): ");
                    String phoneNumber = input.nextLine();
                    //sets the new phone number in the phonebook
                    temp.setPhoneNumber(phoneNumber);

                    System.out.println("Спасибо! Изменение прошло успешно!\n");
                } else if (choiceCheck == 4) {
                    System.out.print("Введите день рождения (MMM-DD-YYYY): ");
                    String birthday = input.nextLine();

                    temp.setBirthDay(birthday);

                    System.out.println("Спасибо! Изменение прошло успешно!\n");
                } else if (choiceCheck == 5) {
                    System.out.print("Введите новый домашний адрес");
                    String homeAdd = input.nextLine();
                    // устанавливает новый домашний адрес в телефонной книге
                    temp.setHomeAddress(homeAdd);

                    System.out.println("Спасибо! Изменение прошло успешно!\n");
                } else if (choiceCheck == 6) {
                    System.out.println("Выход!\n");
                    programMenu = false;
                } else {
                    System.out.println("Ошибка. Попробуйте снова!");
                }
            }
        }
        else if (choice == 4) {
            /** запрашивает у пользователя имя и фамилию пользователя, которого он хочет удалить*/
            System.out.println("Вы выбрали удаление контакта");
            System.out.print("Введите имя: ");
            String firstN = input.nextLine().toUpperCase();
            System.out.print("Введите фамилию: ");
            String lastN = input.nextLine().toUpperCase();


            allContacts.firstNameSort();

            int numContact = allContacts.sizeContact() - 1;
            int index = allContacts.findContact(firstN, lastN, 0, numContact);
            boolean existingContactChecker = ifContactAdd(index);

            //спрашивает пользователя, хочет ли он удалить существующий контакт
            String response = " ";
            if(existingContactChecker) {
                System.out.print("Вы уверены что хотите удалить " + firstN + " " + lastN + "'s контакт? (Да/Нет) \n");
                response = input.nextLine().toUpperCase();

            }
            //если пользователь выберет "да", то он удалит контакт
            if(response.equals("Да") || response.equals("Нет") && existingContactChecker) {
                Contacts temp = allContacts.indexSwitch(index);
                allContacts.removingContact(temp);
                System.out.println(firstN + " " + lastN + "'s contact has been removed. Thank you!\n");
            }
            //если пользователь выберет "нет", то контакт не будет удален
            else if(response.equals("Нет")|| response.equals("Нет")) {
                System.out.println(firstN + " " + lastN + "'s контакт не удален\n");
            }
            //если параметр не равен "да" или "нет", то обнаружена ошибка
            else {
                System.out.println("Вы столкнулись с ошибкой. Пожалуйста, попробуйте снова. \n");
            }
        }
        // позволяет пользователю выполнять поиск контакта
        else if (choice == 5) {
            // запрашивает у пользователя имя и фамилию контакта, который они хотят найти
            System.out.println("Вы выбрали поиск контакта.");
            System.out.print("Введите имя: ");
            String firstN = input.nextLine();
            System.out.print("Введите фамилию: ");
            String lastN = input.nextLine();

            /** сортирует контакт по имени*/
            allContacts.firstNameSort();

            int numContact = allContacts.sizeContact() - 1;
            int index = allContacts.findContact(firstN, lastN, 0, numContact);
            boolean existingContactChecker = ifContactAdd(index);

            /** если он существует, то предоставляет подробную информацию о контакте, который они хотят видеть*/
            if (existingContactChecker) {
                System.out.println("Вот подробная информация " + firstN + " " + lastN + ":");
                Contacts temp = allContacts.indexSwitch(index);
                System.out.println(allContacts.indexSwitch(index).toString() + "\n");
            }
        }
        /** сортирует контакты по имени*/
        else if (choice  == 6) {
            allContacts.firstNameSort();
            System.out.println("Контакты были отсортированы по имени. Чтобы просмотреть их, пожалуйста, выберите номер 2.\n");
        }
        /** сортирует контакты по фамилии*/
        else if (choice == 7){
            allContacts.lastNameSort();
            System.out.println("Контакты были отсортированы по фамилии. Чтобы просмотреть их, пожалуйста, выберите номер 2.\n");
        }
        /**позволяет пользователю экспортировать данные контактов*/
        else if (choice == 8) {
            /**спрашивает пользователя, как он хочет назвать свой файл*/
            System.out.println("Введите имя для экспорта в файл (.csv)");
            String exportFile = input.nextLine();

            allContacts.exportFile(exportFile);
        }
        /** позволяет пользователям импортировать данные контактов*/
        else if (choice == 9) {
            //Просит пользователя ввести файл, который он хочет импортировать. За которым следует .csv
            System.out.println("Введите имя файла в который хотите сделать импорт (.csv)");
            String importName = input.nextLine();

            /**импортирует контакты в раздел контакты*/
            allContacts.importFile(importName);

            /**Подтверждение*/
            System.out.println(importName + " был успешно импортирован в программу!\n");

        }
        /** выходит из меню, если пользователь выбирает 10*/
        else if (choice == 10) {
            System.out.println("Хорошего дня! До свидания!.");

            runProgram = false;
        }
        /**если значение от 1 до 10 не выбрано, пользователю предлагается повторить попытку*/
        else {
            System.out.println("Введите действительный номер. Пожалуйста, попробуйте снова:");
            System.out.println();
        }
        }
    }
}