package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.IMonthDAO;
import me.tomaszterlecki.travel.model.database.Month;
import me.tomaszterlecki.travel.services.IMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthServiceImpl implements IMonthService {
    @Autowired
    public IMonthDAO monthDAO;

    public List<Month> getAllMonthsInEnglish(){
        return monthDAO.getAllMonthsInEnglish();
    }
}
