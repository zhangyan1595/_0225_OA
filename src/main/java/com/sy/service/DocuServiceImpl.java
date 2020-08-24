package com.sy.service;

import com.github.pagehelper.PageHelper;
import com.sy.mapper.DocumentMapper;
import com.sy.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DocuServiceImpl implements DocuService {

    @Autowired
    DocumentMapper documentMapper;

    @Override
    public List<Document> select(Document document) throws Exception {
        return documentMapper.select(document);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int insert(Document document) throws Exception {
        return documentMapper.insert(document);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int update(Document document) throws Exception {
        return documentMapper.update(document);
    }


    @Override
    public List<Document> selectPage(Document document, int page, int limit) throws Exception {
        PageHelper.startPage(page,limit);
        return documentMapper.select(document);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int delete(int id) throws Exception {
        return documentMapper.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int delete1(int uid) throws Exception {
        return documentMapper.delete1(uid);
    }
}
