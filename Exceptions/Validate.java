package Exceptions;

public class Validate {
    public void checkInputDataLength(String[] userData) throws Exception {
        if (userData.length < 6) {
            throw new DataLengthException("ОШИБКА! Введено недостаточное количество данных! Попробуйте снова!");
        } else if (userData.length > 6) {
            throw new DataLengthException("ОШИБКА! Введено слишком много данных! Попробуйте снова!");
        }
    }

    public void checkSex(String sex) throws Exception {
        if (!sex.equalsIgnoreCase("f") && !sex.equalsIgnoreCase("m")) {
            throw new SexException("ОШИБКА! Пол указан неверно!");
        }
    }

    public void checkDate(String date) throws Exception {
        String[] splitDate = date.split("\\.");
        if (Integer.parseInt(splitDate[2]) < 1940 || Integer.parseInt(splitDate[2]) > 2023) {
            throw new DateException("ОШИБКА! Неверно указан год рождения!");
        }
        if (Integer.parseInt(splitDate[1]) < 1 || Integer.parseInt(splitDate[1]) > 12) {
            throw new DateException("ОШИБКА! Неверно указан месяц в дате!");
        } else {
            if (Integer.parseInt(splitDate[0]) < 1 || Integer.parseInt(splitDate[0]) > 31) {
                throw new DateException("ОШИБКА! Неверно указан день в дате!");
            } else {
                if (Integer.parseInt(splitDate[1]) == 4 || Integer.parseInt(splitDate[1]) == 6 ||
                        Integer.parseInt(splitDate[1]) == 9 || Integer.parseInt(splitDate[1]) == 11) {
                    if (Integer.parseInt(splitDate[0]) == 31) {
                        throw new DateException("ОШИБКА! Неверно указан день в дате!");
                    }
                } else if (Integer.parseInt(splitDate[1]) == 2 && Integer.parseInt(splitDate[2]) % 4 != 0) {
                    if (Integer.parseInt(splitDate[0]) > 28) {
                        throw new DateException("ОШИБКА! Неверно указан день в дате!");
                    }
                }
            }
        }
    }
}