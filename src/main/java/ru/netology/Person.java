package ru.netology;

public class Person {
    protected final String name;
    protected final String surname;
    protected Integer age; // используем Integer для хранения null, если возраст неизвестен
    protected String address;

    // конструкторы
    public Person(String name, String surname) {
        this(name, surname, null);
    }

    public Person(String name, String surname, Integer age) {
        if (name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Имя и фамилия обязательны");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.name = name;
        this.surname = surname;
        this.age = age >= 0 ? age : null;
    }

    // методы
    public boolean hasAge() {
        return age != null;
    }

    public boolean hasAddress() {
        return address != null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public java.util.OptionalInt getAge() {
        return hasAge() ? java.util.OptionalInt.of(age) : java.util.OptionalInt.empty();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        if (hasAge()) {
            age++;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + (hasAge() ? age : "неизвестен") +
                ", address=" + (hasAddress() ? address : "неизвестен") +
                '}';
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    // метод для получения полузаполненного билдера для ребёнка
    public PersonBuilder newChildBuilder() {
        return new PersonBuilder()
                .setSurname(surname)
                .setAge(0) // возраст ребёнка по умолчанию 0
                .setAddress(address);
    }
}
