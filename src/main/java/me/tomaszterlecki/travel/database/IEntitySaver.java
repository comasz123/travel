package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.IWriteable;

public interface IEntitySaver {
    void persistEntity (IWriteable entity);
    void updateEntity (IWriteable entity);
    void deleteEntity (IWriteable entity);

}
