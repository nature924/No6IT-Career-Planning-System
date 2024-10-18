
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
 * 答题记录表
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/examrecord")
public class ExamrecordController {
    private static final Logger logger = LoggerFactory.getLogger(ExamrecordController.class);

    private static final String TABLE_NAME = "examrecord";

    @Autowired
    private ExamrecordService examrecordService;


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
        CommonUtil.checkMap(params);
        PageUtils page = examrecordService.queryPage(params);

        //字典表数据转换
        List<ExamrecordView> list =(List<ExamrecordView>)page.getList();
        for(ExamrecordView c:list){
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
        ExamrecordEntity examrecord = examrecordService.selectById(id);
        if(examrecord !=null){
            //entity转view
            ExamrecordView view = new ExamrecordView();
            BeanUtils.copyProperties( examrecord , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(examrecord.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 试卷表
            //级联表
            ExampaperEntity exampaper = exampaperService.selectById(examrecord.getExampaperId());
            if(exampaper != null){
            BeanUtils.copyProperties( exampaper , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setExampaperId(exampaper.getId());
            }
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
    public R save(@RequestBody ExamrecordEntity examrecord, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,examrecord:{}",this.getClass().getName(),examrecord.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            examrecord.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ExamrecordEntity> queryWrapper = new EntityWrapper<ExamrecordEntity>()
            .eq("yonghu_id", examrecord.getYonghuId())
            .eq("exampaper_id", examrecord.getExampaperId())
            .eq("total_score", examrecord.getTotalScore())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ExamrecordEntity examrecordEntity = examrecordService.selectOne(queryWrapper);
        if(examrecordEntity==null){
            examrecord.setInsertTime(new Date());
            examrecord.setCreateTime(new Date());
            examrecordService.insert(examrecord);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ExamrecordEntity examrecord, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,examrecord:{}",this.getClass().getName(),examrecord.toString());
        ExamrecordEntity oldExamrecordEntity = examrecordService.selectById(examrecord.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            examrecord.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            examrecordService.updateById(examrecord);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ExamrecordEntity> oldExamrecordList =examrecordService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        examrecordService.deleteBatchIds(Arrays.asList(ids));

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
            List<ExamrecordEntity> examrecordList = new ArrayList<>();//上传的东西
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
                            ExamrecordEntity examrecordEntity = new ExamrecordEntity();
//                            examrecordEntity.setExamrecordUuidNumber(data.get(0));                    //答题编号 要改的
//                            examrecordEntity.setYonghuId(Integer.valueOf(data.get(0)));   //答题学生 要改的
//                            examrecordEntity.setExampaperId(Integer.valueOf(data.get(0)));   //所属试卷id（外键） 要改的
//                            examrecordEntity.setTotalScore(Integer.valueOf(data.get(0)));   //所得总分 要改的
//                            examrecordEntity.setInsertTime(date);//时间
//                            examrecordEntity.setCreateTime(date);//时间
                            examrecordList.add(examrecordEntity);


                            //把要查询是否重复的字段放入map中
                                //答题编号
                                if(seachFields.containsKey("examrecordUuidNumber")){
                                    List<String> examrecordUuidNumber = seachFields.get("examrecordUuidNumber");
                                    examrecordUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> examrecordUuidNumber = new ArrayList<>();
                                    examrecordUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("examrecordUuidNumber",examrecordUuidNumber);
                                }
                        }

                        //查询是否重复
                         //答题编号
                        List<ExamrecordEntity> examrecordEntities_examrecordUuidNumber = examrecordService.selectList(new EntityWrapper<ExamrecordEntity>().in("examrecord_uuid_number", seachFields.get("examrecordUuidNumber")));
                        if(examrecordEntities_examrecordUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ExamrecordEntity s:examrecordEntities_examrecordUuidNumber){
                                repeatFields.add(s.getExamrecordUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [答题编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        examrecordService.insertBatch(examrecordList);
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = examrecordService.queryPage(params);

        //字典表数据转换
        List<ExamrecordView> list =(List<ExamrecordView>)page.getList();
        for(ExamrecordView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ExamrecordEntity examrecord = examrecordService.selectById(id);
            if(examrecord !=null){


                //entity转view
                ExamrecordView view = new ExamrecordView();
                BeanUtils.copyProperties( examrecord , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(examrecord.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    ExampaperEntity exampaper = exampaperService.selectById(examrecord.getExampaperId());
                if(exampaper != null){
                    BeanUtils.copyProperties( exampaper , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setExampaperId(exampaper.getId());
                }
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
    public R add(@RequestBody ExamrecordEntity examrecord, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,examrecord:{}",this.getClass().getName(),examrecord.toString());
        Wrapper<ExamrecordEntity> queryWrapper = new EntityWrapper<ExamrecordEntity>()
            .eq("examrecord_uuid_number", examrecord.getExamrecordUuidNumber())
            .eq("yonghu_id", examrecord.getYonghuId())
            .eq("exampaper_id", examrecord.getExampaperId())
            .eq("total_score", examrecord.getTotalScore())
//            .notIn("examrecord_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ExamrecordEntity examrecordEntity = examrecordService.selectOne(queryWrapper);
        if(examrecordEntity==null){
            examrecord.setInsertTime(new Date());
            examrecord.setCreateTime(new Date());
        examrecordService.insert(examrecord);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

