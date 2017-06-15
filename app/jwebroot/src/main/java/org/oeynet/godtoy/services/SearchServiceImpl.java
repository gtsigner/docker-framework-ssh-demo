package org.oeynet.godtoy.services;

import org.oeynet.godtoy.daos.interf.SearchDao;
import org.oeynet.godtoy.services.interf.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhaojunlike on 6/15/2017.
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao dao;

    @Override
    public List search() {
        return dao.search();
    }
}
