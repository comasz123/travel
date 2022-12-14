package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.database.Month;

import java.util.List;

public interface IMonthDAO {
    Month getMonthByNameEng(String name);
    Month getMonthById(int id);
    List<Month> getAllMonthsInEnglish();
}
