package com.czxy.changgou4;

import com.czxy.changgou4.feign.SkuFeign;
import com.czxy.changgou4.repository.SkuRepository;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.ESData;
import com.czxy.changgou4.vo.SearchSku;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CGSearchServiceApplication.class)
public class TestSkuFeign {
    @Resource
    private SkuFeign skuFeign;

    @Resource
    private SkuRepository skuRepository;

    @Test
    public void testFindESData() {
        BaseResult<List<SearchSku>> esDataBaseResult = skuFeign.findESData();
        List<SearchSku> esDataList = esDataBaseResult.getData();
        for (SearchSku searchSku : esDataList) {
            System.out.println(searchSku);
        }
        skuRepository.saveAll(esDataList);
    }

}
