package com.sy.service;

import com.sy.model.Account;
import com.sy.model.Department;
import com.sy.model.Document;

import java.util.List;

public interface DocuService {

    List<Document> select(Document document)throws Exception;
    int insert(Document document)throws Exception;
    int update(Document document)throws Exception;
    List<Document> selectPage(Document document, int page, int limit)throws Exception;
    int delete(int id)throws Exception;
    int delete1(int uid)throws Exception;
}
