package com.batchflow.bootstrap.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * packageName    : com.batchflow.batch.seed.dto
 * fileName       : SeedLoadResult
 * author         : AngryPig123
 * date           : 26. 1. 2.
 * description    : 벌크 실행 결과 카운트 리턴.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 2.        AngryPig123       최초 생성
 */
@ToString
@AllArgsConstructor
public class SeedLoadResult {
    private long total;
    private long success;
    private long failed;
}
