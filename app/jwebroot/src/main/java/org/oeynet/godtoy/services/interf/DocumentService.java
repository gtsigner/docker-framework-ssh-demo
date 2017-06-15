package org.oeynet.godtoy.services.interf;

import org.oeynet.godtoy.entites.DocumentEntity;

import javax.swing.text.Document;
import java.util.List;

/**
 * Created by zhaojunlike on 6/3/2017.
 */
public interface DocumentService {

    int update(DocumentEntity document);

    int incView(String uuid);

    int incHeart(String uuid);

    int add(DocumentEntity document);

    List<DocumentEntity> getDocumentEntityList();

    List<DocumentEntity> getDocumentEntityList(DocumentEntity where, int PageSize, int page);
}
