
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
 * 课程学习
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xuexishipin")
public class XuexishipinController {
    private static final Logger logger = LoggerFactory.getLogger(XuexishipinController.class);

    private static final String TABLE_NAME = "xuexishipin";

    @Autowired
    private XuexishipinService xuexishipinService;


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
    private ExamrecordService examrecordService;//考试记录表
    @Autowired
    private ExamredetailsService examredetailsService;//答题详情表
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//公告信息
    @Autowired
    private XuexishipinCollectionService xuexishipinCollectionService;//课程收藏
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZhiyejieshaoService zhiyejieshaoService;//职业介绍
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
        params.put("xuexishipinDeleteStart",1);params.put("xuexishipinDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = xuexishipinService.queryPage(params);

        //字典表数据转换
        List<XuexishipinView> list =(List<XuexishipinView>)page.getList();
        for(XuexishipinView c:list){
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
        XuexishipinEntity xuexishipin = xuexishipinService.selectById(id);
        if(xuexishipin !=null){
            //entity转view
            XuexishipinView view = new XuexishipinView();
            BeanUtils.copyProperties( xuexishipin , view );//把实体数据重构到view中
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
    public R save(@RequestBody XuexishipinEntity xuexishipin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xuexishipin:{}",this.getClass().getName(),xuexishipin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<XuexishipinEntity> queryWrapper = new EntityWrapper<XuexishipinEntity>()
            .eq("xuexishipin_name", xuexishipin.getXuexishipinName())
            .eq("xuexishipin_video", xuexishipin.getXuexishipinVideo())
            .eq("xuexishipin_types", xuexishipin.getXuexishipinTypes())
            .eq("zhiyejieshao_types", xuexishipin.getZhiyejieshaoTypes())
            .eq("xuexishipin_delete", xuexishipin.getXuexishipinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XuexishipinEntity xuexishipinEntity = xuexishipinService.selectOne(queryWrapper);
        if(xuexishipinEntity==null){
            xuexishipin.setXuexishipinDelete(1);
            xuexishipin.setInsertTime(new Date());
            xuexishipin.setCreateTime(new Date());
            xuexishipinService.insert(xuexishipin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XuexishipinEntity xuexishipin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xuexishipin:{}",this.getClass().getName(),xuexishipin.toString());
        XuexishipinEntity oldXuexishipinEntity = xuexishipinService.selectById(xuexishipin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(xuexishipin.getXuexishipinPhoto()) || "null".equals(xuexishipin.getXuexishipinPhoto())){
                xuexishipin.setXuexishipinPhoto(null);
        }
        if("".equals(xuexishipin.getXuexishipinVideo()) || "null".equals(xuexishipin.getXuexishipinVideo())){
                xuexishipin.setXuexishipinVideo(null);
        }

            xuexishipinService.updateById(xuexishipin);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XuexishipinEntity> oldXuexishipinList =xuexishipinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<XuexishipinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            XuexishipinEntity xuexishipinEntity = new XuexishipinEntity();
            xuexishipinEntity.setId(id);
            xuexishipinEntity.setXuexishipinDelete(2);
            list.add(xuexishipinEntity);
        }
        if(list != null && list.size() >0){
            xuexishipinService.updateBatchById(list);
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
            List<XuexishipinEntity> xuexishipinList = new ArrayList<>();//上传的东西
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
                            XuexishipinEntity xuexishipinEntity = new XuexishipinEntity();
//                            xuexishipinEntity.setXuexishipinName(data.get(0));                    //课程名称 要改的
//                            xuexishipinEntity.setXuexishipinPhoto("");//详情和图片
//                            xuexishipinEntity.setXuexishipinVideo(data.get(0));                    //学习视频 要改的
//                            xuexishipinEntity.setXuexishipinTypes(Integer.valueOf(data.get(0)));   //课程类型 要改的
//                            xuexishipinEntity.setZhiyejieshaoTypes(Integer.valueOf(data.get(0)));   //职业 要改的
//                            xuexishipinEntity.setXuexishipinContent("");//详情和图片
//                            xuexishipinEntity.setXuexishipinDelete(1);//逻辑删除字段
//                            xuexishipinEntity.setInsertTime(date);//时间
//                            xuexishipinEntity.setCreateTime(date);//时间
                            xuexishipinList.add(xuexishipinEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        xuexishipinService.insertBatch(xuexishipinList);
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
        List<XuexishipinView> returnXuexishipinViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = xuexishipinCollectionService.queryPage(params1);
        List<XuexishipinCollectionView> collectionViewsList =(List<XuexishipinCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(XuexishipinCollectionView collectionView:collectionViewsList){
            Integer xuexishipinTypes = collectionView.getXuexishipinTypes();
            if(typeMap.containsKey(xuexishipinTypes)){
                typeMap.put(xuexishipinTypes,typeMap.get(xuexishipinTypes)+1);
            }else{
                typeMap.put(xuexishipinTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("xuexishipinTypes",type);
            PageUtils pageUtils1 = xuexishipinService.queryPage(params2);
            List<XuexishipinView> xuexishipinViewList =(List<XuexishipinView>)pageUtils1.getList();
            returnXuexishipinViewList.addAll(xuexishipinViewList);
            if(returnXuexishipinViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = xuexishipinService.queryPage(params);
        if(returnXuexishipinViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnXuexishipinViewList.size();//要添加的数量
            List<XuexishipinView> xuexishipinViewList =(List<XuexishipinView>)page.getList();
            for(XuexishipinView xuexishipinView:xuexishipinViewList){
                Boolean addFlag = true;
                for(XuexishipinView returnXuexishipinView:returnXuexishipinViewList){
                    if(returnXuexishipinView.getId().intValue() ==xuexishipinView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnXuexishipinViewList.add(xuexishipinView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnXuexishipinViewList = returnXuexishipinViewList.subList(0, limit);
        }

        for(XuexishipinView c:returnXuexishipinViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnXuexishipinViewList);
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
        PageUtils page = xuexishipinService.queryPage(params);

        //字典表数据转换
        List<XuexishipinView> list =(List<XuexishipinView>)page.getList();
        for(XuexishipinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XuexishipinEntity xuexishipin = xuexishipinService.selectById(id);
            if(xuexishipin !=null){


                //entity转view
                XuexishipinView view = new XuexishipinView();
                BeanUtils.copyProperties( xuexishipin , view );//把实体数据重构到view中

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
    public R add(@RequestBody XuexishipinEntity xuexishipin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,xuexishipin:{}",this.getClass().getName(),xuexishipin.toString());
        Wrapper<XuexishipinEntity> queryWrapper = new EntityWrapper<XuexishipinEntity>()
            .eq("xuexishipin_name", xuexishipin.getXuexishipinName())
            .eq("xuexishipin_video", xuexishipin.getXuexishipinVideo())
            .eq("xuexishipin_types", xuexishipin.getXuexishipinTypes())
            .eq("zhiyejieshao_types", xuexishipin.getZhiyejieshaoTypes())
            .eq("xuexishipin_delete", xuexishipin.getXuexishipinDelete())
//            .notIn("xuexishipin_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XuexishipinEntity xuexishipinEntity = xuexishipinService.selectOne(queryWrapper);
        if(xuexishipinEntity==null){
            xuexishipin.setXuexishipinDelete(1);
            xuexishipin.setInsertTime(new Date());
            xuexishipin.setCreateTime(new Date());
        xuexishipinService.insert(xuexishipin);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

