package me.tomaszterlecki.travel.services.impl;

import me.tomaszterlecki.travel.database.ICityDAO;
import me.tomaszterlecki.travel.database.IPicturesDAO;
import me.tomaszterlecki.travel.services.ISessionService;
import me.tomaszterlecki.travel.session.SessionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements ISessionService {
    @Autowired
    SessionObject sessionObject;
    @Autowired
    ICityDAO cityDAO;
    @Autowired
    IPicturesDAO picturesDAO;

    @Override
    public void setCitiesCountriesAndYears() {
        sessionObject.setYearsTravelled(picturesDAO.yearsAndMonths());
        cityDAO.setCountriesAndCitiesForUser();
    }

}
