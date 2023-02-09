package Phonebook;

public class Contacts {
    /** переменные для хранения контактных данных*/
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String birthDay;
    private String homeAddress;


    /** объект для хранения двух необходимых контактных данных; имя и фамилия*/
    public  Contacts(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**хранит первое имя пользователя*/
    public String getFirstName(){
        return this.firstName;
    }

    /**хранит фамилию пользователя*/
    public String getLastName(){
        return this.lastName;
    }
    /**хранит номер телефона пользователя*/
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    /**хранит день рождения пользователя*/
    public String getBirthDay(){
        return this.birthDay;
    }
    /**хранит домашний адрес пользователя*/
    public String getHome(){
        return this.homeAddress;
    }

    /**Задает данные пользователя*/
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setBirthDay(String birthDay){
        this.birthDay = birthDay;
    }
    public void setHomeAddress(String homeAddress){
        this.homeAddress = homeAddress;
    }

    /**форматирует контакты при выводе*/

    public String toString() {
        return "\nFirst Name: " + this.firstName + "\nLast Name: " + this.lastName + "\nPhone Number: " + this.phoneNumber + "\nBirthday: " + this.birthDay + "\nAddress: " + this.homeAddress;
    }


}
