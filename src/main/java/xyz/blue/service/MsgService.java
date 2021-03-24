package xyz.blue.service;

import xyz.blue.pojo.Msg;

import java.util.concurrent.CompletableFuture;


public interface MsgService {

    CompletableFuture<Integer> deleteByPrimaryKey(Integer msg_id);

    CompletableFuture<Integer> insert(Msg record);

    CompletableFuture<Integer> insertSelective(Msg record);

    CompletableFuture<Msg> selectByPrimaryKey(Integer msg_id);

    CompletableFuture<Integer> updateByPrimaryKeySelective(Msg record);

    CompletableFuture<Integer> updateByPrimaryKey(Msg record);


}
