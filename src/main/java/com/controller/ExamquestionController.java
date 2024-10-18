
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
 * 试题表
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/examquestion")
public class ExamquestionController {
    private static final Logger logger = LoggerFactory.getLogger(ExamquestionController.class);

    private static final String TABLE_NAME = "examquestion";

    @Autowired
    private ExamquestionService examquestionService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ExampaperService exampaperService;//试卷表
    @Autowired
    private ExampapertopicService exampapertopicService;//试卷选题
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
        PageUtils page = examquestionService.queryPage(params);

        //字典表数据转换
        List<ExamquestionView> list =(List<ExamquestionView>)page.getList();
        for(ExamquestionView c:list){
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
        ExamquestionEntity examquestion = examquestionService.selectById(id);
        if(examquestion !=null){
            //entity转view
            ExamquestionView view = new ExamquestionView();
            BeanUtils.copyProperties( examquestion , view );//把实体数据重构到view中
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
    public R save(@RequestBody ExamquestionEntity examquestion, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,examquestion:{}",this.getClass().getName(),examquestion.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ExamquestionEntity> queryWrapper = new EntityWrapper<ExamquestionEntity>()
            .eq("examquestion_name", examquestion.getExamquestionName())
            .eq("kemu_types", examquestion.getKemuTypes())
            .eq("examquestion_options", examquestion.getExamquestionOptions())
            .eq("examquestion_answer", examquestion.getExamquestionAnswer())
            .eq("examquestion_analysis", examquestion.getExamquestionAnalysis())
            .eq("examquestion_types", examquestion.getExamquestionTypes())
            .eq("examquestion_sequence", examquestion.getExamquestionSequence())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ExamquestionEntity examquestionEntity = examquestionService.selectOne(queryWrapper);
        if(examquestionEntity==null){
            examquestion.setCreateTime(new Date());
            examquestionService.insert(examquestion);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ExamquestionEntity examquestion, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,examquestion:{}",this.getClass().getName(),examquestion.toString());
        ExamquestionEntity oldExamquestionEntity = examquestionService.selectById(examquestion.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            examquestionService.updateById(examquestion);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ExamquestionEntity> oldExamquestionList =examquestionService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        examquestionService.deleteBatchIds(Arrays.asList(ids));

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
            List<ExamquestionEntity> examquestionList = new ArrayList<>();//上传的东西
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
                            ExamquestionEntity examquestionEntity = new ExamquestionEntity();
//                            examquestionEntity.setExamquestionName(data.get(0));                    //试题名称 要改的
//                            examquestionEntity.setKemuTypes(Integer.valueOf(data.get(0)));   //科目 要改的
//                            examquestionEntity.setExamquestionOptions(data.get(0));                    //选项，json字符串 要改的
//                            examquestionEntity.setExamquestionAnswer(data.get(0));                    //正确答案 要改的
//                            examquestionEntity.setExamquestionAnalysis(data.get(0));                    //答案解析 要改的
//                            examquestionEntity.setExamquestionTypes(Integer.valueOf(data.get(0)));   //试题类型 要改的
//                            examquestionEntity.setExamquestionSequence(Integer.valueOf(data.get(0)));   //试题排序，值越大排越前面 要改的
//                            examquestionEntity.setCreateTime(date);//时间
                            examquestionList.add(examquestionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        examquestionService.insertBatch(examquestionList);
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
        PageUtils page = examquestionService.queryPage(params);

        //字典表数据转换
        List<ExamquestionView> list =(List<ExamquestionView>)page.getList();
        for(ExamquestionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ExamquestionEntity examquestion = examquestionService.selectById(id);
            if(examquestion !=null){


                //entity转view
                ExamquestionView view = new ExamquestionView();
                BeanUtils.copyProperties( examquestion , view );//把实体数据重构到view中

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
    public R add(@RequestBody ExamquestionEntity examquestion, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,examquestion:{}",this.getClass().getName(),examquestion.toString());
        Wrapper<ExamquestionEntity> queryWrapper = new EntityWrapper<ExamquestionEntity>()
            .eq("examquestion_name", examquestion.getExamquestionName())
            .eq("kemu_types", examquestion.getKemuTypes())
            .eq("examquestion_options", examquestion.getExamquestionOptions())
            .eq("examquestion_answer", examquestion.getExamquestionAnswer())
            .eq("examquestion_analysis", examquestion.getExamquestionAnalysis())
            .eq("examquestion_types", examquestion.getExamquestionTypes())
            .eq("examquestion_sequence", examquestion.getExamquestionSequence())
//            .notIn("examquestion_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ExamquestionEntity examquestionEntity = examquestionService.selectOne(queryWrapper);
        if(examquestionEntity==null){
            examquestion.setCreateTime(new Date());
        examquestionService.insert(examquestion);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

