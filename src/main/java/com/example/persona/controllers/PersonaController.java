package com.example.persona.controllers;

import com.example.persona.entities.Persona;
import com.example.persona.services.PersonaServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
La clase controladora recibirá el mensaje o datos en formato JSON del servidor y lo modificará para posteriormente
enviarlo a la clase de servicio. A partir de la etiqueta controller, informamos al servidor que envíe aquí las
peticiones JSON. Tendrá los métodos controladores también, los cuales tendrán un fin especifico dependiendo el
funcionamiento que espera el servidor de la base de datos. Necesitamos aplicar la uri, ya que recordemos que las url
tienen la sgte forma: http/localhost:8808/entidad, el /entidad es la uri que debemos incorporar a los controladores
para relacionarlos con los métodos que necesitamos que entren en acción dependiendo la solicitud del cliente.
Estos métodos a su vez deben tener la etiqueta responde body, que especifica que el controlador deberá devolver
una respuesta en forma response, es decir, formato JSON (con RestController nos ahorramos el trabajo)

 */
@RestController //@Controller + @ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/personas/")
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl> {
    /*
    Para cada método necesitamos Request mapping, mapear su uri correspondiente y luego el tipo de método http del
    que se trata. Para evitar esto, tenemos la etiqueta METODOMapping, la cual requiere solo la uri
    */
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String filtro) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/searchPaged")
    public ResponseEntity<?> search(@RequestParam String filtro, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }
}
