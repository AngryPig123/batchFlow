package com.batchflow.batch.seed.loader;

/**
 * packageName    : com.batchflow.batch.seed
 * fileName       : Inserter
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@FunctionalInterface
public interface Inserter<T> {
    void insert(T row);
}
