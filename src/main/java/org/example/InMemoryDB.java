package org.example;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDB {
    // Экономичный способ хранения данных в памяти
    private final Map<Long, Record> recordsByAccount = new HashMap<>();
    private final Map<String, Record> recordsByName = new HashMap<>();
    private final Map<Double, Record> recordsByValue = new HashMap<>();

    // Добавление новых записей
    public void addRecord(long account, String name, double value) {
        Record record = new Record(account, name, value);
        recordsByAccount.put(account, record);
        recordsByName.put(name, record);
        recordsByValue.put(value, record);
    }

    // Удаление записей
    public void deleteRecordByAccount(long account) {
        Record record = recordsByAccount.remove(account);
        if (record != null) {
            recordsByName.remove(record.name());
            recordsByValue.remove(record.value());
        }
    }

    public void deleteRecordByName(String name) {
        Record record = recordsByName.remove(name);
        if (record != null) {
            recordsByAccount.remove(record.account());
            recordsByValue.remove(record.value());
        }
    }

    public void deleteRecordByValue(double value) {
        Record record = recordsByValue.remove(value);
        if (record != null) {
            recordsByAccount.remove(record.account());
            recordsByName.remove(record.name());
        }
    }
    
    // Поиск записей по любому полю с одинаковой алгоритмической сложностью
    public Record findRecordByAccount(long account) {
        return recordsByAccount.get(account);
    }

    public Record findRecordByName(String name) {
        return recordsByName.get(name);
    }

    public Record findRecordByValue(double value) {
        return recordsByValue.get(value);
    }

    // Изменение записей
    public void updateRecord(long account, String newName, double newValue) {
        deleteRecordByAccount(account);
        addRecord(account, newName, newValue);
    }

    public static void main(String[] args) {
        InMemoryDB db = new InMemoryDB();
        db.addRecord(234678, "Иванов Иван Иванович", 2035.34);
        db.addRecord(234679, "Петров Петр Петрович", 3045.67);

        System.out.println(db.findRecordByAccount(234678));
        System.out.println(db.findRecordByName("Петров Петр Петрович"));
        System.out.println(db.findRecordByValue(2035.34));

        db.updateRecord(234678, "Сидоров Сидор Сидорович", 4050.12);
        System.out.println(db.findRecordByAccount(234678));

        db.deleteRecordByAccount(234679);
        System.out.println(db.findRecordByAccount(234679));
    }
}
