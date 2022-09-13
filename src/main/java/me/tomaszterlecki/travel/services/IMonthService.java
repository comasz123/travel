package me.tomaszterlecki.travel.services;

import me.tomaszterlecki.travel.model.database.Month;

import java.util.List;

public interface IMonthService {
    List<Month> getAllMonthsInEnglish();
}
