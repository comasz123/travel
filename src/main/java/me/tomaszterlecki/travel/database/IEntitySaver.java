package me.tomaszterlecki.travel.database;

import me.tomaszterlecki.travel.model.Writeable;

public interface IEntitySaver {
    void persistEntity (Writeable entity);
    void updateEntity (Writeable entity);
    void deleteEntity (Writeable entity);
}
