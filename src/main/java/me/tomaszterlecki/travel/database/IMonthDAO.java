package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.Month;

public interface IMonthDAO {
    Month getMonthByNameEng(String name);
    Month getMonthById(int id);
}
