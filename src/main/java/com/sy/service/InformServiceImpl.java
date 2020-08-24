package com.sy.service;

import com.github.pagehelper.PageHelper;
import com.sy.mapper.InformMapper;
import com.sy.model.Inform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InformServiceImpl implements InformService {
  @Autowired
    InformMapper informMapper;

    @Override
    public List<Inform> select(Inform inform) throws Exception {
        return informMapper.select(inform);
    }

  @Override
  public List<Inform> selectAll() throws Exception {
    return informMapper.selectAll();
  }


  @Override
  @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
  public int update(Inform inform) throws Exception {
    return informMapper.update(inform);
  }

  @Override
  @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
  public int delete(int id) throws Exception {
    return informMapper.delete(id);
  }

  @Override
  @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
  public int insert(Inform inform) throws Exception {
    return informMapper.insert(inform);
  }

  @Override
  public int count(Inform inform) throws Exception {
    return informMapper.count(inform);
  }

  @Override
  public List<Inform> selectPage(Inform inform, int page, int limit) throws Exception {
    PageHelper.startPage(page,limit);
    return informMapper.select(inform);
  }
}
