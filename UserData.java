import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Exceptions.DataLengthException;
import Exceptions.DateException;
import Exceptions.SexException;
import Exceptions.Validate;

public class UserData {
    Validate validate = new Validate();

    public String[] getUserData() throws Exception {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("_____________________________________");
                System.out.println("Введите данные через пробел:\n" +
                        "Фамилия, Имя, Отчество, дата рождения (в формате dd.mm.yyyy), " +
                        "номер телефона (10 цифр - без символов и пробелов), " +
                        "пол (мужской - m, женский - f)");
                System.out.println("_____________________________________");
                String userData = in.nextLine();
                String[] userDataArray = userData.split(" ");
                validate.checkInputDataLength(userDataArray);
                return userDataArray;
            } catch (DataLengthException e) {
                System.out.println();
                System.out.println("_____________________________");
                System.out.println(e.getMessage());
                System.out.println("______________________________");
                System.out.println();
            }
        }
    }

    public User checkUserData(String[] userData) throws Exception {
        HashMap<String, String> userD = new HashMap<>();
        List<String> fullName = new ArrayList<>();

        System.out.println();
        for (String data : userData) {
            if (data.length() == 1) {
                try {
                    validate.checkSex(data);
                    userD.put("sex", data);
                } catch (SexException e) {
                    System.out.println("___________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________");
                }
            } else if (data.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}")) {
                try {
                    validate.checkDate(data);
                    userD.put("birthday", data);
                } catch (DateException e) {
                    System.out.println("_______________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("_________________________________________");
                }
            } else if (data.matches("[0-9]{10}")) {
                userD.put("phone", data);
            } else if ((data.matches("[a-zA-Z]{2,20}") || data.matches("[а-яА-я]{2,20}"))) {
                fullName.add(data);
            } else {
                System.out.println("________________________________________________");
                System.out.printf("ОШИБКА! Данные '%s' не распознаны!\n", data);
                System.out.println("__________________________________________________");
            }
        }

        if (fullName.size() != 3 || userD.size() != 3) {
            System.out.println();
            System.out.println("_________________________________________________");
            System.out.println("ОШИБКА! Введённые данные не удовлетворяют запросу! Повторите попытку!");
            System.out.println("____________________________________________________");
            System.out.println();
            return null;
        } else {
            String surname = fullName.get(0).substring(0, 1).toUpperCase() + fullName.get(0).substring(1);
            String name = fullName.get(1).substring(0, 1).toUpperCase() + fullName.get(1).substring(1);
            String patronymic = fullName.get(2).substring(0, 1).toUpperCase() + fullName.get(2).substring(1);
            String birthday = userD.get("birthday");
            BigInteger phone = new BigInteger(userD.get("phone"));
            String sex = userD.get("sex").toLowerCase();

            return new User(surname, name, patronymic, birthday, phone, sex);
        }
    }

    public void saveData(User user) {
        if (user != null) {
            String filename = user.getSurname() + ".txt";
            try (FileReader reader = new FileReader(filename);
                 FileWriter writer = new FileWriter(filename, true)) {
                writer.write(String.format("%s %s %s %s %d %s",
                        user.getSurname(), user.getName(), user.getPatronymic(),
                        user.getBirthday(), user.getPhone(), user.getSex()));
                writer.append("\n");
                writer.flush();
            } catch (IOException e) {
                try (FileWriter writer = new FileWriter(filename, false)) {
                    writer.write(String.format("%s %s %s %s %d %s",
                            user.getSurname(), user.getName(), user.getPatronymic(),
                            user.getBirthday(), user.getPhone(), user.getSex()));
                    writer.append("\n");
                    writer.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }
}