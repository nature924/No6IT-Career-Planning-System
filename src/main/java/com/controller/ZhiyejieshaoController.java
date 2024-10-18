
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 职业介绍
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhiyejieshao")
public class ZhiyejieshaoController {
    private static final Logger logger = LoggerFactory.getLogger(ZhiyejieshaoController.class);

    private static final String TABLE_NAME = "zhiyejieshao";

    @Autowired
    private ZhiyejieshaoService zhiyejieshaoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ExampaperService exampaperService;//试卷表
    @Autowired
    private ExampapertopicService exampapertopicService;//试卷选题
    @Autowired
    private ExamquestionService examquestionService;//试题表
    @Autowired
    private ExamrecordService examrecordService;//答题记录表
    @Autowired
    private ExamredetailsService examredetailsService;//答题详情表
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//公告信息
    @Autowired
    private XuexishipinService xuexishipinService;//课程学习
    @Autowired
    private XuexishipinCollectionService xuexishipinCollectionService;//课程收藏
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZhiyejieshaoCollectionService zhiyejieshaoCollectionService;//职业介绍收藏
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("zhiyejieshaoDeleteStart",1);params.put("zhiyejieshaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = zhiyejieshaoService.queryPage(params);

        //字典表数据转换
        List<ZhiyejieshaoView> list =(List<ZhiyejieshaoView>)page.getList();
        for(ZhiyejieshaoView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhiyejieshaoEntity zhiyejieshao = zhiyejieshaoService.selectById(id);
        if(zhiyejieshao !=null){
            //entity转view
            ZhiyejieshaoView view = new ZhiyejieshaoView();
            BeanUtils.copyProperties( zhiyejieshao , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZhiyejieshaoEntity zhiyejieshao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhiyejieshao:{}",this.getClass().getName(),zhiyejieshao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ZhiyejieshaoEntity> queryWrapper = new EntityWrapper<ZhiyejieshaoEntity>()
            .eq("zhiyejieshao_name", zhiyejieshao.getZhiyejieshaoName())
            .eq("zhiyejieshao_types", zhiyejieshao.getZhiyejieshaoTypes())
            .eq("zhiyejieshao_delete", zhiyejieshao.getZhiyejieshaoDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhiyejieshaoEntity zhiyejieshaoEntity = zhiyejieshaoService.selectOne(queryWrapper);
        if(zhiyejieshaoEntity==null){
            zhiyejieshao.setZhiyejieshaoDelete(1);
            zhiyejieshao.setInsertTime(new Date());
            zhiyejieshao.setCreateTime(new Date());
            zhiyejieshaoService.insert(zhiyejieshao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhiyejieshaoEntity zhiyejieshao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zhiyejieshao:{}",this.getClass().getName(),zhiyejieshao.toString());
        ZhiyejieshaoEntity oldZhiyejieshaoEntity = zhiyejieshaoService.selectById(zhiyejieshao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(zhiyejieshao.getZhiyejieshaoPhoto()) || "null".equals(zhiyejieshao.getZhiyejieshaoPhoto())){
                zhiyejieshao.setZhiyejieshaoPhoto(null);
        }

            zhiyejieshaoService.updateById(zhiyejieshao);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZhiyejieshaoEntity> oldZhiyejieshaoList =zhiyejieshaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZhiyejieshaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZhiyejieshaoEntity zhiyejieshaoEntity = new ZhiyejieshaoEntity();
            zhiyejieshaoEntity.setId(id);
            zhiyejieshaoEntity.setZhiyejieshaoDelete(2);
            list.add(zhiyejieshaoEntity);
        }
        if(list != null && list.size() >0){
            zhiyejieshaoService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ZhiyejieshaoEntity> zhiyejieshaoList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZhiyejieshaoEntity zhiyejieshaoEntity = new ZhiyejieshaoEntity();
//                            zhiyejieshaoEntity.setZhiyejieshaoName(data.get(0));                    //职业名称 要改的
//                            zhiyejieshaoEntity.setZhiyejieshaoPhoto("");//详情和图片
//                            zhiyejieshaoEntity.setZhiyejieshaoTypes(Integer.valueOf(data.get(0)));   //职业 要改的
//                            zhiyejieshaoEntity.setZhiyejieshaoContent("");//详情和图片
//                            zhiyejieshaoEntity.setZhiyejieshaoDelete(1);//逻辑删除字段
//                            zhiyejieshaoEntity.setInsertTime(date);//时间
//                            zhiyejieshaoEntity.setCreateTime(date);//时间
                            zhiyejieshaoList.add(zhiyejieshaoEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        zhiyejieshaoService.insertBatch(zhiyejieshaoList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ZhiyejieshaoView> returnZhiyejieshaoViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = zhiyejieshaoCollectionService.queryPage(params1);
        List<ZhiyejieshaoCollectionView> collectionViewsList =(List<ZhiyejieshaoCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ZhiyejieshaoCollectionView collectionView:collectionViewsList){
            Integer zhiyejieshaoTypes = collectionView.getZhiyejieshaoTypes();
            if(typeMap.containsKey(zhiyejieshaoTypes)){
                typeMap.put(zhiyejieshaoTypes,typeMap.get(zhiyejieshaoTypes)+1);
            }else{
                typeMap.put(zhiyejieshaoTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("zhiyejieshaoTypes",type);
            PageUtils pageUtils1 = zhiyejieshaoService.queryPage(params2);
            List<ZhiyejieshaoView> zhiyejieshaoViewList =(List<ZhiyejieshaoView>)pageUtils1.getList();
            returnZhiyejieshaoViewList.addAll(zhiyejieshaoViewList);
            if(returnZhiyejieshaoViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = zhiyejieshaoService.queryPage(params);
        if(returnZhiyejieshaoViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnZhiyejieshaoViewList.size();//要添加的数量
            List<ZhiyejieshaoView> zhiyejieshaoViewList =(List<ZhiyejieshaoView>)page.getList();
            for(ZhiyejieshaoView zhiyejieshaoView:zhiyejieshaoViewList){
                Boolean addFlag = true;
                for(ZhiyejieshaoView returnZhiyejieshaoView:returnZhiyejieshaoViewList){
                    if(returnZhiyejieshaoView.getId().intValue() ==zhiyejieshaoView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnZhiyejieshaoViewList.add(zhiyejieshaoView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnZhiyejieshaoViewList = returnZhiyejieshaoViewList.subList(0, limit);
        }

        for(ZhiyejieshaoView c:returnZhiyejieshaoViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnZhiyejieshaoViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = zhiyejieshaoService.queryPage(params);

        //字典表数据转换
        List<ZhiyejieshaoView> list =(List<ZhiyejieshaoView>)page.getList();
        for(ZhiyejieshaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZhiyejieshaoEntity zhiyejieshao = zhiyejieshaoService.selectById(id);
            if(zhiyejieshao !=null){


                //entity转view
                ZhiyejieshaoView view = new ZhiyejieshaoView();
                BeanUtils.copyProperties( zhiyejieshao , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZhiyejieshaoEntity zhiyejieshao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zhiyejieshao:{}",this.getClass().getName(),zhiyejieshao.toString());
        Wrapper<ZhiyejieshaoEntity> queryWrapper = new EntityWrapper<ZhiyejieshaoEntity>()
            .eq("zhiyejieshao_name", zhiyejieshao.getZhiyejieshaoName())
            .eq("zhiyejieshao_types", zhiyejieshao.getZhiyejieshaoTypes())
            .eq("zhiyejieshao_delete", zhiyejieshao.getZhiyejieshaoDelete())
//            .notIn("zhiyejieshao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhiyejieshaoEntity zhiyejieshaoEntity = zhiyejieshaoService.selectOne(queryWrapper);
        if(zhiyejieshaoEntity==null){
            zhiyejieshao.setZhiyejieshaoDelete(1);
            zhiyejieshao.setInsertTime(new Date());
            zhiyejieshao.setCreateTime(new Date());
        zhiyejieshaoService.insert(zhiyejieshao);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

