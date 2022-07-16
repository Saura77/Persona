package com.example.persona.services;

import com.example.persona.entities.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/*
Esta sera la interfaz que tendrá todos los métodos para comunicarnos con las bases de datos,
que heredarán todas las clases que dan servicio a las entidades
 */
public interface BaseService<E extends Base, ID extends Serializable> {
    public List<E> findAll() throws Exception; //Trae lista de entidades en nuestra BD

    public Page<E> findAll(Pageable pageable) throws Exception;

    public E findById(ID id) throws Exception; //Trae una entidad de acuerdo a su ID

    public E save(E entity) throws Exception; //Crea entidad

    public E update(ID id, E entity) throws Exception;  //Actualiza entidad

    public boolean delete(ID id) throws Exception; //Elimina registro

}
