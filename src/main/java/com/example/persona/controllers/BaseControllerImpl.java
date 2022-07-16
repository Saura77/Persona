package com.example.persona.controllers;

import com.example.persona.entities.Base;
import com.example.persona.entities.Persona;
import com.example.persona.services.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {

    @Autowired
    protected S servicio;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
    /*
     * Como vemos, todos los métodos poseen un bloque try catch. En caso de poder llevar a cabo la funcionalidad,
     * devuelve estado okey junto con el body request formado por el parametro en formato JSON a devolver,
     * pero en el caso de no encontrar devuelve un error personalizado. Esto es para evitar un error extremadamente
     * largo por defecto y retornar errores personalizados. Tambien tenemos distintos tipos de error, en caso de
     * getAll, por ej, tenemos un NOT_FOUND (quiere decir que no encontró entidades del tipo solicitado), lo mismo
     * con findById. En cambio, BAD_REQUEST es en caso de una mala request body, ya sea por omitir un parametro
     * necesario, mal formato de peticion, etc.
     * */

    /*
     * Al ser de tipo ResponseEntity, debe recibir tanto el objeto que va a retornar como un http status, por eso
     * mismo planteamos de esa forma
     * */
    @GetMapping("/paged")
    public ResponseEntity<?> getAll(Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    /*
     * Para realizar el traspaso de informacion cliente-servidor, tenemos dos maneras: la primera es por el método path,
     * es decir, el usuario en la url nos provee el valor de la variable a partir de la cual vamos a crear la misma.
     * Se especifica con las {nombreVariable}. En los casos de busqueda por ID, modificación, dar de baja, etc, el usuario
     * necesitará proveer el id del objeto al cual quiere hacer referencia, y este id lo capturaremos en la path variable.
     * Por eso mismo especificamos que recibirá una path como argumento, la cual tomará de la url.
     * */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    /*
     * Ahora bien, en métodos como el put y el post, que necesitamos que el cliente nos brinde la información del
     * objeto, ya sea para guardarlo o modificarlo en la base de datos, utilizaremos la etiqueta RequestBody,
     * especificando al método que el parámetro lo recibirá del cuerpo de la petición del usuario
     * */
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody E entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

}
