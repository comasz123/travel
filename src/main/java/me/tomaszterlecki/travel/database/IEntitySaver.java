package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.Writeable;

import java.util.List;

public interface IEntitySaver {
    void persistEntity (Writeable entity);
    void updateEntity (Writeable entity);
    void deleteEntity (Writeable entity);

}
