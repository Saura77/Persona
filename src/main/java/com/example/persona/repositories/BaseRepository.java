package com.example.persona.repositories;


import com.example.persona.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean //NO SE PUEDEN CREAR INSTANCIAS
public interface BaseRepository<E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {
/*JPA repository provee los metodos de interaccion con base de datos, read, delete, save,  etc.
 A su vez, esta extiende de PagingAndSortingRepository<T, ID>, la cual implementa todas las interfaces
 necesarias para implementar la paginacion a nuestra base de datos
* */
}
