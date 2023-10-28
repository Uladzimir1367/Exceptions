import java.math.BigInteger;

public class User {
    private String surname;
    private String name;
    private String patronymic;
    private String birthday;
    private BigInteger phone;
    private String sex;

    public User(String surname, String name, String patronymic, String birthday, BigInteger phone, String sex) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.phone = phone;
        this.sex = sex;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getBirthday() {
        return birthday;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return String.format("Surname: %s, Name: %s, Patronymic: %s, Birthday: %s, Phone: %d, Sex: %s",
                surname, name, patronymic, birthday, phone, sex);
    }
}