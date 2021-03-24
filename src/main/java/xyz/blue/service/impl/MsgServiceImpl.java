package xyz.blue.service.impl;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xyz.blue.mapper.MsgMapper;
import xyz.blue.pojo.Msg;
import xyz.blue.service.MsgService;

import java.util.concurrent.CompletableFuture;

@Service("DevicesService")//别名
@Async
public class MsgServiceImpl implements MsgService {

    private final MsgMapper msgMapper;

    public MsgServiceImpl(MsgMapper msgMapper) {
        this.msgMapper = msgMapper;
    }


    @Override
    public CompletableFuture<Integer> deleteByPrimaryKey(Integer msg_id) {
        return CompletableFuture.completedFuture(0);
    }

    @Override
    public CompletableFuture<Integer> insert(Msg record) {
        msgMapper.insert(record);
        return CompletableFuture.completedFuture(0);
    }

    @Override
    public CompletableFuture<Integer> insertSelective(Msg record) {
        return CompletableFuture.completedFuture(0);
    }

    @Override
    public CompletableFuture<Msg> selectByPrimaryKey(Integer msg_id) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Integer> updateByPrimaryKeySelective(Msg record) {
        return CompletableFuture.completedFuture(0);
    }

    @Override
    public CompletableFuture<Integer> updateByPrimaryKey(Msg record) {
        return CompletableFuture.completedFuture(0);
    }
}

