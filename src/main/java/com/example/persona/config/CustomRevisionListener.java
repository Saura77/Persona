package com.example.persona.config;

import org.hibernate.envers.RevisionListener;
import com.example.persona.entities.audit.Revision;

public class CustomRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
        final Revision revision = (Revision) revisionEntity;
    }
}
