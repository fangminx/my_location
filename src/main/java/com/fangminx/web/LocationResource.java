package com.fangminx.web;

import com.fangminx.service.LocationService;
import com.fangminx.service.SolrLocationService;
import com.fangminx.solr.SolrLocationDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fangmin on 2017/9/13.
 */
@RestController
@RequestMapping("/api")
public class LocationResource {

    @Autowired
    @Qualifier("locationServiceImpl")
    private LocationService locationService;

    @Autowired
    @Qualifier("solrLocationServiceImpl")
    private SolrLocationService solrLocationService;

    /**
     * 移除solr中全部数据
     */
    @RequestMapping(value = "/remove/all", method = RequestMethod.DELETE)
    public void removeAllSolrLocation(){
        solrLocationService.removeAll();
    }

    /**
     * 初始化全部城市数据
     */
    @RequestMapping(value = "/init/all", method = RequestMethod.GET)
    public void initAllSolrLocation(){
        solrLocationService.location2SolrLocationAll();
    }


    /**
     *根据条件在solr中查询城市数据
     * @param language 语言 zh_cn、en_us
     * @param country 国家 china、foreign、all
     * @param vendorType 供应商 standard、ctrip_air...
     * @param keyWord 用户输入的查询关键字
     * @param page 第几页，从0开始
     * @param size 每页多少条
     * @param type 城市类型 city、country、region、state
     * @return
     */
    @GetMapping(value = "/location/search")
    public ResponseEntity<SolrLocationDTO> searchBySolr(
            @RequestHeader(value = "language") String language,
            @RequestHeader(value = "country", required = false, defaultValue = "all") String country,
            @RequestParam(value = "vendorType", required = false,defaultValue = "standard") String vendorType,
            @RequestParam(value = "keyWord") String keyWord,
            @RequestParam(value = "page", required = false,defaultValue = "0") String page,
            @RequestParam(value = "size", required = false,defaultValue = "50") String size,
            @RequestParam(value = "type", required = false) String type) {
        SolrDocumentList res = locationService.searchBySolr(language,country,vendorType,keyWord,page,size,type);
        return new ResponseEntity(res, HttpStatus.ACCEPTED);
    }

    /**
     * 根据code查一个城市信息
     * @param language
     * @param vendorType
     * @param code
     * @return
     */
    @RequestMapping(value = "/locations/{code}", method = RequestMethod.GET)
    public ResponseEntity<SolrLocationDTO> getLocationByCode(
            @RequestHeader(value = "language") String language,
            @RequestHeader(value = "vendorType",required = false,defaultValue = "standard") String vendorType,
            @PathVariable("code") String code) {

        SolrDocumentList res = locationService.searchByCode(code,language,vendorType);
        return new ResponseEntity(res.size()==0?null:res.get(0), HttpStatus.ACCEPTED);
    }

    /**
     * 根据多个code查多个城市信息
     * @param language
     * @param codes
     * @param vendorType
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/locations/codes")
    public ResponseEntity<List<SolrLocationDTO>> getLocationByCodes(
            @RequestHeader(value = "language") String language,
            @RequestBody List<String> codes,
            @RequestParam (value = "vendorType",required = false,defaultValue = "standard") String vendorType){
        SolrDocumentList result = new SolrDocumentList();
        if(CollectionUtils.isNotEmpty(codes)) {
            codes.stream().forEach(code -> {
                if (code != null) {
                    List<SolrDocument> docs = locationService.searchByCode(code, language, vendorType);
                    if (CollectionUtils.isNotEmpty(docs)) {
                        SolrDocument doc = docs.get(0);
                        result.add(doc);
                    }
                }
            });
        }
        return new ResponseEntity(result, HttpStatus.ACCEPTED);
    }


}
