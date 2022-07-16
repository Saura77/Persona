package com.example.persona.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Audited
public class Persona extends Base {

    /*
     * Etiqueta Column NO ES OBLIGATORIA, si el nombre del atributo coinciden tanto en la clase como en la request
     * HTTP, es indiferente colocarla o no, tipazo.
     * */
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    /*
     * Si colocamos la etiqueta @JasonIgnore a cualquiera de los atributos, al momento del controlador retornar
     * el response body al servidor, omitirá el valor de dicho atributo, esto es en el caso de información sensible
     * como puede ser una contraseña.
     * De la misma manera, existe una etiqueta llamada @JasonProperty("nombre"), la cual sirve para casos en los que el
     * nombre del atributo dentro de la http request difiere del nombre dentro del modelo de clases
     * */
    @Column(name = "dni")
    private int dni;

    /*
     * Existen columnas extras para la fecha en que creamos la columna y la fecha que la modificamos. Utilizando la
     * etiqueta @CreationTimestamp, creamos un atributo tipo Date y nos arrojará la fehca y hora de creación de la
     * tabla (al momento de usar @Column, recordar usar atributos (nullable = false, updatetable = false), para
     * evitar que se modifique nuestra hora de creacion). Del mismo modo, usando @UpdateTimestamp podemos crear un
     * atributo tipo Date que contendrá la ultima fecha de modificación de la tabla (no hacen falta atributos extra)
     * */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "persona_libro",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    private List<Libro> libros = new ArrayList<Libro>();
}
