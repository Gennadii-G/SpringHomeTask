package ru.epam.spring.hometask.abstract_layout.service;

import ru.epam.spring.hometask.domain.DomainObject;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * @author Yuriy_Tkach
 *
 * @param <T>
 *            DomainObject subclass
 */
public interface AbstractDomainObjectService<T extends DomainObject> {

    /**
     * Saving new object to storage or updating existing one
     *
     * @param object
     *            Object to save
     * @return saved object with assigned id
     */
    public boolean save(@Nonnull T object);

    /**
     * Removing object from storage
     *
     * @param object
     *            Object to remove
     */
    public boolean remove(@Nonnull T object);

    public T remove(@Nonnull int id);

    /**
     * Getting object by id from storage
     *
     * @param id
     *            id of the object
     * @return Found object or <code>null</code>
     */
    public T getById(@Nonnull int id);

    /**
     * Getting all objects from storage
     *
     * @return collection of objects
     */
    public @Nonnull
    Collection<T> getAll();
}

