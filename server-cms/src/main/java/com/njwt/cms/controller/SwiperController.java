package com.njwt.cms.controller;

import com.njwt.cms.entity.SwiperEntity;
import com.njwt.cms.service.SwiperServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "SwiperController", description = "首页-走马灯花")
public class SwiperController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SwiperServiceImpl swiperServiceImpl;

    @ApiOperation(value = "列表分页-走马灯花")
    @RequestMapping(value = "/selectSwiperList", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List selectAll() {

        return swiperServiceImpl.selectAll();
    }

    @ApiOperation(value = "列表分页-走马灯花")
    @RequestMapping(value = "/selectSwiperByPage", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List selectByPage(@ApiParam(value = "当前页", required = true)
                             @RequestParam(value = "page", required = true) Integer page,
                             @ApiParam(value = "页行数", required = true)
                             @RequestParam(value = "pagesize", required = true) Integer pagesize) {

        return swiperServiceImpl.selectByPage(page, pagesize);
    }

    @ApiOperation(value = "查询-走马灯花")
    @RequestMapping(value = "/getSwiper", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public SwiperEntity getSwiper(@ApiParam(value = "id", required = true)
                                      @RequestParam(value = "id", required = true) Integer id,
                                  @ApiParam(value = "名称", required = true)
                                      @RequestParam(value = "name", required = true) String namel ) {
        SwiperEntity swiperEntity = new SwiperEntity();
        swiperEntity.setId(id);
        return swiperServiceImpl.getSwiper(swiperEntity);
    }


    @ApiOperation(value = "添加-走马灯花")
    @RequestMapping(value = "/addSwiper", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String addSwiper(
                            @ApiParam(value = "名称", required = true)
                            @RequestParam(value = "name", required = true) String name,
                            @ApiParam(value = "图片URL")
                                @RequestParam(value = "imgurl") String imgurl,
                            @ApiParam(value = "URL")
                                @RequestParam(value = "url") String url,
                            @ApiParam(value = "备注")
                                @RequestParam(value = "remark") String remark ) {
        SwiperEntity swiperEntity = new SwiperEntity();
        swiperEntity.setName(name);
        swiperEntity.setImgurl(imgurl);
        swiperEntity.setUrl(url);
        swiperEntity.setRemark(remark);
        swiperServiceImpl.addSwiper(swiperEntity);
        return "";
    }


    @ApiOperation(value = "修改-走马灯花")
    @RequestMapping(value = "/updateSwiper", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public int updateSwiper(@ApiParam(value = "id", required = true)
    @RequestParam(value = "id", required = true) Integer id,
                            @ApiParam(value = "名称", required = true)
                                @RequestParam(value = "name", required = true) String name,
                            @ApiParam(value = "图片URL")
                                @RequestParam(value = "imgurl") String imgurl,
                            @ApiParam(value = "URL")
                                @RequestParam(value = "url") String url,
                            @ApiParam(value = "备注")
                                @RequestParam(value = "remark") String remark,
                            @ApiParam(value = "是否启用")
                                @RequestParam(value = "isenable") String isenable )  {
        SwiperEntity swiperEntity = new SwiperEntity();
        swiperEntity.setId(id);
        swiperEntity.setName(name);
        swiperEntity.setImgurl(imgurl);
        swiperEntity.setUrl(url);
        swiperEntity.setRemark(remark);
        swiperEntity.setIsenable(isenable);
       return swiperServiceImpl.updateSwiper(swiperEntity);

    }

    @ApiOperation(value = "删除-走马灯花")
    @RequestMapping(value = "/deleteSwiper", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public int deleteSwiper(@ApiParam(value = "id", required = true)
                         @RequestParam(value = "id", required = true) Integer id) {

      return   swiperServiceImpl.deleteSwiper(id);
    }




}
